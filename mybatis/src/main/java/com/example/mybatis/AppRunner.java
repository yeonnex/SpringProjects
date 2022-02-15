package com.example.mybatis;

import com.example.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    UserMapper userMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {/**
        System.out.println("=======================");
        List<User> users = userMapper.getAllUser();
        if(!CollectionUtils.isEmpty(users)){
            users.forEach(System.out::println);
        }
        System.out.println("======================="); **/
     User user = userMapper.getUserById(1);
     if(!Objects.isNull(user)){
         System.out.println(user);
     }
    }
}