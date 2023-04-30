package com.example.bladecalculator.service;

import com.example.bladecalculator.entity.Calculator;
import com.example.bladecalculator.repository.CalculatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculatorService {

    private final CalculatorRepository calculatorRepository;

    public Calculator getCalculator() {
        return calculatorRepository.findById(1L).orElse(null);
    }
}
