## Filter

> **Filter**란, Web Application에서 관리되는 영역으로써 Spring Boot Framework에서 Client 로부터 오는 요청/응답에 대해서 **최초/최종 단계의 위치에 존재**하며, 이를 통해서 요청/응답의 정보를 변경하거나, **Spring에 의해서 데이터가 변환되기 전**의 순수한 Client의 요청/응답 값을 확인할 수 있다.

- **유일하게  ServletRequest, ServletResponse 의 객체를 변환**할 수 있다
- 주로 SpringFramework에서는 request / response 의 **Logging 용도**로 활용하거나, **인증과 관련된 Logic** 들은 해당 Filter에서 처리한다
  - 예를 들어, 어드민 같은 곳에서 이곳에 대한 모든요청(어떤 사용자가 들어왔고, 어떤 것을 요청했는지)에 대한 기록을 찍는다고 할 때, 뒷단의 AOP는 이미 객체로 매핑이 되었기 때문에 정말 순수한 내용이 아니다. 가장 앞단에서 순수한 request body 를 기록할 수 있다
  - 뒷단의 Interceptor를 타기도 전에, 아예 앞단에서 세션이 있는지 없는지 등의 처리가 가능하다

- 이를 선 후 처리함으로써, **Service business logic과 분리**시킨다

## 전체적인 구성도

![image-20220121142146255](https://raw.githubusercontent.com/yeonnex/image-server/main/img/image-20220121142146255.png)

1. Request가 들어온다
2. 제일 앞단에는 Filter가 있다
3. DispatcherServlet을 거친다
4. Interceptor 를 만나게 된다
5. AOP가 동작한다

> Filter - Interceptor - AOP의 순으로 동작!
