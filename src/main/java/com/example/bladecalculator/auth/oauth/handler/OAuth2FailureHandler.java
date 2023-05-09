package com.example.bladecalculator.auth.oauth.handler;

import com.example.bladecalculator.entity.LoginLog;
import com.example.bladecalculator.repository.LoginLogRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2FailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final LoginLogRepository loginLogRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        String userId = request.getUserPrincipal().getName() == null ? "" : request.getUserPrincipal().getName();
        LoginLog loginLog = LoginLog.builder()
                .userId(userId)
                .tryFlag("FAIL")
                .tryAt(LocalDateTime.now())
                .tryIp(ip)
                .build();

        loginLogRepository.save(loginLog);

        response.sendRedirect("/");
    }
}
