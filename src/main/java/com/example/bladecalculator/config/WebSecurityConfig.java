package com.example.bladecalculator.config;

import com.example.bladecalculator.auth.oauth.PrincipalOAuth2UserService;
import com.example.bladecalculator.auth.oauth.handler.LogoutSuccessHandler;
import com.example.bladecalculator.auth.oauth.handler.OAuth2FailureHandler;
import com.example.bladecalculator.auth.oauth.handler.OAuth2SuccessHandler;
import com.example.bladecalculator.entity.Authority;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final PrincipalOAuth2UserService principalOAuth2UserService;

    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private final OAuth2FailureHandler oAuth2FailureHandler;

    private final LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
//        requestCache.setMatchingRequestParameterName(null);
        http
                .csrf().disable()
                .securityMatcher("/**")
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/vendor/**", "/css/**", "/js/**",
                                "/img/**", "/login", "/logout", "/").permitAll()
                )
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/calculator/**")
                                .hasAnyRole(Authority.USER.name(), Authority.ADMIN.name())
                )
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/_admin/**")
                                .hasAuthority(Authority.ADMIN.getAuthority())
                )
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler(((request, response, authentication) -> {
                    HttpSession httpSession = request.getSession();
                    if (httpSession != null) {
                        httpSession.invalidate();
                    }
                }))
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
//                .requestCache(cache -> cache.requestCache(requestCache))
                .oauth2Login()
                .loginPage("/login")
                .userInfoEndpoint()
                .userService(principalOAuth2UserService)
                .and()
                .successHandler(oAuth2SuccessHandler)
                .failureHandler(oAuth2FailureHandler);

        return http.build();
    }
}
