package com.example.securitybasic.controller;

import com.example.securitybasic.config.auth.PrincipalDetails;
import com.example.securitybasic.model.User;
import com.example.securitybasic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // View 를 리턴하겠다!!
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication){
        System.out.println("/test/login =============");
        System.out.println("authentication: " + authentication.getPrincipal());
        // authentication: com.example.securitybasic.config.auth.PrincipalDetails@410fa756

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal(); // 리턴 타입이 Object 이기 때문에 다운캐스팅
        System.out.println("authentication user: " + principalDetails.getUser());
        // authentication user: User(id=1, username=yks, password=$2a$10$6m9m5SnKKbw904bL//pMOOwiIeAIjjwuy/cgZxnM8KSur/tq6W.ly, email=hepi@naver.com, role=ROLE_USER, provider=null, providerId=null, createDate=2022-02-23 10:43:23.0)
        return "세션정보 확인하기";
    }

    @GetMapping({"", "/"})
    public String index(){
        // mustache 기본 폴더 src/main/resources/
        // 뷰리졸버 설정: templates (prefix), .mustache (suffix) 생략 가능
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(User user){
        System.out.println(user);
        user.setRole("ROLE_USER");
        String pwd = user.getPassword();
        user.setPassword(encoder.encode(pwd));
        userRepository.save(user); // 회원가입 잘 됨. 비밀번호 : 1234 => 시큐리티로 로그인을 할 수 없음. 이유는 패스워드가 암호화가 안되었기 때문
        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "데이터 정보";
    }
}
