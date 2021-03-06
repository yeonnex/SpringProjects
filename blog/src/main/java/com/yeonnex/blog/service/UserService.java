package com.yeonnex.blog.service;

import com.yeonnex.blog.model.RoleType;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

    private final BCryptPasswordEncoder encoder;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.encoder = bCryptPasswordEncoder;
    }

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user){
        String name = user.getUserName();
        String email = user.getEmail();
        String password = user.getPassword();
        System.out.println("========");
        System.out.println(name + email + password);
        String hashedPWD = encoder.encode(password);

        User newUser = new User();
        newUser.setUserName(name);
        newUser.setEmail(email);
        newUser.setPassword(hashedPWD);
        newUser.setRole(RoleType.USER);
        newUser.setOauth(user.getOauth());
        userRepository.save(newUser);
    }

    @Transactional
    public void 회원수정(User user){
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        // select 를 해서 User 오브젝트를 DB 로부터 가져오는 이유는 영속화를 하기 위해서!!
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update 문을 날려주기 때문
        User persistence = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원찾기 실패");
        });

        // Validate 체크. 즉 oauth 에 값이 없는 사람만 이메일과 패스워드 수정할 수 있음
        if(persistence.getOauth() == null || persistence.getOauth().equals("")){
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistence.setPassword(encPassword);
            persistence.setEmail(user.getEmail());
        }

        // 세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 회원수정 함수 종료 시 == 서비스 종료 시 == 트랜잭션 종료 == commit 이 자동으로 된다
        // 영속화된 persistence 객체의 변화가 감지되면 더티체킹이 되어 변화된 것들에 대해 update 문을 날려줌.
    }

    @Transactional
    public boolean 회원찾기(User user){
        if (userRepository.findByUserName(user.getUserName()).isPresent()){
            System.out.println("회원존재!");
            return true;
        }
        System.out.println("존재하지않음!");

        return false;
    }

}
