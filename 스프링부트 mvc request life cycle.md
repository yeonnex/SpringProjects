## 1. 스프링 mvc request life cycle

![img](https://blog.kakaocdn.net/dn/bbXVtH/btqELyiXfkR/jtmaCidXlKdKTkrrbhk2E0/img.png)

## 2. Filter 와 Interceptor는 실행시점이 다르다

`Filter` 는 WebAppliction에 등록 -> web.xml

`Interceptor` 는 Spring 컨텍스트에 등록

## 3.Filter 와  Interceptor의 차이

Interceptor 는 시큐리티가 나오기 전에 인증, 권한을 체크하는 훌륭한 도구였음. Interceptor는 **AOP**를 흉내낼 수 있다. handlerMethod 를 제공하기 때문에 **메서드의 전후처리가 가능**하다.

## 4. Security

Security 의 인증절차는 필터체인을 거쳐 dispatcherservlet 으로 가기 전에 적용된다. 그리고 Security 필터 체인을 통해 걸러진 request 요청을 Interceptor 를 이용해서 전후 처리할 수 있다.

## 5. Security 인증절차

Security 를 통한 로그인은 Authentication 객체의 모험(여행)이라고 생각하면 이해하기 쉽다.

![img](https://blog.kakaocdn.net/dn/cs4tgw/btqEIKq0DF4/Fm93FYLNM8fZKmSwFrEchK/img.png)

## 6. 스프링 시큐리티 설정 파일 참고

https://bamdule.tistory.com/53