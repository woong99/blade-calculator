package com.example.bladecalculator.repository;

import com.example.bladecalculator.domain.UserGrowthVO;
import com.example.bladecalculator.entity.DataMiningType;
import com.example.bladecalculator.entity.QDataMining;
import com.example.bladecalculator.entity.QGrowth;
import com.example.bladecalculator.entity.QUserGrowth;
import com.example.bladecalculator.entity.UserGrowth;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GrowthRepositoryImpl implements GrowthRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final JdbcTemplate jdbcTemplate;

    QGrowth qGrowth = QGrowth.growth;

    QUserGrowth qUserGrowth = QUserGrowth.userGrowth;

    QDataMining qDataMining = QDataMining.dataMining;


    @Override
    public List<UserGrowthVO> getGrowths(Long userId) {
        String sql = "select g.name, g.image_url, g.description, g.data_mining_type, u.point, d.cost, d.value_increase "
                + "from growth g "
                + "left join user_growth u on g.name=u.growth_id and u.user_id = ? "
                + "left join data_mining d on cast(d.level as nchar) = coalesce(cast(format(replace(u.point, ',', '') + 1,0)  as nchar), '1') and g.data_mining_type = d.data_mining_type "
                + "order by g.growth_order";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                UserGrowthVO.builder()
                        .name(rs.getString("name"))
                        .imageUrl(rs.getString("image_url"))
                        .description(rs.getString("description"))
                        .point(rs.getString("point"))
                        .cost(rs.getString("cost"))
                        .dataMiningType(DataMiningType.valueOf(rs.getString("data_mining_type")))
                        .valueIncrease(rs.getString("value_increase"))
                        .build(), userId);
    }


    @Override
    public UserGrowthVO getGrowthCost(String point, DataMiningType dataMiningType) {
        String sql = "select cost, value_increase from data_mining where convert(level, nchar) = ? and data_mining_type = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> UserGrowthVO.builder()
                    .cost(rs.getString("cost"))
                    .valueIncrease(rs.getString("value_increase"))
                    .build(), point, dataMiningType.name());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public boolean existsGrowth(Long userId) {
        UserGrowth fetchOne = jpaQueryFactory
                .selectFrom(qUserGrowth)
                .where(qUserGrowth.user.id.eq(userId))
                .fetchFirst();
        return fetchOne != null;
    }


    @Override
    public void insertGrowths(List<UserGrowthVO> userGrowthVOS, Long userId) {
        String sql = "INSERT INTO user_growth (point, growth_id, user_id, created_at) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        UserGrowthVO userGrowthVO = userGrowthVOS.get(i);
                        ps.setString(1, userGrowthVO.getPoint());
                        ps.setString(2, userGrowthVO.getName());
                        ps.setLong(3, userId);
                        ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                    }

                    @Override
                    public int getBatchSize() {
                        return userGrowthVOS.size();
                    }
                });
    }


    @Override
    public void updateGrowths(List<UserGrowthVO> userGrowthVOS, Long userId) {
        String sql = "UPDATE user_growth SET point = ?, updated_at = ? WHERE user_id = ? and growth_id = ?";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                UserGrowthVO userGrowthVO = userGrowthVOS.get(i);
                ps.setString(1, userGrowthVO.getPoint());
                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                ps.setLong(3, userId);
                ps.setString(4, userGrowthVO.getName());
            }

            @Override
            public int getBatchSize() {
                return userGrowthVOS.size();
            }
        });
    }
}
