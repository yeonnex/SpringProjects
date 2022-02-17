package com.yeonnex.blog.controller;


import com.yeonnex.blog.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
        System.out.println("=====코드 출력=====" + code);
        // RestTemplate 라는 라이브러리를 사용하면, http 요청이 매우 쉬워진다.(이외에도 Retrofit2, OkHttp 등이 있음)
        // POST 방식으로 key=value 데이터를 요청(카카오쪽으로)
        RestTemplate rt = new RestTemplate();


        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 전송할 데이터가 키-밸류 라는 것을 알려주는 역할

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "37514041de77dcefe91c8b81178d8d57");
        params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        // HttpHeader 와 HttpBody 를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Http 요청하기
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class // 반환타입
        );
        return "카카오 토큰 요청 완료 : " + response; // 코드값을 받았기 때문에 일단 "인증" 은 완료됨!
        // 이 인증된 코드를 통해, "엑세스 토큰을 받아야 한다". 이유는 카카오 리스소 서버에 등록된 개인정보를 응답받기 위해서이다. 개인정보에 접근하기 위해 토큰이 필요하다!
    }

    @GetMapping("/user/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("user", id);
        return "user/updateForm";
    }
}
