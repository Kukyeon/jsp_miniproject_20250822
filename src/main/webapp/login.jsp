<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>로그인 - CoffeeBean</title>
  <link rel="stylesheet" href="css/header.css" />
  <link rel="stylesheet" href="css/login.css" />
  <link rel="stylesheet" href="css/footer.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<main>
	<div class="login-container">
	  <h2>로그인</h2>
	  <form action="login_process.jsp" method="post" class="login-form">
	    <label for="memberid">아이디</label>
	    <input type="text" id="memberid" name="memberid" placeholder="아이디를 입력하세요" required />
	
	    <label for="password">비밀번호</label>
	    <input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요" required />
	
	    <button type="submit">로그인</button>
	  </form>
	
	  <div class="signup-link">
	    <a href="signup.jsp">회원가입</a>
	  </div>
	</div>
	
	<div class="login-links">
      	<c:if test="${param.msg == 1}">
      		<p style="color: red;">
      			아이디 또는 비밀번호가 잘못 되었습니다.
      		</p>
      	</c:if>
      		<c:if test="${param.msg == 2}">
      		<p style="color: red;">
      			로그인 한 유저만 글을 쓸 수 있습니다.
      		</p>
      	</c:if>
    </div>

</main>

<jsp:include page="footer.jsp" />

</body>
</html>
