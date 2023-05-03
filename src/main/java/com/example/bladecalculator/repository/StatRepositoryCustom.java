package com.example.bladecalculator.repository;

import com.example.bladecalculator.entity.Stat;
import com.example.bladecalculator.entity.StatType;
import com.example.bladecalculator.entity.UserStat;
import com.example.bladecalculator.vo.UserStatVO;
import java.util.List;

public interface StatRepositoryCustom {

    List<UserStat> getStatsWithUserId(String userId, StatType statType);

    List<Stat> getStatsWithOutUserId(StatType statType);

    void insertStats(List<UserStatVO> userStatVOS, Long userId);

    void updateStats(List<UserStatVO> userStatVOS, Long userId);
}
