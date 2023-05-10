package com.example.bladecalculator.controller;

import com.example.bladecalculator.domain.PaginationVO;
import com.example.bladecalculator.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

@RequestMapping("/_admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final LogService logService;


    @RequestMapping("/login-log")
    public String loginLog(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                           ModelMap model) {
        PaginationVO paginationVO = logService.getLoginLogs(page);

        model.addAttribute("pagination", paginationVO);
        return "views/admin/loginLog";
    }
}
