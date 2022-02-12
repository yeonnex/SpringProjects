package com.yeonnex.blog.service;


import com.yeonnex.blog.model.RoleType;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;


// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(HashMap<String, String> user){
        String name = (String) user.get("username");
        String email = (String) user.get("email");
        String password = (String) user.get("password");

        String hashedPWD = bCryptPasswordEncoder.encode(password);

        User newUser = new User();
        newUser.setUserName(name);
        newUser.setEmail(email);
        newUser.setPassword(hashedPWD);
        newUser.setRole(RoleType.USER);

        userRepository.save(newUser);
    }

}
