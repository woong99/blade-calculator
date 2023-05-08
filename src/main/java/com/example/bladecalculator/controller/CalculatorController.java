package com.example.bladecalculator.controller;

import com.example.bladecalculator.auth.annotation.AuthUser;
import com.example.bladecalculator.domain.UserGrowthListVO;
import com.example.bladecalculator.domain.UserGrowthVO;
import com.example.bladecalculator.domain.UserStatListVO;
import com.example.bladecalculator.domain.UserStatVO;
import com.example.bladecalculator.entity.DataMiningType;
import com.example.bladecalculator.entity.StatType;
import com.example.bladecalculator.entity.User;
import com.example.bladecalculator.service.CalculatorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/calculator")
@RequiredArgsConstructor
@Slf4j
public class CalculatorController {

    private final CalculatorService calculatorService;


    /**
     * 계산기 > 체력
     *
     * @param user  유저 정보
     * @param model 모델
     * @return "/views/user/calculator/hp"
     */
    @RequestMapping("/hp")
    public String hp(@AuthUser User user, ModelMap model) {
        List<UserStatVO> stats = calculatorService.getStats(user, StatType.HP);

        model.addAttribute("stats", stats);
        model.addAttribute("statType", StatType.HP);
        return "views/user/calculator/hp";
    }


    /**
     * 계산기 > 체력 > 저장 action
     *
     * @param user           유저 정보
     * @param userStatListVO 유저 스탯 정보
     * @param statType       스탯 타입
     * @param model          모델
     * @return "/views/common/message"
     */
    @RequestMapping("/hp/save.do")
    public String hpSave(@AuthUser User user, UserStatListVO userStatListVO,
                         @RequestParam("type") StatType statType, ModelMap model) {
        calculatorService.saveStats(userStatListVO, user, statType);

        model.addAttribute("type", "msg+url");
        model.addAttribute("message", "저장이 완료되었습니다.");
        model.addAttribute("returnUrl", "/calculator/hp");
        return "views/common/message";
    }


    /**
     * 계산기 > 방어력
     *
     * @param user  유저 정보
     * @param model 모델
     * @return "/views/user/calculator/defense"
     */
    @RequestMapping("/defense")
    public String defense(@AuthUser User user, ModelMap model) {
        List<UserStatVO> stats = calculatorService.getStats(user, StatType.DEF);

        model.addAttribute("stats", stats);
        model.addAttribute("statType", StatType.DEF);
        return "views/user/calculator/defense";
    }


    /**
     * 계산기 > 방어력 > 저장 action
     *
     * @param user           유저 정보
     * @param userStatListVO 유저 스탯 정보
     * @param statType       스탯 타입
     * @param model          모델
     * @return "/views/common/message"
     */
    @RequestMapping("/defense/save.do")
    public String defenseSave(@AuthUser User user, UserStatListVO userStatListVO,
                              @RequestParam("type") StatType statType, ModelMap model) {
        calculatorService.saveStats(userStatListVO, user, statType);

        model.addAttribute("type", "msg+url");
        model.addAttribute("message", "저장이 완료되었습니다.");
        model.addAttribute("returnUrl", "/calculator/defense");
        return "views/common/message";
    }


    /**
     * 계산기 > 데미지 & 성장
     *
     * @param user  유저 정보
     * @param model 모델
     * @return "/views/user/calculator/damage"
     */
    @RequestMapping("/damage")
    public String damage(@AuthUser User user, ModelMap model) {
        List<UserStatVO> stats = calculatorService.getStats(user, StatType.ATK);
        List<UserGrowthVO> growths = calculatorService.getGrowths(user);

        model.addAttribute("stats", stats);
        model.addAttribute("statType", StatType.ATK);
        model.addAttribute("growths", growths);
        return "views/user/calculator/damage";
    }


    /**
     * 계산기 > 데미지 & 성장 > 데미지 저장 action
     *
     * @param user           유저 정보
     * @param userStatListVO 유저 스탯 정보
     * @param statType       스탯 타입
     * @param model          모델
     * @return "/views/common/message"
     */
    @RequestMapping("/damage/atk/save.do")
    public String damageSave(@AuthUser User user, UserStatListVO userStatListVO,
                             @RequestParam("type") StatType statType, ModelMap model) {
        calculatorService.saveStats(userStatListVO, user, statType);

        model.addAttribute("type", "msg+url");
        model.addAttribute("message", "저장이 완료되었습니다.");
        model.addAttribute("returnUrl", "/calculator/damage");
        return "views/common/message";
    }


    /**
     * 계산기 > 데미지 & 성장 > 골드 스탯 정보 조회
     * @param point
     * @param dataMiningType
     * @return
     */
    @RequestMapping("/damage/growth/cost")
    public ResponseEntity<UserGrowthVO> getGrowthCost(@RequestParam("point") String point, @RequestParam("type")
    DataMiningType dataMiningType) {
        UserGrowthVO cost = calculatorService.getGrowthCost(point, dataMiningType);

        if (cost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(cost);
    }


    /**
     * 계산기 > 데미지 & 성장 > 골드 스탯 저장 action
     * @param user
     * @param userGrowthListVO
     * @param model
     * @return
     */
    @RequestMapping("/damage/growth/save.do")
    public String growthSave(@AuthUser User user, UserGrowthListVO userGrowthListVO, ModelMap model) {
        userGrowthListVO.getUserGrowthList().forEach(System.out::println);
        calculatorService.saveGrowths(userGrowthListVO, user);

        model.addAttribute("type", "msg+url");
        model.addAttribute("message", "저장이 완료되었습니다.");
        model.addAttribute("returnUrl", "/calculator/damage");
        return "views/common/message";
    }
}
