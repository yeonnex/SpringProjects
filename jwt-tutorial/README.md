entity 패키지에 `User`클래스와 `Authority` 클래스를 생성하였다.

```java
@Entity
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password",length = 100)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "activated")
    private Boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;


}

```

```java
@Entity
public class Authority {
    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;
}
```

![image-20220227185743001](https://raw.githubusercontent.com/yeonnex/image-server/main/img/image-20220227185743001.png)

@ManyToMany, @JoinTable 은 User 객체와 Authority 객체의 다대다 관계를 일대다, 다대일 관계의 조인 테이블로 정의했다는 뜻이다.



> **Hibernate and data.sql**
> By default, data.sql scripts are now run before Hibernate is initialized. This aligns the behavior of basic script-based initialization with that of Flyway and Liquibase. If you want to use data.sql to populate a schema created by Hibernate, set spring.jpa.defer-datasource-initialization to true. While mixing database initialization technologies is not recommended, this will also allow you to use a schema.sql script to build upon a Hibernate-created schema before it’s populated via data.sql.

이제 data.sql 스크립트는 hibernate가 초기화되기 전에 실행되며 hibernate에 의해 생성된 스키마에 데이터를 넣기 위해 data.sql을 사용하고 싶으면 spring.jpa.defer-datasource-initialization 이 값을 true로 하라고 되어있다.

**(단, 데이터 데이터 초기화 기술을 섞는 것을 추천하지는 않는다고 되어있다.)**

스프링부트 2.5 릴리즈 노트: https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.5-Release-Notes#hibernate-and-datasql