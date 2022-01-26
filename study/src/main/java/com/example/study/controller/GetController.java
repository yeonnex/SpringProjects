package com.example.study.controller;


import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequest(@RequestParam String id, @RequestParam(name="password") String pwd){
        return "Hi getRequest" + id + pwd;
    }

    // localhost:8080/api/multiParameter?account=seoyeon&email=study@naver.com&page=10
    @GetMapping("/multiParameter")
    public String getMultiParam(@RequestParam String account, @RequestParam String email, @RequestParam int page){
        return account + email + page;
    }

    @GetMapping("/betterMultiParameter")
    public SearchParam getBetterMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getEmail());
        return searchParam; // 객체를 리턴하면 자동으로 json 형식으로 반환됨!
    }
}
