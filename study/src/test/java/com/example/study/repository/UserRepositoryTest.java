package com.example.study.repository;

import com.example.study.StudyApplication;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.study.StudyApplicationTests;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection (DI)
    @Autowired UserRepository userRepository;

    @Test
    public void create(){
        // String sql = "insert into user (%s, %s, %d) values (account, email, age);
        User user = new User();
        // user.setId(); DB에서 Auto Increment 될 것이기 때문
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-333-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user);
        System.out.println("new User: " + newUser);
    }

    @Test
    public void read(){
        List<User> list = userRepository.findAll();
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            System.out.println("user: " + selectUser);
        });
        System.out.println(list);
    }

    public void update(){

    }
    public void delete(){

    }
}
