<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>회원가입 - CoffeeBean</title>
  <link rel="stylesheet" href="css/header.css" />
  <link rel="stylesheet" href="css/signup.css" /> <!-- 로그인 스타일과 비슷하게 -->
  <link rel="stylesheet" href="css/footer.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<main>
  <div class="signup-container">
    <h2>회원가입</h2>
    <form action="signup_process.jsp" method="post" class="signup-form">
    
      <label for="username">아이디</label>
      <input type="text" id="username" name="username" placeholder="아이디를 입력하세요" required />

      <label for="password">비밀번호</label>
      <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required />

      <label for="passwordConfirm">비밀번호 확인</label>
      <input type="password" id="passwordConfirm" name="passwordConfirm" placeholder="비밀번호를 다시 입력하세요" required />

      <label for="email">이메일</label>
      <input type="email" id="email" name="email" placeholder="이메일을 입력하세요" required />

      <button type="submit">회원가입</button>

    </form>
  </div>
</main>


<jsp:include page="footer.jsp" />

</body>
</html>
