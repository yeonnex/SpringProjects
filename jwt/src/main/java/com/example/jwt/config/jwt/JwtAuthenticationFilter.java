package com.example.jwt.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 스프링 시큐리티에 UsernamePasswordAuthenticationFilter 가 있음
// 원래 기본적으로, /login 요청해서 username, password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 필터가 동작을 함.

// 이 필터를 동작시키는 법은, SecurityConfig.java 에 이 필터를 등록해주면 된다!
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수수
   @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       System.out.println("JwtAuthenticationFilter: 로그인 시도중");
       /**
        * 1. username, password 받아서
        *
        * 2. 정상인지 로그인 시도를 해보는 거예요. authenticationManager 로 로그인 시도를 하면!!
        *    PrincipalDetailsService 가 호출됩니다.
        *    그럼 loadByUsername 이 자동으로 호출됩니다.
        *
        * 3. PrincipalDetails 를 세션에 담고(권한 관리(user, manager, admin)가 안되어도 괜찮다면 "굳이" 세션에 담지 않아도 된다.)
        *
        * 4. JWT 토큰을 만들어서 응답해주면 됨.
        */



        return super.attemptAuthentication(request, response);
    }
}
