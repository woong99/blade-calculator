package com.example.bladecalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DataMiningType {
    CRITICAL_P, CRITICAL_D, SUPER_CRITICAL_P, SUPER_CRITICAL_D, HYPER_CRITICAL_P, HYPER_CRITICAL_D, ATK, SUPER_SKILL_ATK, SPECIAL_ATK, SUPER_ATK
}
