package com.yeonnex.blog.test;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {

    @Test
    public void 해쉬_암호화(){
        String hashed = new BCryptPasswordEncoder().encode("wkdtjdus123!");
        System.out.println(hashed);
    }
}
