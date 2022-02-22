<%@ page contentType = "text/html; charset=utf-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--현재 세션에 저장된 로그인한 객체가 principal로 저장되어있음. princial == auth/PrincipalDetail.java --%>
<sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<%-- 썸머노트 라이브러리  --%>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<%-- 썸머노트 라이브러리  끝--%>

</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="/">YEONNEX</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
<%-- 세션이 없다면!  --%>
        <c:choose>
            <c:when test="${empty principal}">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
                <li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
            </ul>
            </c:when>

<%-- 세션이 있다면!  --%>
            <c:otherwise>
                <ul class="navbar-nav">
<%-- id 를 중복해서 주면 절대 안됨 😥 회원번호: <span id="userId"><i>${principal.user.id}</i></span>--%>
                    <li class="nav-item"><a class="nav-link" href="/board/saveForm">글쓰기</a></li>
                    <li class="nav-item"><a class="nav-link" href="/user/${principal.user.id}/updateForm">회원정보</a></li>
                    <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
                </ul>

            </c:otherwise>
        </c:choose>

    </div>
</nav>