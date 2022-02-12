
let index = {
    init: function () {
        $("#btn-save").on("click", () => { // function(){} X, ()=>{} this 를 바인딩하기 위해서!!
            this.save();
        });


    },
    save: function () {
        let data = {
            username: $("#username").val(),
            email: $("#email").val(),
            password: $("#password").val()
        }
        console.log(data);
        // ajax 호출시 default 가 비동기 호출출
       $.ajax({
            // 어떤 통신 수행(회원 가입 수행 요청)
           type: "POST",
           url: "/auth/joinProc",
           data: JSON.stringify(data), // http body 데이터
           contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
           dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 바꿔줌
        }).done(function (resp) {
            // 정상이면 여기 실행
           alert("회원가입이 완료되었습니다");
           location.href = "/";
        }).fail(function (error){
            // 실패면 여기 실행
            alert("회원가입 실패!");
        })
    },

}
index.init();