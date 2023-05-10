package com.example.bladecalculator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSkillVO {

    private String name;

    private String imageUrl;

    private String description;

    private String point;

    private int skillOrder;

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
