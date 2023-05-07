package com.example.bladecalculator.repository;

import com.example.bladecalculator.domain.UserGrowthVO;
import com.example.bladecalculator.entity.DataMiningType;
import java.util.List;

public interface GrowthRepositoryCustom {

    List<UserGrowthVO> getGrowths(Long userId);

    UserGrowthVO getGrowthCost(String point, DataMiningType dataMiningType);

    boolean existsGrowth(Long userId);

    void insertGrowths(List<UserGrowthVO> userGrowthVOS, Long userId);

    void updateGrowths(List<UserGrowthVO> userGrowthVOS, Long userId);
}
