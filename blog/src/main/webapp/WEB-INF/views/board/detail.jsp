<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType = "text/html; charset=utf-8" %>
<%--header.jsp와 footer.jsp를 import할 때 경로를 /layout/* 이렇게 앞에 / 붙여주면 경로를 찾지 못한다.--%>
<%--반드시 "상대경로" 를 적어주어야 한다--%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id == principal.user.id}">
    <a href="/board/${board.id}/updateForm">수정</a>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <hr/>

    <div>
        글번호: <span id="writeId"><i>${board.id} </i></span>
        작성자: <span id="name"><i>${board.user.userName} </i></span>
    </div>
    <div>
        <h3>${board.title}</h3>
    </div>
    <hr/>
    <div>
        <div>${board.content}</div>
    </div>


    <div class="card">
        <div class="card-body"><textarea class="form-control"></textarea></div>
        <div class="card-footer"><button class="btn btn-primary">등록</button></div>
    </div>
    <br/>
<%-- 제이쿼리나 부트스트랩이 쓰고 있는 id 와 겹칠수도 있기 때문에  --%>
<%-- 구분하기 위해 내가 만든 id 는 작대기를 두개 걸어주는 것이 좋음   --%>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="comment--box" class="list-group">
            <li id="comment--1" class="list-group-item d-flex justify-content-between" >
                <div>댓글 내용입니다!</div>
                <div class="d-flex">
                    <div class="font-italic">작성자: syhoneyjam &nbsp;</div>
                    <button class="badge">삭제</button>
                </div>
            </li>
        </ul>
    </div>

</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>