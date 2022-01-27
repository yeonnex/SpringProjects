## Entity

JPA에서는 테이블을 자동으로 생성해주는 기능 존재. 그러나 나는 이미 MySQL DB에다가 케이블과 컬럼을 미리 생성하였음

DB table == JPA Entity

| Annotation       | 용도                           |
| ---------------- | ------------------------------ |
| @Entity          | 해당 Class가 Entity임을 명시   |
| @Table           | 실제 DB 테이블의 이름을 명시   |
| @Id              | Index primary key 를 명시      |
| @Column          | 실제 DB Column 의 이름을 명시  |
| @Generated Value | Primary key 식별키의 전략 설정 |

@Table과 @Id, @Column 의 경우 DB 에서 설정한 값과 같다면 굳이 명시하지 않아도 JPA에서 자동으로 매핑시켜준다.

 