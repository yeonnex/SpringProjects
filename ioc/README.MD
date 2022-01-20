DI를 사용해 내가(개발자가) 직접 객체를 생성(new)하여 주입했었다.
```java
IEncoder encoder = new Encoder(new Base64Encoder());
String result = encoder.encode(url);
```
스프링에서는, 스프링 컨테이너가 객체의 생성 등 모든 생명주기를 관리하기 때문에, 이 프로젝트에서는 객체를 스프링 컨테이너가 관리할 수 있게 바꿔본다.

객체로 관리해달라는 요청은, 클래스 위에 `@Component`라는 어노테이션을 통해 가능하다.
"이 클래스는 스프링 너가 Bean으로 만들어서 관리해줘!"

`@Component`를 통해 컴포넌트로 등록을 하면,스프링이 실행이 될때 이 어노테이션이 붙은 클래스를 찾아서 직접 객체를 싱글톤으로 만들어
스프링 컨테이너에서 관리하게 된다.

> 그럼 이걸 어떻게 꺼내 쓰지?


> SpringApplicationContext 를 통해 객체를 가져 올 수 있다!

> SpringApplicationContext, 즉 직접 스프링 컨테이너에 접근해서 가져오기 위해서는 접근하는 클래스 코드가 필요하다

```java
// ApplicationContextProvider.java
public class ApplicationContextProvider {

}
```
ApplicationContextAware를 상속받는다.
```java
public class ApplicationContextProvider implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // ApplicationContext 는 스프링이 주입해줄 것이다
    }
}
```
ApplicationContextProvider.java 완성
```java
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext(){
        return context;
    }
}
```

실험해보자
```java
@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IocApplication.class, args);
    }
    ApplicationContext context = ApplicationContextProvider.getContext();

    // 등록한 빈을 찾는 방법 (이름으로 찾거나, 클래스로 찾거나)
    Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);

    Encoder encoder = new Encoder(base64Encoder);

    // 실험해보자!
    String url = "www.naver.com/books/it?page=10&name=spring-boot";
    String result = encoder.encode(url);
}
```
Encoder까지 스프링에서 관리하게 하려면?
Encoder도 @Component를 붙여주면된다!

```java
Encoder encoder = context.getBean(Encoder.class);

// 실험해보자!
String url = "www.naver.com/books/it?page=10&name=spring-boot";
String result = encoder.encode(url);
```
모든 객체를 스프링에서 관리하게 되었다. 더이상 new하는 코드를 찾아볼 수 없다!