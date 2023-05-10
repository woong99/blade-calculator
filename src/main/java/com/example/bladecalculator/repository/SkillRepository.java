package com.example.bladecalculator.repository;

import com.example.bladecalculator.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long>, SkillRepositoryCustom {
}
