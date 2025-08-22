<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지사항 수정 - CoffeeBean</title>
  <link rel="stylesheet" href="css/header.css" />
  <link rel="stylesheet" href="css/view.css" />
  <link rel="stylesheet" href="css/footer.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<main>
  <div class="notice-edit-container">
    <h2>공지사항 수정</h2>
    <form action="notice_update_process.jsp" method="post" class="notice-edit-form">
      <label for="title">제목</label>
      <input type="text" id="title" name="title" value="공지사항 제목 예시" required />

      <label for="author">작성자</label>
      <input type="text" id="author" name="author" value="관리자" readonly />

      <label for="content">내용</label>
      <textarea id="content" name="content" rows="10" required>안녕하세요, 추석 연휴 배송 일정 안내드립니다...</textarea>

      <div class="form-buttons">
        <button type="submit" class="btn">저장</button>
        <a href="notice_view.jsp" class="btn btn-cancel">취소</a>
      </div>
    </form>
  </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>
