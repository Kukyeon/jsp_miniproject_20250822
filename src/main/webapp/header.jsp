<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>CoffeeBean</title>
  <link rel="stylesheet" href="css/header.css" />
</head>
<body>
<header>
  <div class="header-container">
    <nav class="nav-left">
      <a href="index.jsp">홈</a>
      <a href="shop.jsp">샵</a>
      <a href="boardList.jsp">게시판</a>
      <a href="notice.jsp">공지사항</a>
    </nav>

    <div class="logo">
      <a href="index.jsp">CoffeeBean</a>
    </div>

    <div class="nav-right">
      <%
        String userId = (String) session.getAttribute("userId");
        if(userId != null) {
      %>
        <!-- 로그인 상태일 때 -->
        <div class="profile-menu">
          <div class="profile-icon">&#128100;</div> <!-- 사람모양 이모지, 필요하면 이미지나 svg로 교체 -->
          <div class="dropdown-menu">
            <a href="mypage.jsp">내 정보</a>
            <a href="mypage_edit.jsp">회원정보 수정</a>
            <a href="logout.jsp">로그아웃</a>
          </div>
        </div>
      <% } else { %>
        <!-- 로그인 전 -->
        <a href="login.jsp" class="login-icon">&#128100;</a>
      <% } %>
    </div>
  </div>
</header>
</body>
</html>
