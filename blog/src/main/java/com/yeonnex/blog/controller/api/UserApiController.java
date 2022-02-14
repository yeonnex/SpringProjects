package com.yeonnex.blog.controller.api;

import com.yeonnex.blog.dto.ResponseDto;
import com.yeonnex.blog.model.RoleType;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.service.UserService;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;

// 이 컨트롤러는 나중에 "앱"에도 쓸 수 있음!
@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody HashMap<String, String> user){

        // 실제로 DB에 insert 를 하고 리턴하면 된다!
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }

    @PutMapping("/auth/updateProc")
    public void update( @RequestBody User user ){ // 이제 User 받을 수 있다!
        System.out.println("==============");
        System.out.println(user.getUserName());
        System.out.println(user.getEmail());

    }

}
