package com.example.bladecalculator.repository;

import com.example.bladecalculator.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository extends JpaRepository<Stat, Long>, StatRepositoryCustom {

}
