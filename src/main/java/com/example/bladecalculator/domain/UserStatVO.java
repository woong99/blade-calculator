package com.example.bladecalculator.domain;

import com.example.bladecalculator.entity.Stat;
import com.example.bladecalculator.entity.UserStat;
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
public class UserStatVO {
    private String name;

    private String statType;

    private String description;

    private String point;

    public static UserStatVO toUserStatVO(UserStat userStat) {
        return UserStatVO.builder()
                .name(userStat.getStat().getName())
                .statType(userStat.getStat().getType().name())
                .description(userStat.getStat().getDescription())
                .point(userStat.getPoint())
                .build();
    }

    public static UserStatVO toUserStatVO(Stat stat) {
        return UserStatVO.builder()
                .name(stat.getName())
                .statType(stat.getType().name())
                .description(stat.getDescription())
                .build();
    }
}
