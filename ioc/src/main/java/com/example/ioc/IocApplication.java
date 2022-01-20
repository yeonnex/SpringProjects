package com.example.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IocApplication.class, args);
    }
    ApplicationContext context = ApplicationContextProvider.getContext();

    // 등록한 빈을 찾는 방법 (이름으로 찾거나, 클래스로 찾거나)
    Encoder encoder = context.getBean(Encoder.class);

    // 실험해보자!
    String url = "www.naver.com/books/it?page=10&name=spring-boot";
    String result = encoder.encode(url);
}
