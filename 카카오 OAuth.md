### 카카오 api

클라이언트키: 37514041de77dcefe91c8b81178d8d57
카카오 로그인 요청 콜백주소: http://localhost:8000/auth/kakao/callback

웹서버주소: http://localhost:8000

카카오로부터 받을 정보: profile정보(필수), email(선택)
User 오브젝트: id(번호), username, password, email

### 로그인 요청주소 (GET)

`로그인 요청주소`: https://kauth.kakao.com/oauth/authorize?client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}&response_type=code

`https://kauth.kakao.com/oauth/authorize?client_id=37514041de77dcefe91c8b81178d8d57&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code`

### 토큰 발급 요청주소 (POST)  - http body에 데이터를 전달 (4가지 데이터를 담아라)

이떄 MIME 타입이 application/x-www-form-urlencoded;charset=utf-8 (key=value)

`토큰 발급 요청주소` : https://kauth.kakao.com/oauth/token

![image-20220217152609558](https://raw.githubusercontent.com/yeonnex/image-server/main/img/image-20220217152609558.png)

![image-20220217153401156](https://raw.githubusercontent.com/yeonnex/image-server/main/img/image-20220217153401156.png)

grant_type=authorization_code
client_id=37514041de77dcefe91c8b81178d8d57
redirect_uri=http://localhost:8000/auth/kakao/callback
code={**동적임**}

