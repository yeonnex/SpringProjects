<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType = "text/html; charset=utf-8" %>
<%--header.jsp와 footer.jsp를 import할 때 경로를 /layout/* 이렇게 앞에 / 붙여주면 경로를 찾지 못한다.--%>
<%--반드시 "상대경로" 를 적어주어야 한다--%>
<%@ include file="layout/header.jsp"%>
<!-- Card -->
<c:forEach var="board" items="${boards}">
    <div class="card m-3">
        <div class="card-body">
            <h4 class="card-title">${board.title}</h4>
            <a href="#" class="btn btn-primary">상세보기</a>
        </div>
    </div>
</c:forEach>
<!-- Card -->
<script src="/js/board.js"></script>
<%@ include file="layout/footer.jsp"%>




