package com.example.bladecalculator.controller;

import com.example.bladecalculator.service.CalculatorService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final CalculatorService calculatorService;

    @RequestMapping("/")
    public String main() throws IOException {
//        Calculator calculator = calculatorService.getCalculator();
//        File file = new File("/Users/woong_9yo/Desktop/Workplace/blade-calculator/src/main/resources/_source/cal.html");
//        FileUtils.writeStringToFile(file, calculator.getContent(), StandardCharsets.UTF_8);
//        log.info("calculator : {}", calculator);
        return "views/main";
    }
}
