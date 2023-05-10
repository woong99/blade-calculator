package com.example.bladecalculator.entity;

import jakarta.persistence.Entity;
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
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skill {

    @Id
    private String name;

    private String imageUrl;

    private int skillOrder;

    private String description;

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    @Setter
    @Exclude
    private List<UserSkill> userSkills = new ArrayList<>();
}
