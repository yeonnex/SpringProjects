## Exception 처리

> Web Applicaton의 입장에서, 에러가 났을 때 내려줄 수 있는 방법은 많지 않다

1. 에러 페이지
2. 4XX Error 또는 5XX Error
3. Client가 200 밖에 처리를 하지 못할 때는 200을 내려주고 별도의 에러 Message 전달

웹 애플리케이션 입장에서, 이러한 에러는 한곳에서 모아서 처리하는 것이 좋다. try-catch 로 묶는 것보다, 자연스럽게 throw를 시키거나, 전체 애플리케이션에 대해서 한번에 Exception을 처리하는 것이 좋은데, 이러한 것들이 스프링에 이미 있다!

## Exeption 처리의 기본적인 두가지 방식

| 어노테이션        | 설명                                                   |
| ----------------- | ------------------------------------------------------ |
| @ControllerAdvice | Global 예외 처리 및 특정 pacakge / Controller 예외처리 |
| @ExceptionHandler | 특정 Controller 의 예외처리                            |

