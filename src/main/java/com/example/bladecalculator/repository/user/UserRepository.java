package com.example.bladecalculator.repository.user;

import com.example.bladecalculator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    User findByUserId(String userId);
}
