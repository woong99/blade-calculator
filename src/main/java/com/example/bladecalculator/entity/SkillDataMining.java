package com.example.bladecalculator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillDataMining {

    @Id
    private Long id;

    private String name;

    private int level;

    private String ownedEffect1;

    private String ownedEffectValue1;

    private String ownedEffect2;

    private String ownedEffectValue2;

    private String skillBooksNeeded;

    private String moonstonesNeeded;

    private String pen;

    private String acu;

    private String critical;

    private String superCritical;

    private String hyperCritical;

    private String skill;

    private String superSkill;
}
