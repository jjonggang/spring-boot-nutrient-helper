package com.example.nutritionhelper.config;

import com.example.nutritionhelper.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http 시큐리티 빌더
        http
                .cors()
                .and()// cors는 따로 설정했으므로 기본으로만 설정
                .csrf()
                .disable()
                .httpBasic()// 토큰을 사용하기 때문에 basic 인증 disable
                .disable()
                .sessionManagement() // 세션 기반이 아님을 선언한다.
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/api/v1/no-login/**", "/api/v1/auth/**").permitAll()
                .anyRequest() // /와 /auth/** 이외의 모든 경로는 인증 해야됨
                .authenticated();
        // filter 등록
        // 매 요청마다
        // CorsFilter 실행한 후에
        // jwtAuthenticationFilter 실행한다.
        http.addFilterAfter(
                jwtAuthenticationFilter, // jwtAuthenticationFilter를
                CorsFilter.class // CorsFilter 클래스의 동작이 끝나면 실행하라.
        );
    }
}