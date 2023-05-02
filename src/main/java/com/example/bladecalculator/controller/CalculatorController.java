package com.example.bladecalculator.controller;

import com.example.bladecalculator.auth.annotation.AuthUser;
import com.example.bladecalculator.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calculator")
@RequiredArgsConstructor
@Slf4j
public class CalculatorController {

    @RequestMapping("/hp")
    public String hp(@AuthUser User user) {
        return "/views/user/calculator/hp";
    }
}
