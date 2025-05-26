package com.nn.nerdnest;

import com.nn.nerdnest.member.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화 방식을 BCryptPasswordEncoder로 지정
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보안 비활성화
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/nerdnest/**",
                                "/v3/api-docs/**",   // OpenAPI 문서
                                "/swagger-ui/**",    // Swagger UI 리소스
                                "/swagger-ui.html", // Swagger UI 페이지
                                "/api/members", // 회원가입
                                "/api/members/recovery/username", // 아이디 찾기
                                "/api/members/auth"// 로그인
                                // 메인화면
                        ).permitAll()// 인증 없이 접근 가능

                        .requestMatchers("/api/boards/**").hasRole("USER")//board 관련

                        .anyRequest().authenticated()// 다른 요청은 인증 필요
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
