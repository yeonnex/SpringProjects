package com.yeonnex.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionController {

    /**
    // 이 함수는 모든 익셉션을 처리하는 함수이다.
    // 익셉션의 가장 부모인 Exception 이기 떄문!
    @ExceptionHandler(value=Exception.class)
    public String handleAllException(Exception e){
        return "<h1>" + "해당 요청을 처리할 수 없어요 😥" + "</h1>";
    }
    */

    // IllegalArgumentException 처리
    @ExceptionHandler(value=IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e){
        return "<h1>" + e.getMessage() + "</h1>";
    }

    // 위처럼 처리를 해주면 사용자에게 stack trace 리턴하지 않고
    // html 로 미리 지정한 에러 메시지를 예쁘게 반환한다,
    // 만약 다른 Exception 을 처리하고 싶다면 특정 익셉션 클래스로
    // 만들어진 함수를 위처럼 또 만들면 된다.


}
