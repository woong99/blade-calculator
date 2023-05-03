package com.example.bladecalculator.config;

import com.example.bladecalculator.auth.oauth.PrincipalOAuth2UserService;
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
                        auth -> auth.requestMatchers("/calculator/**").hasAuthority(Authority.USER.getAuthority())
                )
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler(((request, response, authentication) -> {
                    HttpSession httpSession = request.getSession();
                    if (httpSession != null) {
                        httpSession.invalidate();
                    }
                }))
                .logoutSuccessHandler(((request, response, authentication) -> response.sendRedirect("/login")))
                .and()
//                .requestCache(cache -> cache.requestCache(requestCache))
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .userInfoEndpoint()
                .userService(principalOAuth2UserService);
        return http.build();
    }
}
