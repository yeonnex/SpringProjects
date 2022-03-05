package com.example.jwt.config.jwt;

public interface JwtProperties {
    String SECRET = "cos"; // 우리 서버만 알고있는 비밀값
    int EXPIRATION_TIME = 6000*10; // 10분
    String TOKEN_PREFIX = "Bearer: ";
    String HEADER_STRING = "Authorization";
}
