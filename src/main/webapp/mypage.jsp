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
    <form action="mypage_process.jsp" method="post" class="mypage-form">
  
  <!-- 아이디: 수정 불가, 표시만 -->
  <label for="userId">아이디</label>
  <input type="text" id="userId" name="userId" value="${user.userId}" readonly />

  <!-- 이메일 -->
  <label for="email">이메일</label>
  <input type="email" id="email" name="email" value="${user.email}" required />

  <!-- 현재 비밀번호 -->
  <label for="currentPassword">현재 비밀번호</label>
  <input type="password" id="currentPassword" name="currentPassword" placeholder="현재 비밀번호 입력" required />

  <!-- 새 비밀번호 -->
  <label for="newPassword">새 비밀번호</label>
  <input type="password" id="newPassword" name="newPassword" placeholder="변경할 비밀번호 입력" />

  <!-- 새 비밀번호 확인 -->
  <label for="newPasswordConfirm">새 비밀번호 확인</label>
  <input type="password" id="newPasswordConfirm" name="newPasswordConfirm" placeholder="비밀번호 다시 입력" />

  <button type="submit">정보 수정</button>
</form>

  </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>
