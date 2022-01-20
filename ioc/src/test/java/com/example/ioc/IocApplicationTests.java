package com.example.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IocApplicationTests {

    @Test
    void contextLoads() {
        String message = "https://www.naver.com/";
        IEncoder encoder = new Encoder(new Base64Encoder());
        
        String result = encoder.encode(message);
        System.out.println(result);

    }

}
