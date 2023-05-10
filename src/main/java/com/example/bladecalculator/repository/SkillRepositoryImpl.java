package com.example.bladecalculator.repository;

import com.example.bladecalculator.domain.UserSkillVO;
import com.example.bladecalculator.entity.QUserSkill;
import com.example.bladecalculator.entity.UserSkill;
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
public class SkillRepositoryImpl implements SkillRepositoryCustom {

    private final JdbcTemplate jdbcTemplate;

    private final JPAQueryFactory jpaQueryFactory;

    QUserSkill qUserSkill = QUserSkill.userSkill;


    /**
     * 유저의 스킬 정보를 조회한다.
     *
     * @param userId 유저 ID
     * @return 스킬 정보
     */
    @Override
    public List<UserSkillVO> getSkills(Long userId) {
        String sql = "select s.*, u.point, sd.* from skill s "
                + "left join user_skill u on s.name = u.skill_id and u.user_id = ? "
                + "left join skill_data_mining sd on sd.id = concat(s.skill_order, coalesce(u.point, 0)) "
                + "order by s.skill_order";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                        UserSkillVO.builder()
                                .name(rs.getString("name"))
                                .description(rs.getString("description"))
                                .imageUrl(rs.getString("image_url"))
                                .skillOrder(rs.getInt("skill_order"))
                                .point(rs.getString("point"))
                                .ownedEffect1(rs.getString("owned_effect1"))
                                .ownedEffectValue1(rs.getString("owned_effect_value1"))
                                .ownedEffect2(rs.getString("owned_effect2"))
                                .ownedEffectValue2(rs.getString("owned_effect_value2"))
                                .skillBooksNeeded(rs.getString("skill_books_needed"))
                                .moonstonesNeeded(rs.getString("moonstones_needed"))
                                .pen(rs.getString("pen"))
                                .acu(rs.getString("acu"))
                                .critical(rs.getString("critical"))
                                .superCritical(rs.getString("super_critical"))
                                .hyperCritical(rs.getString("hyper_critical"))
                                .skill(rs.getString("skill"))
                                .superSkill(rs.getString("super_skill"))
                                .build()
                , userId);
    }


    /**
     * 단일 스킬 정보를 조회한다.
     *
     * @param skillId 스킬 ID
     * @return 단일 스킬 정보
     */
    @Override
    public UserSkillVO getSkill(String skillId) {
        String sql = "select * from skill_data_mining where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> UserSkillVO.builder()
                    .name(rs.getString("name"))
                    .level(rs.getInt("level"))
                    .ownedEffect1(rs.getString("owned_effect1"))
                    .ownedEffectValue1(rs.getString("owned_effect_value1"))
                    .ownedEffect2(rs.getString("owned_effect2"))
                    .ownedEffectValue2(rs.getString("owned_effect_value2"))
                    .skillBooksNeeded(rs.getString("skill_books_needed"))
                    .moonstonesNeeded(rs.getString("moonstones_needed"))
                    .pen(rs.getString("pen"))
                    .acu(rs.getString("acu"))
                    .critical(rs.getString("critical"))
                    .superCritical(rs.getString("super_critical"))
                    .hyperCritical(rs.getString("hyper_critical"))
                    .skill(rs.getString("skill"))
                    .superSkill(rs.getString("super_skill"))
                    .build(), skillId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    /**
     * 저장된 스킬 정보가 있는지 조회한다.
     *
     * @param userId 유저 ID
     * @return 스킬 저장 유무
     */
    @Override
    public boolean existsSkill(Long userId) {
        UserSkill fetchOne = jpaQueryFactory
                .selectFrom(qUserSkill)
                .where(qUserSkill.user.id.eq(userId))
                .fetchFirst();
        return fetchOne != null;
    }


    /**
     * 스킬 정보를 저장한다.
     *
     * @param userSkillVOS 유저 스킬 정보
     * @param userId       유저 ID
     */
    @Override
    public void insertSkills(List<UserSkillVO> userSkillVOS, Long userId) {
        String sql = "INSERT INTO user_skill (point, created_at, skill_id, user_id) VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        UserSkillVO userSkillVO = userSkillVOS.get(i);
                        ps.setString(1, userSkillVO.getPoint());
                        ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                        ps.setString(3, userSkillVO.getName());
                        ps.setLong(4, userId);
                    }

                    @Override
                    public int getBatchSize() {
                        return userSkillVOS.size();
                    }
                });
    }


    /**
     * 스킬 정보를 수정한다.
     *
     * @param userSkillVOS 유저 스킬 정보
     * @param userId       유저 ID
     */
    @Override
    public void updateSkills(List<UserSkillVO> userSkillVOS, Long userId) {
        String sql = "UPDATE user_skill SET point = ?, updated_at = ? WHERE user_id = ? AND skill_id = ?";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                UserSkillVO userSkillVO = userSkillVOS.get(i);
                ps.setString(1, userSkillVO.getPoint());
                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                ps.setLong(3, userId);
                ps.setString(4, userSkillVO.getName());
            }

            @Override
            public int getBatchSize() {
                return userSkillVOS.size();
            }
        });
    }
}
