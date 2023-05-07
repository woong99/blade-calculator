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
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Growth {

    @Id
    private String name;

    private String imageUrl;

    private int growthOrder;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private DataMiningType dataMiningType;

    @OneToMany(mappedBy = "growth", fetch = FetchType.LAZY)
    @Setter
    @Exclude
    private List<UserGrowth> userGrowths = new ArrayList<>();
}
