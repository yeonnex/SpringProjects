<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType = "text/html; charset=utf-8" %>
<%--header.jsp와 footer.jsp를 import할 때 경로를 /layout/* 이렇게 앞에 / 붙여주면 경로를 찾지 못한다.--%>
<%--반드시 "상대경로" 를 적어주어야 한다--%>
<%@ include file="../layout/header.jsp"%>
<!-- 회원수정 폼 -->

<form>
    <input type="hidden" id="id" value="${principal.user.id}">
    <input type="hidden" id="user-name" value="${principal.user.userName}">
    <div class="form-group">
        <label for="username">Username:</label>
        <input value= "${principal.user.userName}" type="text" class="form-control" placeholder="Enter username" id="username" readonly>
    </div>
    <div class="form-group">
        <label for="email">Email:</label>
        <input value="${principal.user.email}" type="email" class="form-control" placeholder="Enter email" id="email" readonly>
    </div>
    <c:if test="${empty principal.user.oauth}">
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
    </c:if>
</form>
<button id="btn-update" class="btn btn-primary">회원수정완료</button>
<!-- 회원수정 폼 끝 -->
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>

