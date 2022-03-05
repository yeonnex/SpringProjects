package com.example.jwt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyFilter3 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 다운 캐스팅
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        /**
         * 토큰을 만들었다고 가정...! ("cors" 라는 토큰) -> id, pw 가 정상적으로 들어와서 로그인이 완료되면 토큰을 만들어주고, 그걸 응답!
         *이 "cors"라는 토큰이 넘어오면, 인증이 되게 하고, 필터를 계속 타게 하겠다
         * 근데 만약 그게 아니라면, 더이상 필터를 못타게, 즉 컨트롤러에 진입조차 못하게 해보자
         */

        /**
         * 요청할때마다 header 에 Authorization 에 value 값으로 토큰을 가지고 오겠죠?
         * 그때 토큰이 내가 만든 토큰이 맞는지만 검증만 하면 됨 (RSA, HS256)
         */
        if(req.getMethod().equals("POST")){
            System.out.println("POST 요청됨");
            String headerAuth = req.getHeader("Authorization");
            System.out.println(headerAuth);
            System.out.println("필터1");
            if(headerAuth.equals("cors")){ // 토큰이 "cors" 일때만 필터를 타게하겠음!
                filterChain.doFilter(req,res); // 다운캐스팅한 애를 chain 에 넘겨주겠음!
            }else{
                PrintWriter out = res.getWriter();
                out.println("인증 안됨");
                System.out.println("인증안됨");
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
