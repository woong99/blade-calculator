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
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginLogRepository loginLogRepository;

    private final RequestCache requestCache = new HttpSessionRequestCache();


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        LoginLog loginLog = LoginLog.builder()
                .userId(authentication.getName())
                .tryFlag("SUCCESS")
                .tryAt(LocalDateTime.now())
                .tryIp(ip)
                .build();

        loginLogRepository.save(loginLog);
        log.info("로그인 성공 - {}", authentication.getName());

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest != null) {
            String returnUrl = savedRequest.getRedirectUrl();
            response.sendRedirect(returnUrl);
        } else {
            response.sendRedirect("/");
        }
    }

}
