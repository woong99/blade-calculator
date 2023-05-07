package com.example.bladecalculator.repository;

import com.example.bladecalculator.domain.UserStatVO;
import com.example.bladecalculator.entity.StatType;
import java.util.List;

public interface StatRepositoryCustom {
    List<UserStatVO> getStats(Long userId, StatType statType);

    boolean existsStat(Long userId, StatType statType);

    void insertStats(List<UserStatVO> userStatVOS, Long userId);

    void updateStats(List<UserStatVO> userStatVOS, Long userId);
}
