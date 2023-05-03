package com.example.bladecalculator.repository;

import com.example.bladecalculator.entity.QStat;
import com.example.bladecalculator.entity.QUserStat;
import com.example.bladecalculator.entity.Stat;
import com.example.bladecalculator.entity.StatType;
import com.example.bladecalculator.entity.UserStat;
import com.example.bladecalculator.vo.UserStatVO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StatRepositoryImpl implements StatRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final JdbcTemplate jdbcTemplate;
    QStat qStat = QStat.stat;
    QUserStat qUserStat = QUserStat.userStat;


    /**
     * 유저의 스탯을 조회한다.
     *
     * @param userId   유저 ID
     * @param statType 스탯 타입
     * @return 스탯
     */
    @Override
    public List<UserStat> getStatsWithUserId(String userId, StatType statType) {
        return jpaQueryFactory
                .selectFrom(qUserStat)
                .leftJoin(qUserStat.stat, qStat)
                .fetchJoin()
                .where(qUserStat.user.userId.eq(userId))
                .where(qStat.type.stringValue().like(statType.toString() + "%"))
                .orderBy(qStat.statOrder.asc())
                .fetch();
    }


    /**
     * 유저의 스탯을 조회한다.
     *
     * @param statType 스탯 타입
     * @return 스탯
     */
    @Override
    public List<Stat> getStatsWithOutUserId(StatType statType) {
        return jpaQueryFactory
                .select(qStat)
                .from(qStat)
                .where(qStat.type.stringValue().like(statType.toString() + "%"))
                .orderBy(qStat.statOrder.asc())
                .fetch();
    }


    /**
     * 스탯을 저장한다.
     *
     * @param userStatVOS 유저 스탯 정보
     * @param userId      유저 ID
     */
    @Override
    public void insertStats(List<UserStatVO> userStatVOS, Long userId) {
        String sql = "INSERT INTO user_stat (point, stat_name, user_id, created_at) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        UserStatVO userStatVO = userStatVOS.get(i);
                        ps.setString(1, userStatVO.getPoint());
                        ps.setString(2, userStatVO.getName());
                        ps.setLong(3, userId);
                        ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                    }

                    @Override
                    public int getBatchSize() {
                        return userStatVOS.size();
                    }
                });
    }


    /**
     * 스탯을 수정한다.
     *
     * @param userStatVOS 유저 스탯 정보
     * @param userId      유저 ID
     */
    @Override
    public void updateStats(List<UserStatVO> userStatVOS, Long userId) {
        String sql = "UPDATE user_stat SET point = ?, updated_at = ? WHERE user_id = ? and stat_name = ?";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        UserStatVO userStatVO = userStatVOS.get(i);
                        ps.setString(1, userStatVO.getPoint());
                        ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                        ps.setLong(3, userId);
                        ps.setString(4, userStatVO.getName());
                    }

                    @Override
                    public int getBatchSize() {
                        return userStatVOS.size();
                    }
                });
    }
}
