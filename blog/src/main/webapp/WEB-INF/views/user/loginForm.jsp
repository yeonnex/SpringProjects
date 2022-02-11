<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType = "text/html; charset=utf-8" %>
<%--header.jsp와 footer.jsp를 import할 때 경로를 /layout/* 이렇게 앞에 / 붙여주면 경로를 찾지 못한다.--%>
<%--반드시 "상대경로" 를 적어주어야 한다--%>
<%@ include file="../layout/header.jsp"%>
<!-- 로그인 폼 -->
<form action="#" method="post">
    <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
    </div>
    <div class="form-group form-check">
        <label class="form-check-label">
            <input class="form-check-input" name="remember" type="checkbox"> Remember me
        </label>
    </div>
<%-- 이제 로그인 시 user.js 사용하지 않을 것임! 폼으로 바로 로그인할 것이다. 버튼을 누르면 폼에서 action 을 탄다!!! --%>
<button id="btn-login" class="btn btn-primary">로그인</button>
</form>
<!-- 로그인 폼 끝 -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>




