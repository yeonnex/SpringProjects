package com.example.securitybasic.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 얘를 활성화하면 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됩니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 이렇게 Bean 으로 등록하면 해당 메서드의 리턴되는 오브젝트를 IoC 로 등록해준다다
   @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // 로그인 한 사람만 들어올 수 있음

                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") // 로그인 && 롤이 둘 중 하나인 사람만 들어올 수 있음
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 로그인 && 롤이 admin인 사람만 들어올 수 있음

                .anyRequest().permitAll() // 다른 요청들은 모두 허용하겠슴!
                .and()
                .formLogin()
                .loginPage("/loginForm");

    }
}
