<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType = "text/html; charset=utf-8" %>
<%--header.jsp와 footer.jsp를 import할 때 경로를 /layout/* 이렇게 앞에 / 붙여주면 경로를 찾지 못한다.--%>
<%--반드시 "상대경로" 를 적어주어야 한다--%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id == principal.user.id}">
    <button class="btn btn-warning"><a href="/board/${board.id}/updateForm">수정</a></button>
    <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <hr/>

    <div>
        글번호: <span id="writeId"><i>${board.id}</i></span>
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
        <form>
            <input type="hidden" id="userId" value="${board.user.id}"/>
            <input type="hidden" id="boardId" value="${board.id}"/>
            <div class="card-body">
                <textarea id ="reply-content" class="form-control"></textarea>
            </div>
            <div class="card-footer">
                <button type="button" id="btn-reply-save"class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>
    <br/>
<%-- 제이쿼리나 부트스트랩이 쓰고 있는 id 와 겹칠수도 있기 때문에  --%>
<%-- 구분하기 위해 내가 만든 id 는 작대기를 두개 걸어주는 것이 좋음   --%>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply--box" class="list-group">
            <c:forEach var="reply" items="${board.replys}">
                <li id="reply--1" class="list-group-item d-flex justify-content-between" >
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자: ${reply.user.userName} &nbsp;</div>
                        <button class="badge">삭제</button>
                    </div>
                </li>
            </c:forEach>

        </ul>
    </div>

</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>