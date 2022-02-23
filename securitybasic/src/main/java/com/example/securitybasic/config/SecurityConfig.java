package com.example.securitybasic.config;

/**
 * 1. 코드받기(인증), 2. 액세스토큰(권한)
 * 3. 사용자프로필 정보를 가져오고, 4-1. 그 정보(이메일, 전화번호, 이름, 아이디)를 토대로 회원가입을 자동으로 진행시키기도 함
 * 4-2. 정보가 부족한경우, ex)쇼핑몰 -> (집주소), 백화점몰 -> (vip 등급, 일반등급)
 */

import com.example.securitybasic.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 얘를 활성화하면 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됩니다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize, postAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    // 이렇게 Bean 으로 등록하면 해당 메서드의 리턴되는 오브젝트를 IoC 로 등록해준다다
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() // 로그인 한 사람만 들어올 수 있음. 인증만 되면 들어갈 수 있는 주소!

                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") // 로그인 && 롤이 둘 중 하나인 사람만 들어올 수 있음
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 로그인 && 롤이 admin인 사람만 들어올 수 있음

                .anyRequest().permitAll() // 다른 요청들은 모두 허용하겠슴!
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
                // 그래서, 직접 내가 컨트롤러에 /login 을 만들어주지 않아도 된다!
                .defaultSuccessUrl("/") // 로그인 성공시 메인 페이지로 가게하겠다.
                .and()
                .oauth2Login()
                .loginPage("/loginForm") // 구글 로그인이 완료된 후의 후처리가필요함
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
        /**
         * Tip. 구글 로그인이 완료되면, 코드를 받는 것이 아니라,
         * 액세스토큰 + 사용자 프로필 정보를 한방에 받는다.
         * 그래서 oauth client 라이브러리가 매우 편리하다는 것!
         */
    }
}
