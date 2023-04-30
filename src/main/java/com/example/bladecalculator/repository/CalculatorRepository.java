package com.example.bladecalculator.repository;

import com.example.bladecalculator.entity.Calculator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculatorRepository extends JpaRepository<Calculator, Long> {

}
