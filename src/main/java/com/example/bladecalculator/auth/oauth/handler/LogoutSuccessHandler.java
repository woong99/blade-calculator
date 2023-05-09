package com.example.bladecalculator.auth.oauth.handler;

import com.example.bladecalculator.entity.LoginLog;
import com.example.bladecalculator.repository.LoginLogRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private final LoginLogRepository loginLogRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        LoginLog loginLog = LoginLog.builder()
                .userId(authentication.getName())
                .tryFlag("LOGOUT")
                .tryAt(LocalDateTime.now())
                .tryIp(ip)
                .build();

        loginLogRepository.save(loginLog);

        response.sendRedirect("/login");
    }
}
