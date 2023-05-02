package com.example.bladecalculator.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Authority {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String authority;
}
