
### 왜 JWT 를 쓰는건가?

유지에 부담이 크고, 확장이 어려운 세션 방식의 인가의 한계를 극복해보기 위해서이다. 즉 **부담없이 인가를 구현**하기 위해 고안된 것이 jwt, 토큰 방식이다.
## JWT 를 적용하기 전에 알아야하는것

### Filter

시큐리티는 우선 **필터체인 방식으로 동작**한다. 기존의 시큐리티 프로젝트와는 다르게, formLogin과 httpBasic 을 disable한다.

- formLogin 을 사용하지 않는 이유

form 으로 로그인 하는 경우는 javascript 로 서버에 id, pw를 요청한다. 이때, javascript 로 요청할 경우 서버에 클라이언트가 갖고있는 쿠키를 전송하지 못하는데, 그 이유는 대부분의 서버는 httpOnly 값이 true 로 설정되어 있기 때문이다. 만약 formLogin을 허용한다면 javascript로 요청시 보안상 좋지 않기 때문에 formLogin 을 사용하지 않는다.

- httpBasic 을 사용하지 않고 Bearer 방식을 사용하는 이유

httpBasic 방식은 요청헤더의 Authorization key에 id와 pw 를 그대로 노출한다. 이 경우 상당히 위험! Bearer 방식은 토큰을 Authorization key 값으로 요청하기에, 전자보다 훨씬 보안상 좋다. 이 토큰이 바로 지금 적용해볼 Json Web Token.







jwt 토큰을 생성하기 위해 직접 Base64로 인코딩할 수도 있으나, 이걸 해주는 라이브러리가 역시나 존재했다.

mvnrepository 에서 Java JWT 라이브러리를 사용하자.

이번 프로젝트에서는, 회원가입을 먼저 하고, 로그인을 하면 jwt 토큰을 만들어 돌려주는 서버를 만들어볼것임.

![in-depth-introduction-jwt-token-based-authentication](https://bezkoder.com/wp-content/uploads/2019/10/in-depth-introduction-jwt-token-based-authentication.png)

https://www.bezkoder.com/spring-boot-react-jwt-auth/

쿠키안의 세션 ID가 들어있는 세션 기반 인증에 비해, JWT(토큰 기반 인증) 은 세션 기반 인증의 단점을 해소할 수 있다.

## Flow for User Registration and User Login

![spring-boot-react-authentication-jwt-example-flow](https://bezkoder.com/wp-content/uploads/2020/03/spring-boot-react-authentication-jwt-example-flow.png)

### There are 2 endpoints for authentication:

- `api/auth/signup` for user Registration
- `api/auth/signin` for user Login

## Spring Boot & Spring Security for Back-end

![spring-boot-authentication-spring-security-architecture](https://bezkoder.com/wp-content/uploads/2019/10/spring-boot-authentication-spring-security-architecture.png)