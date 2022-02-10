package com.yeonnex.blog.controller.api;

import com.yeonnex.blog.dto.ResponseDto;
import com.yeonnex.blog.model.RoleType;
import com.yeonnex.blog.model.User;
import com.yeonnex.blog.service.UserService;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

// 이 컨트롤러는 나중에 "앱"에도 쓸 수 있음!
@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody HashMap<String, Object> param){
        System.out.println(param);
        String name = (String) param.get("username");
        String email = (String) param.get("email");
        String password = (String) param.get("password");
        User user = new User();
        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(RoleType.USER);

        System.out.println("User Api 호출됨");

        // 실제로 DB에 insert 를 하고 리턴하면 된다!
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody Map<String, String> param){
        System.out.println("===== USER PRINT =====");
        String name = (String)param.get("username");
        String password = (String)param.get("password");

        User user = new User();
        user.setUserName(name);
        user.setPassword(password);

        User principal = userService.로그인(user); // principal (접근주체)
        System.out.println("principal 출력");
        System.out.println(principal);

        if(principal != null){
            session.setAttribute("principal", principal); // 세션이 만들어짐!
            System.out.println("세션이 생성됨");
        }else{
            return new ResponseDto<Integer>(HttpStatus.BAD_REQUEST, -1);
        }
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }
}
