package com.example.securitybasic.config.oauth;

import com.example.securitybasic.config.auth.PrincipalDetails;
import com.example.securitybasic.model.User;
import com.example.securitybasic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // 여기서 후처리!
    // 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration: " + userRequest.getClientRegistration()); // registrationId 로 어떤 OAuth 로 회원가입했는지 확인 가능(google, facebook..)
        System.out.println("getAccessToken: " + userRequest.getAccessToken().getTokenValue());
        /**
         * 구글로그인 버튼 클릭 -> 구글로그인창 -> 로그인 완료 -> code 를 리턴(OAuth-Clinet 라이브러리가 받아줌
         * -> code 를 통해 Access Token 요청
         *
         * => 여기까지가 userRequest 정보
         * 이 정보로 회원 프로필을 받아야하는데, 그때 사용하는 함수가 loadUser 함수이다. 이 함수호출을 통해 회원 프로필을 받을 수 있음
         * */
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes: " + oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
        String providerId = oAuth2User.getAttribute("sub");
        String email = oAuth2User.getAttribute("email");
        String username = provider + "_" + providerId;
        String password = bCryptPasswordEncoder.encode("ticktok");
        String role = "ROLE_USER";

        User userEntity = userRepository.findByUsername(username);
        if(userEntity == null){
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .provider(provider)
                    .providerId(providerId)
                    .role(role)
                    .build();
            userRepository.save(userEntity);
        }else{
            System.out.println("로그인을 이미 한적이 있습니다");
        }
        // 회원가입을 강제로 진행해볼 예정
       return new PrincipalDetails(userEntity, oAuth2User.getAttributes()); // 이 정보로 "강제회원가입"을 시킬 것임
    }
}
