package com.example.jwt.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않겠다는 뜻. 즉 stateless 서버로 만들겠따!
                .and()
                .formLogin().disable() // 왜냐, jwt 서버니까 id,비번 폼 로그인을 하지 않음!
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")

                .anyRequest().permitAll(); // 그 외 다른 요청들은 권한 없이 들어갈 수 있음!
        /**
         * 이렇게 설정함으로써, 폼태그로 로그인하지 않음
         * 그리고 기본적인 http 로그인 방식을 아예 쓰지 않는다
         * 세션을 만들지도않음
         *
         * 이렇게 설정하는 것이 jwt 설정의 기본본         */
    }
}
