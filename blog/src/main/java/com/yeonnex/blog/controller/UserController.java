package com.yeonnex.blog.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeonnex.blog.model.KakaoProfile;
import com.yeonnex.blog.model.OAuthToken;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는 /js/**, /css/**, /image/** 허용

@Controller
public class UserController {

    // 이 키는 외부에 노출되면 안됨
    @Value("${yeonnex.key}")
    private String yeonnexKey;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired UserService userService;

    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(@RequestParam String code){ // 반환값이 @ResponseBody 이므로, 데이터를 리턴해주게 된다
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

        // Http 요청하고 응답받기
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class // 반환타입
        );

        // json 데이터를 편리하게 다루기 위해, 객체로 만들어주겠다.
        // 이를 위해, Gson, Json Simple, ObjectMapper 등의 라이브러리가 있는데, 여기서는 ObjectMapper 를 써보겠다
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
            oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        System.out.println("===액세스 토큰 출력===" + oAuthToken.getAccess_token());

        RestTemplate rt2 = new RestTemplate();


        // HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 전송할 데이터가 키-밸류 라는 것을 알려주는 역할

        // HttpHeader 와 HttpBody 를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

        // Http 요청하고 응답받기
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class // 반환타입
        );

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(response2.getBody(), KakaoProfile.class);
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        // User 오브젝트: username, password, email
        System.out.println("카카오 아이디: " + kakaoProfile.getId());
        System.out.println("카카오 이메일: " + kakaoProfile.getKakaoAccount().getEmail());

        System.out.println("블로그서버 유저네임: " + kakaoProfile.getKakaoAccount().getEmail()+ "_" + kakaoProfile.getId());
        System.out.println("블로그서버 이메일: " + kakaoProfile.getKakaoAccount().getEmail());

        UUID garbagePassword = UUID.randomUUID(); // 임시 패스워드 (쓰레기)
        System.out.println("블로그서버 패스워드: " + garbagePassword);

        // 이제 강제로 회원 가입을 시켜줄 것이다!
        User user = User.builder().userName(kakaoProfile.getKakaoAccount().getEmail()+ "_" + kakaoProfile.getId())
                        .email(kakaoProfile.getKakaoAccount().getEmail())
                                .password(garbagePassword.toString()).build();
        // 가입자 혹은 비가입자 체크해서 처리
        if(userService.회원찾기(user)){ // 이미 회원 가입이 되어있다면
            System.out.println("===이미 가입됨===");
            System.out.println(yeonnexKey);
            return "index";
        }

        // 처음 회원가입하는 사람이라면 회원가입 시킴
        userService.회원가입(user);

        // 로그인처리
        // 세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
       return "index";
    }

    @GetMapping("/user/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("user", id);
        return "user/updateForm";
    }
}
