package com.yeonnex.blog.controller;


import com.yeonnex.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/**, /image/** 허용

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @GetMapping("/auth/kakao/callback")
    public @ResponseBody String kakaoCallback(@RequestParam String code){ // 반환값이 @ResponseBody 이므로, 데이터를 리턴해주게 된다
        System.out.println("========코드 출력=====");
        System.out.println(code);
        return "카카오 인증 완료"; // 코드값을 받았기 때문에 일단 "인증" 은 완료됨!
        // 이 인증된 코드를 통해, "엑세스 토큰을 받아야 한다". 이유는 카카오 리스소 서버에 등록된 개인정보를 응답받기 위해서이다. 개인정보에 접근하기 위해 토큰이 필요하다!
    }

    @GetMapping("/user/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("user", id);
        return "user/updateForm";
    }
}
