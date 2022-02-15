package com.yeonnex.blog.controller.api;

import com.yeonnex.blog.config.auth.PrincipalDetail;
import com.yeonnex.blog.dto.ResponseDto;
import com.yeonnex.blog.model.RoleType;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.service.UserService;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

// 이 컨트롤러는 나중에 "앱"에도 쓸 수 있음!
@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }


    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user, @AuthenticationPrincipal PrincipalDetail principal, HttpSession session){ // json 데이터를 받으려면 반드시 @RequestBody 를 써주어야 함.
        System.out.println("업데이트 시작");                      // key=value(mime 타입이 x-www-form-urlencoded  를 받고싶다면
        userService.회원수정(user);                              // @RequestBody 안적어도 됨
        // 여기서는 트랜잭션이 종료되기 때문에 DB 값은 변경이 됐음.
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 내가 "직접 세션값을 변경"해줄 것임.
        // 세션값을 변경하는 방법은 스프링 시큐리티가 어떻게 로그인이 되는지,
        // 로그인이 될 때 세션이 어떻게 만들어지는지에 대한 기본 개념이 필요함

        // 강제로 세션값을 바꿈
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }
}
