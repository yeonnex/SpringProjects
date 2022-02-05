<%@ page contentType = "text/html; charset=utf-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="/blog">YEONNEX</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/user/login">로그인</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user/join">회원가입</a>
            </li>
        </ul>
    </div>
</nav>
<!-- Card -->
<div class="card m-3">
    <div class="card-body">
        <h4 class="card-title">제목 적는 부분</h4>
        <p class="card-text">내용</p>
        <a href="#" class="btn btn-primary">상세보기</a>
    </div>
</div>
<div class="card m-3">
    <div class="card-body">
        <h4 class="card-title">제목 적는 부분</h4>
        <p class="card-text">내용</p>
        <a href="#" class="btn btn-primary">상세보기</a>
    </div>
</div>
<!-- Card -->
<!-- Footer -->
<footer class="page-footer font-small teal pt-4" style="background-color: gainsboro">

    <!-- Footer Text -->
    <div style="text-align: center">
        <h6>Created by yeonnex</h6>
        <h6>📞 010-1111-2222</h6>
        <h6>🏠 서울시 동작구</h6>
    </div>
    <!-- Copyright -->
    <div class="footer-copyright text-center py-3">© 2022 Copyright:
        <a href="https://mdbootstrap.com/"> MDBootstrap.com</a>
    </div>
    <!-- Copyright -->

</footer>
<!-- Footer -->


</body>


</html>


