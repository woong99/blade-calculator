package com.example.bladecalculator.domain;

import com.example.bladecalculator.entity.DataMiningType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserGrowthVO {

    private String name;

    private String imageUrl;

    private String description;

    private String point;

    private String cost;

    private String valueIncrease;

    private DataMiningType dataMiningType;

}
