<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType = "text/html; charset=utf-8" %>
<%--header.jsp와 footer.jsp를 import할 때 경로를 /layout/* 이렇게 앞에 / 붙여주면 경로를 찾지 못한다.--%>
<%--반드시 "상대경로" 를 적어주어야 한다--%>
<%@ include file="../layout/header.jsp"%>
<!-- 로그인 폼 -->
<form>
    <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" class="form-control" placeholder="Enter username" id="username">
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" placeholder="Enter password" id="password">
    </div>
    <div class="form-group form-check">
        <label class="form-check-label">
            <input class="form-check-input" type="checkbox"> Remember me
        </label>
    </div>
</form>
<button id="btn-login" class="btn btn-primary">로그인</button>
<!-- 로그인 폼 끝 -->
<script src="/blog/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>




