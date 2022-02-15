package com.example.mybatis.mapper;

import com.example.mybatis.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUser();
    User getUserById(int id);
}
