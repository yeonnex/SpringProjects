package com.yeonnex.blog.config;

import com.yeonnex.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration // 빈등록 (IOC관리)
@EnableWebSecurity // 시큐리티 칠터가 등록이 된다
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean // IoC가 돼요!!
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder(); // 이걸 스프링이 관리하게됨 !! 필요할 떄마다 가져와서 쓰면 됨
    }

    // 시큐리티가 대신 로그인해주는데 password 가 가로채기를 하는데
    // 해당 password 가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    // 같은 해쉬를 암호화해서 DB에 있는 해쉬를 비교할 수 있음.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf 토큰 비활성화 (테스트 시 걸어두는 게 좋음)
                .authorizeRequests()
                    .antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
                    .permitAll()

                    .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로챈뒤 대신 로그인해준다
                .defaultSuccessUrl("/");
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**");
    }
}
