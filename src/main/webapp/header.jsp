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
      <a href="index.do">홈</a>
      <a href="shop.jsp">샵</a>
      <a href="boardList.do">게시판</a>
      <a href="notice.do">공지사항</a>
    </nav>

    <div class="logo">
      <a href="index.do">CoffeeBean</a>
    </div>

    <div class="nav-right">
      <%
        String userId = (String) session.getAttribute("sessionId");
        if(userId != null) {
      %>
        <!-- 로그인 상태일 때 -->
        <div class="profile-menu">
          <div class="profile-icon">&#128100;</div> <!-- 사람모양 이모지, 필요하면 이미지나 svg로 교체 -->
          <div class="dropdown-menu">
            <a href="mypage.do">내 정보</a>
            <a href="mypage.do">회원정보 수정</a>
            <a href="logout.do">로그아웃</a>
          </div>
        </div>
      <% } else { %>
        <!-- 로그인 전 -->
        <a href="login.do" class="login-icon">&#128100;</a>
      <% } %>
    </div>
  </div>
</header>
<script>
	  const urlParams = new URLSearchParams(window.location.search);
	  const message = urlParams.get("message");
	  if (message === "login") {
		    alert("로그인 되었습니다.");
		  }
	  if (message === "signup") {
		    alert("회원가입 되었습니다. 오른쪽 상단을 눌러 로그인 해주세요.");
		  }
	  if (message === "logout") {
	    alert("로그아웃 되었습니다.");
	  }
	  if (message === "signup1") {
		    alert("회원정보가 수정되었습니다 다시 로그인해주세요.");
		  }
	  if (message) {
		    window.history.replaceState({}, document.title, window.location.pathname);
		  }
</script>
</body>
</html>
