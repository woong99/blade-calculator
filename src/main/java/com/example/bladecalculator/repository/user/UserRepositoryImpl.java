package com.example.bladecalculator.repository.user;

import com.example.bladecalculator.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    QUser qUser = QUser.user;


    @Override
    public void updateLastLoginAt(String userId) {
        jpaQueryFactory
                .update(qUser)
                .where(qUser.userId.eq(userId))
                .set(qUser.lastLoginAt, LocalDateTime.now())
                .execute();
    }
}
