package com.example.jwttutorial.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // HttpServletRequest 를 사용하는 요청들에 대한 접근제한을 설정하겠다
                .antMatchers("/api/hello").permitAll() // /api/hello 에 대한 요청은 인증없이 접근을 허용하겠다
                .anyRequest().authenticated(); // 나머지 요청들은 모두 인증을 받아야 한다.

    }

    /**
     * h2-console 하위 모든 요청들과 파비콘 관련 요청은 Spring Security 로직을
     * 수행하지 않도록 configure 메서도를 오버라이드하자
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        "/h2-console/**",
                        "/favicon.ico"
                );
    }
}
