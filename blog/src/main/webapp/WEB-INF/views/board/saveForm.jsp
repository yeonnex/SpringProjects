<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType = "text/html; charset=utf-8" %>
<%--header.jsp와 footer.jsp를 import할 때 경로를 /layout/* 이렇게 앞에 / 붙여주면 경로를 찾지 못한다.--%>
<%--반드시 "상대경로" 를 적어주어야 한다--%>
<%@ include file="../layout/header.jsp"%>


<form>
    <div class="form-group">
        <label for="title">Title:</label>
        <input type="text" class="form-control" placeholder="Enter username" id="title">
    </div>

    <div class="form-group">
        <label for="content">Content:</label>
        <textarea class="form-control summernote" id="content" cols="30" rows="5"></textarea>
    </div>

    <script>
        $('.summernote').summernote({
            tabsize: 2,
            height: 300
        });
    </script>

    <%-- 이제 로그인 시 user.js 사용하지 않을 것임! 폼으로 바로 로그인할 것이다. 버튼을 누르면 폼에서 action 을 탄다!!! --%>
</form>

<button id="btn-save" class="btn btn-primary">글쓰기완료</button>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>