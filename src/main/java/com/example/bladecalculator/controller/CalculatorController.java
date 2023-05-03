package com.example.bladecalculator.controller;

import com.example.bladecalculator.auth.annotation.AuthUser;
import com.example.bladecalculator.entity.StatType;
import com.example.bladecalculator.entity.User;
import com.example.bladecalculator.service.CalculatorService;
import com.example.bladecalculator.vo.UserStatListVO;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Map<String, Object> statMap = calculatorService.getStats(user, StatType.HP);

        model.addAttribute("stats", statMap.get("stats"));
        model.addAttribute("command", statMap.get("command"));
        return "/views/user/calculator/hp";
    }


    /**
     * 계산기 > 체력 > 등록 action
     *
     * @param user           유저 정보
     * @param userStatListVO 유저 스탯 정보
     * @param model          모델
     * @return "/views/common/message"
     */
    @RequestMapping("/hp/insert.do")
    public String hpInsert(@AuthUser User user, UserStatListVO userStatListVO, ModelMap model) {
        calculatorService.insertStats(userStatListVO, user);

        model.addAttribute("type", "msg+url");
        model.addAttribute("message", "저장이 완료되었습니다.");
        model.addAttribute("returnUrl", "/calculator/hp");
        return "/views/common/message";
    }


    /**
     * 계산기 > 체력 > 수정 action
     *
     * @param user           유저 정보
     * @param userStatListVO 유저 스탯 정보
     * @param model          모델
     * @return "/views/common/message"
     */
    @RequestMapping("/hp/update.do")
    public String hpUpdate(@AuthUser User user, UserStatListVO userStatListVO, ModelMap model) {
        calculatorService.updateStats(userStatListVO, user);

        model.addAttribute("type", "msg+url");
        model.addAttribute("message", "저징이 완료되었습니다.");
        model.addAttribute("returnUrl", "/calculator/hp");
        return "/views/common/message";
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
        Map<String, Object> statMap = calculatorService.getStats(user, StatType.DEF);

        model.addAttribute("stats", statMap.get("stats"));
        model.addAttribute("command", statMap.get("command"));
        return "/views/user/calculator/defense";
    }


    /**
     * 계산기 > 방어력 > 등록 action
     *
     * @param user           유저 정보
     * @param userStatListVO 유저 스탯 정보
     * @param model          모델
     * @return "/views/common/message"
     */
    @RequestMapping("/defense/insert.do")
    public String defenseInsert(@AuthUser User user, UserStatListVO userStatListVO, ModelMap model) {
        calculatorService.insertStats(userStatListVO, user);

        model.addAttribute("type", "msg+url");
        model.addAttribute("message", "저장이 완료되었습니다.");
        model.addAttribute("returnUrl", "/calculator/defense");
        return "/views/common/message";
    }


    /**
     * 계산기 > 방어력 > 수정 action
     *
     * @param user           유저 정보
     * @param userStatListVO 유저 스탯 정보
     * @param model          모델
     * @return "/views/common/message"
     */
    @RequestMapping("/defense/update.do")
    public String defenseUpdate(@AuthUser User user, UserStatListVO userStatListVO, ModelMap model) {
        calculatorService.updateStats(userStatListVO, user);

        model.addAttribute("type", "msg+url");
        model.addAttribute("message", "저징이 완료되었습니다.");
        model.addAttribute("returnUrl", "/calculator/defense");
        return "/views/common/message";
    }
}
