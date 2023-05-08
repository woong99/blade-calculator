package com.example.bladecalculator.controller;

import com.example.bladecalculator.auth.annotation.AuthUser;
import com.example.bladecalculator.entity.User;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    @RequestMapping("/")
    public String main() throws IOException {
        return "views/user/main";
    }


    @RequestMapping("/login")
    public String login(@AuthUser User user) {
        if (user != null) {
            return "redirect:/";
        }
        return "views/login";
    }
}
