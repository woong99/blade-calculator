package com.example.bladecalculator.service;

import com.example.bladecalculator.entity.StatType;
import com.example.bladecalculator.entity.User;
import com.example.bladecalculator.entity.UserStat;
import com.example.bladecalculator.repository.StatRepository;
import com.example.bladecalculator.vo.UserStatListVO;
import com.example.bladecalculator.vo.UserStatVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculatorService {

    private static final String COMMAND = "command";
    private static final String STATS = "stats";
    private final StatRepository statRepository;


    /**
     * 스탯을 조회한다.
     *
     * @param user     유저 정보
     * @param statType 스탯 타입
     * @return 스탯
     */
    public Map<String, Object> getStats(User user, StatType statType) {
        Map<String, Object> map = new HashMap<>();

        List<UserStat> stats = statRepository.getStatsWithUserId(user.getUserId(), statType);
        if (stats.isEmpty()) {
            map.put(COMMAND, "insert");
            map.put(STATS,
                    statRepository.getStatsWithOutUserId(statType).stream().map(UserStatVO::toUserStatVO).toList());
            return map;
        }
        map.put(COMMAND, "update");
        map.put(STATS, stats.stream().map(UserStatVO::toUserStatVO).toList());

        return map;
    }


    /**
     * 스탯을 등록한다.
     *
     * @param userStatListVO 유저 스탯 정보
     * @param user           유저 정보
     */
    public void insertStats(UserStatListVO userStatListVO, User user) {
        statRepository.insertStats(userStatListVO.getUserStatList(), user.getId());
    }


    /**
     * 스탯을 수정한다.
     *
     * @param userStatListVO 유저 스탯 정보
     * @param user           유저 정보
     */
    public void updateStats(UserStatListVO userStatListVO, User user) {
        statRepository.updateStats(userStatListVO.getUserStatList(), user.getId());
    }
}
