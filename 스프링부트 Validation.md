> Java에서 null값에 대해 접근하려하면 null pointer exception이 발생한다. 이를 방지하기 위해 미리 검증하는 것을 Validation이라 한다.

단순하게는 아래와 같은 코드들이다.

```java
public void run(String account, String pw, int age){
    if (account == null || pw == null){
        return
    }
    if (age == 0) return;
   	
    // 정상 logic
}
```

파라미터가 엄청 많아진다면 어떡할까? 매우 불필요하고 소모적이며 반복적인 작업이 된다.

1. 검증해야 할 값이 많은 경우 코드의 길이가 길어진다
2. (구현에 따라 달라질 수 있지만) 서비스 로직과의 분리가 필요하다
3. 흩어져있는 경우 어디에서 검증을 하는지 알기 어려우며, 재사용의 한계가 있다

이를 위해 스프링에서는 어노테이션 기반으로 Validation 기능을 제공한다.

| 어노테이션 | 설명                        |
| ---------- | --------------------------- |
| @Size      | 문자열 길이 측정            |
| @NotNull   | null 불가                   |
| @NotEmpty  | null, ""불가                |
| @Pattern   | 정규식 적용                 |
| @Max       | 최대값                      |
| @Valid     | 해당 object validation 실행 |

그 외 등등...

1. gradle dependencies

   implementation("org.springframword.boot:spring-boot-starter-validation")

2. bean validation spec

   https://beanvalidation.org/2.0-jsr380/

3. 핸드폰번호 정규식

   "^\\\d{2,3}-\\\d{3,4}-\\\d{4}\\$"

## Custom Validation

1. AssertTrue / False 와 같은 method 지정을 통해서 Custom logic 적용 가능
2. ConstraintValidator 를 적용하여 재사용이 가능한 Custrom logic 적용 가능

