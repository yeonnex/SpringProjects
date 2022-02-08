package com.yeonnex.blog.controller.api;

import com.yeonnex.blog.dto.ResponseDto;
import com.yeonnex.blog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 이 컨트롤러는 나중에 "앱"에도 쓸 수 있음!
@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("User Api 호출됨");
        return new ResponseDto<Integer>(HttpStatus.OK,1);
    }
}
