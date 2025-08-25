<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>회원정보 수정 - CoffeeBean</title>
  <link rel="stylesheet" href="css/header.css" />
  <link rel="stylesheet" href="css/mypage.css" />
  <link rel="stylesheet" href="css/footer.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<main>

  <div class="login-container">
    <h2>회원정보 수정</h2>
    <% String msg = (String) request.getAttribute("msg");
   if (msg != null) { %>
   <script>alert("<%= msg %>");</script>
<% } %>
    
    <form action="mypageOk.do" method="post" class="mypage-form">
  
  <!-- 아이디: 수정 불가, 표시만 -->
  <label for="userId">아이디</label>
  <input type="text" id="userId" name="memberid" value="${bDto.memberid }" readonly />

  <!-- 이메일 -->
  <label for="email">이메일</label>
  <input type="email" id="email" name="memberemail" value="${bDto.memberemail}" required />

  <!-- 현재 비밀번호 -->
  <label for="currentPassword">현재 비밀번호</label>
  <input type="password" id="currentPassword" name="currentPw" placeholder="현재 비밀번호 입력" required />

  <!-- 새 비밀번호 -->
  <label for="newPassword">새 비밀번호</label>
  <input type="password" id="newPassword" name="memberpw" placeholder="변경할 비밀번호 입력" />

  

  <button type="submit">정보 수정</button>
</form>

  </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>
