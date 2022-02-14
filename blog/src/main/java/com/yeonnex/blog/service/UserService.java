package com.yeonnex.blog.service;


import com.yeonnex.blog.model.RoleType;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;


// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user){
        String name = user.getUserName();
        String email = user.getEmail();
        String password = user.getPassword();
        System.out.println("========");
        System.out.println(name + email + password);
        String hashedPWD = bCryptPasswordEncoder.encode(password);

        User newUser = new User();
        newUser.setUserName(name);
        newUser.setEmail(email);
        newUser.setPassword(hashedPWD);
        newUser.setRole(RoleType.USER);

        userRepository.save(newUser);
    }

    @Transactional
    public void 회원수정(User user, int id){

        Optional<User> selectedUser = userRepository.findById(id);
        selectedUser.ifPresent((user1)->{
            user1.setUserName(user1.getUserName());
            user1.setEmail(user1.getEmail());
            user1.setPassword(bCryptPasswordEncoder.encode(user1.getPassword())); // 수정시에도 비밀번호 해쉬화
            // 해당 함수 종료시(Service 가 종료될 때) 트랜잭션이 종료된다. 이때 더티체킹 - 자동 업데이트가 됨. db flush
//            boardRepository.save(board1); 이런거 안해줘도 됨
        });

    }

}
