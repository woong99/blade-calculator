package com.example.bladecalculator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Stat {

    @Id
    private String name;

    @Enumerated(value = EnumType.STRING)
    private StatType type;

    private int statOrder;

    private String description;

    @OneToMany(mappedBy = "stat", fetch = FetchType.LAZY)
    @Setter
    @Exclude
    private List<UserStat> userStats = new ArrayList<>();

}
