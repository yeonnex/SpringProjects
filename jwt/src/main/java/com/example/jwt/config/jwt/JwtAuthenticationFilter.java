package com.example.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.config.auth.PrincipalDetails;
import com.example.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

/**
 * 안드로이드에서 로그인시도 할수도, 리액트에서 로그인시도 할 수도, 일반적인 웹 클라이언트에서 로그인 시도 할 수도.
 * 일반적인 웹 클라이언트라면, x-www-form-urlencoded 방식으로 로그인할 것이다.
 *
 * BUT, 최근 자바스크립트에서 json 으로 요청을 많이 하고, 안드로이드는 거의 대부분 json 요청!
 *
 */

// 스프링 시큐리티에 UsernamePasswordAuthenticationFilter 가 있음
// 원래 기본적으로, /login 요청해서 username, password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 필터가 동작을 함.

// 이 필터를 동작시키는 법은, SecurityConfig.java 에 이 필터를 등록해주면 된다!
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
   @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       System.out.println("JwtAuthenticationFilter: 로그인 시도중");
       try {
           // json 데이터 쉽게 파싱하기
           ObjectMapper om = new ObjectMapper();
           User user = om.readValue(request.getInputStream(), User.class);
           System.out.println(user); // User(id=null, username=ssar, password=1234, roles=null)
           UsernamePasswordAuthenticationToken authenticationToken =
                   new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

           // PrincipalDetailsService 의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication 이 리턴됨.
           // DB에 있는 username 과 password가 일치한다.
          Authentication authentication =
                   authenticationManager.authenticate(authenticationToken);

           PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
           System.out.println("로그인 완료됨: " + principalDetails.getUser().getUsername()); // 로그인이 정상적으로 되었다는 뜻

           // authentication 객체가 session 영역에 저장을 해야하고 그 방법이 return 해주면 됨.
           // 리턴의 이유는 권한 관리를 security 가 대신 해주기 때문에 편하려고 하는거임.
           // 굳이 JWT 토큰 사용하면서 세션을 만들 이유가 없음. 근데 단지 권한 처리 때문에 session 에 넣어줌.
           return authentication; // authentication 객체가 session 영역에 저장됨. => 로그인이 되었다는 뜻

       } catch (IOException e) {
           e.printStackTrace();
       }
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



        return null;
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행된다.
    // 여기서 JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면된다

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthenticaion 실행됨: 인증완료!!!");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // RSA 방식 아니고, Hash 암호화 방식
        // RSA 방식은 공개키와 개인키를 기반으로 돌아가고,
        // HMAC 은 시크릿 키가 있어야 함
       String jwtToken = JWT.create()
                .withSubject("cos토큰")
                .withExpiresAt(new Date(System.currentTimeMillis()+ (60000*10))) // 토큰이 만료되는 시간은 10분
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512("cos"));
        System.out.println("jwtToken 출력" + jwtToken);
       response.addHeader("Authorization", "Bearer "+jwtToken);
    }
}
