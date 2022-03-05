package com.example.jwt.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.config.auth.PrincipalDetails;
import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 얘는 언제 동작함?
 *
 * 시큐리티가 여러 filter 들을 가지고 있는데, 그 필터중에 BasicAuthenticationFilter 라는 것이 있음.
 * 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음.
 * 만약에 권한이 인증이 필요한 주소가 아니라면 이 필터를 타지 않음.
 */

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        System.out.println(JwtProperties.SECRET);
    }

    // 인증이나 권한이 필요한 주소 요청이 있을 때 해당 필터를 타게 될 것!
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소요청이 됨");
        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtHeader :" + jwtHeader);

        // header 가 있는지 확인인
       if (jwtHeader == null || jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        // JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
       String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
       String username = JWT.require(Algorithm.HMAC512("cos")).build().verify(jwtToken).getClaim("username").asString();

       // 서명이 정상적으로 됨
       if(username!=null){
           User userEntity = userRepository.findByUsername(username);

           // Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
           PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
           Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

           // 강제로 시큐리티 세션에 접근하여 Authentication 객체를 저장장
          SecurityContextHolder.getContext().setAuthentication(authentication);
       }
        chain.doFilter(request, response);

    }
}

