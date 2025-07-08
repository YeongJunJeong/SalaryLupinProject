//Bean(부품) 등록 하기
package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration  //이 클래스가 설정파일 임을 알림
@EnableWebSecurity  //Spring Security를 활성화
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 보안 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // (1) CORS 설정 활성화
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // (2) CSRF 보호 비활성화 (Stateless한 REST API에서는 보통 불필요)
                .csrf(csrf -> csrf.disable())

                // (3) 세션 관리 정책 설정: 세션을 사용하지 않음 (Stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // (4) HTTP 요청에 대한 접근 권한 설정
                .authorizeHttpRequests(authz -> authz
                        // '/api/users/**' 경로 (회원가입, 로그인)는 인증 없이 누구나 접근 허용
                        .requestMatchers("/api/users/**").permitAll()
                        // 그 외의 모든 요청은 인증된 사용자만 접근 가능
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    // 3. CORS(Cross-Origin Resource Sharing) 설정
    // Vue.js 개발 서버(예: http://localhost:8080)와 Spring Boot 서버(예: http://localhost:8080)의
    // 포트가 다르기 때문에 브라우저에서 보안상 요청을 차단함. 이를 허용해주기 위한 설정.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:5173"); // Vue 개발 서버 주소
        configuration.addAllowedMethod("*"); // 모든 HTTP 메소드 허용 (GET, POST, PUT, DELETE 등)
        configuration.addAllowedHeader("*"); // 모든 헤더 허용
        configuration.setAllowCredentials(true); // 자격 증명(쿠키 등) 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 위 설정 적용
        return source;
    }
}
