package com.yeonnex.blog.config.auth;

import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class PrincipalDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;
    // 스프링이 로그인 요청을 가로챌 때, username, password 이 두개의 변수를 가로채는데
    // password 부분 처리는 알아서 함.
    // username 이 DB에 있는지만 확인하면 됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUserName(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("유저를 찾을 수 없음...");
                });
        return new PrincipalDetail(principal); // 시큐리티의 세션에 유저 정보가 저장이 됨.
    }
}
