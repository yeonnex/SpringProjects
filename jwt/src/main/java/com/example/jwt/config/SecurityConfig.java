package com.example.jwt.config;

import com.example.jwt.filter.MyFilter1;
import com.example.jwt.filter.MyFilter3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter; // 모든 요청이 이 필터를 타게 되어있음. 이게 걸려있는 한, cors 요청이 와도 다 허용됨!

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new MyFilter3(), BasicAuthenticationFilter.class); //근데 이런걸 굳이 시큐리티 필터에 넣어줄 필요 없지! 그냥 테스트용
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않겠다는 뜻. 즉 stateless 서버로 만들겠따!
                .and()
                .addFilter(corsFilter) // 이 필터를 설정함으로써 내 서버는 cors 정책에서 벗어날 수 있게 되었다. cross-origin 요청이 와도, 다 허용됨!
                .formLogin().disable() // 왜냐, jwt 서버니까 id,비번 폼 로그인을 하지 않음!
                .httpBasic().disable() // Authorization 에 http basic 방식을 쓰지 않고, http bearer 방식을 쓸 것이다
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
