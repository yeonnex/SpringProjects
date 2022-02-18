package com.yeonnex.blog.controller.api;

import com.yeonnex.blog.config.auth.PrincipalDetail;
import com.yeonnex.blog.dto.ResponseDto;
import com.yeonnex.blog.model.RoleType;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.service.UserService;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
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
    public ResponseDto<Integer> update(@RequestBody User user){ // json 데이터를 받으려면 반드시 @RequestBody 를 써주어야 함.
        System.out.println("업데이트 시작");                      // key=value(mime 타입이 x-www-form-urlencoded  를 받고싶다면
        userService.회원수정(user);

        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }
}
