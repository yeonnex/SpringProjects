package com.example.jwt.config.auth;

import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhost:8080/login 요청이 올 때 이 서비스가 동작한다.
// /login 주소를 요청할 때 동작하는 이유는 스프링 시큐리티의 기본 요청 주소가
// /login 이기 때문이다

// 근데 동작을 안할텐데, 이유는 앞서 시큐리티 설정을 하면서 폼로그인을 disable 하면서
// 자동으로 /login 주소가 비활성화되었기 때문이다.
// ... 직접 내가 필터를 만들어주겠다!!!
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService 의 loadUserByUsername 함수 동작!");
        User userEntity = userRepository.findByUsername(username);
        return new PrincipalDetails(userEntity);
    }
}
