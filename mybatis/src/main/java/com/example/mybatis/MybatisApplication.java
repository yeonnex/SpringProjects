package com.example.mybatis;

import com.example.mybatis.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(MybatisApplication.class);

        // 8080 포트에 톰캣 띄우지 않고 스프링 동작시키는 방법
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
