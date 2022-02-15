package com.example.mybatis;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private int id;

    private String userName;

    private String password;

    private String email;

    private String role;

    private Timestamp createDate;
}

