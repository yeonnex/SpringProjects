package com.yeonnex.blog.service;


import com.yeonnex.blog.model.User;
import com.yeonnex.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user){
        userRepository.save(user);
    }

    @Transactional(readOnly = true) // Select 할 때 트렌잭션 시작. 서비스 종료시에 트랜잭션 종료(정합성 유지시킬 수 있음). 즉 여러번 select 하더라도 같은 데이터!
   public User 로그인(User user){
        return userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
    }
}
