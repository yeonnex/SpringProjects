package com.example.exception.advice;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    // 스프링 웹앱에 일어나는 모든 익셉션을 다 잡겠다
    // 익셉션 터지면 무조건 여기 코드 실행
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e){
        System.out.println(e.getClass().getName());
        System.out.println("==== 익셉션 메시지 출력 ===");
        System.out.println(e.getLocalizedMessage());
        System.out.println("==== 익셉션 메시지 출력 ===");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    // 특정 메소드의 예외를 잡고 싶을 때
   @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
