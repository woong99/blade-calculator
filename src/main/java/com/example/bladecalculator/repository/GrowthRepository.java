package com.example.bladecalculator.repository;

import com.example.bladecalculator.entity.Growth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrowthRepository extends JpaRepository<Growth, String>, GrowthRepositoryCustom {
}
