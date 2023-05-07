package com.example.bladecalculator.service;

import com.example.bladecalculator.domain.UserGrowthListVO;
import com.example.bladecalculator.domain.UserGrowthVO;
import com.example.bladecalculator.domain.UserStatListVO;
import com.example.bladecalculator.domain.UserStatVO;
import com.example.bladecalculator.entity.DataMiningType;
import com.example.bladecalculator.entity.StatType;
import com.example.bladecalculator.entity.User;
import com.example.bladecalculator.repository.GrowthRepository;
import com.example.bladecalculator.repository.StatRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculatorService {

    private final StatRepository statRepository;

    private final GrowthRepository growthRepository;


    /**
     * 스탯을 조회한다.
     *
     * @param user     유저 정보
     * @param statType 스탯 타입
     * @return 스탯
     */
    public List<UserStatVO> getStats(User user, StatType statType) {
        return statRepository.getStats(user.getId(), statType);
    }


    /**
     * 스탯을 저장한다.
     *
     * @param userStatListVO 유저 스탯 정보
     * @param user           유저 정보
     * @param statType       스탯 타입
     */
    public void saveStats(UserStatListVO userStatListVO, User user, StatType statType) {
        boolean existsStat = statRepository.existsStat(user.getId(), statType);

        if (existsStat) {
            statRepository.updateStats(userStatListVO.getUserStatList(), user.getId());
        } else {
            statRepository.insertStats(userStatListVO.getUserStatList(), user.getId());
        }
    }


    public List<UserGrowthVO> getGrowths(User user) {
        return growthRepository.getGrowths(user.getId());
    }


    public UserGrowthVO getGrowthCost(String point, DataMiningType dataMiningType) {
        return growthRepository.getGrowthCost(point, dataMiningType);
    }


    public void saveGrowths(UserGrowthListVO userGrowthListVO, User user) {
        boolean existsGrowth = growthRepository.existsGrowth(user.getId());

        if (existsGrowth) {
            growthRepository.updateGrowths(userGrowthListVO.getUserGrowthList(), user.getId());
        } else {
            growthRepository.insertGrowths(userGrowthListVO.getUserGrowthList(), user.getId());
        }
    }
}
