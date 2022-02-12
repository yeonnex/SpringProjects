
let index = {
    init: function () {
        $("#btn-save").on("click", () => { // function(){} X, ()=>{} this 를 바인딩하기 위해서!!
            this.save();
        });
        $("#btn-delete").on("click", ()=>{
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{
            this.updateById();
        })

    },
    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        // ajax 호출시 default 가 비동기 호출출
       $.ajax({
            // 어떤 통신 수행(회원 가입 수행 요청)
           type: "POST",
           url: "/api/board",
           data: JSON.stringify(data), // http body 데이터
           contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
           dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 바꿔줌
        }).done(function (resp) {
            // 정상이면 여기 실행
           alert("글쓰기가 완료되었습니다");
           location.href = "/";
        }).fail(function (error){
            // 실패면 여기 실행
            alert("회원 가입에 실패!");
        }) // ajax 통신을 이용해서 3개의 데이터를 json 으로 변경하여 insert 요청!!
    },

    deleteById: function (){

        var id = document.getElementById("writeId");
        alert(id.innerText + "번째 게시물 삭제!");
        // 또는
        // var id = $("#writeId").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id.innerText,
            dataType: "json"
        }).done(function (resp){
            alert("삭제가 완료되었습니다");
            location.href = "/";
        }).fail(function (error){
            // 실패면 여기 실행
            alert("삭제 실패!");
        })

    },
    updateById: function (){
        var id = $("#updated-writeId").text();
        alert(id);
        let data = {
            title: $("#updated-title").val(),
            content: $("#updated-content").val()
        }
        alert("제목은!");
        alert(data.title);
        // ajax 호출시 default 가 비동기 호출
        $.ajax({
            // 어떤 통신 수행(회원 가입 수행 요청)
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 바꿔줌
        }).done(function (resp) {
            // 정상이면 여기 실행
            alert("수정이 완료되었습니다");
            location.href = "/";
        }).fail(function (error){
            // 실패면 여기 실행
            alert("수정이 실패!");
        })
    }


}
index.init();