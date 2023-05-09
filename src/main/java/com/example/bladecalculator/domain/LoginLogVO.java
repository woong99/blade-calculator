package com.example.bladecalculator.domain;

import com.example.bladecalculator.entity.LoginLog;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LoginLogVO {

    private String userId;

    private LocalDateTime tryAt;

    private String tryIp;

    private String tryFlag;

    public static LoginLogVO toLoginLogVO(LoginLog loginLog) {
        return LoginLogVO.builder()
                .userId(loginLog.getUserId())
                .tryAt(loginLog.getTryAt())
                .tryFlag(loginLog.getTryFlag())
                .tryIp(loginLog.getTryIp())
                .build();
    }
}
