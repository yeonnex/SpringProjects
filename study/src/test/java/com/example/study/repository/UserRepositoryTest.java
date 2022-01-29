package com.example.study.repository;

import com.example.study.StudyApplication;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.study.StudyApplicationTests;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.junit.jupiter.api.Assertions.*;
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
        user.setPassword("1234");
        user.setStatus("Registered");
        user.setEmail("TestUser09@gmail.com");
        user.setPhoneNumber("010-999-9876");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");
        User newUser = userRepository.save(user);
        System.out.println("new User: " + newUser);
    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-999-9876");
        assertNotNull(user);
    }

    @Test
    @Transactional
    public void update(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser -> {
            selectUser.setAccount("yeonnex");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("장서연");

            userRepository.save(selectUser);
        });

    }

    @Test
    @Transactional
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });
        Optional<User> deletedUser = userRepository.findById(1L);
        if(deletedUser.isPresent()){
            System.out.println("삭제되지 않음. 데이터 존재" + deletedUser.get());
        }else{
            System.out.println("삭제됨. 데이터 없음");
        }

    }
}
