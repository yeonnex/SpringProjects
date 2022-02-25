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