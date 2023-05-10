package com.example.bladecalculator.repository;

import com.example.bladecalculator.domain.UserSkillVO;
import java.util.List;

public interface SkillRepositoryCustom {

    List<UserSkillVO> getSkills(Long userId);

    UserSkillVO getSkill(String skillId);

    boolean existsSkill(Long userId);

    void insertSkills(List<UserSkillVO> userSkillVOS, Long userId);

    void updateSkills(List<UserSkillVO> userSkillVOS, Long userId);
}
