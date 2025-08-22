<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>게시글 수정 - CoffeeBean</title>
  <link rel="stylesheet" href="css/header.css" />
  <link rel="stylesheet" href="css/view.css" />
  <link rel="stylesheet" href="css/footer.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<main>
  <div class="board-edit-container">
    <h2>게시글 수정</h2>
    <form action="board_update_process.jsp" method="post" class="board-edit-form">
      <label for="title">제목</label>
      <input type="text" id="title" name="title" value="게시글 제목 예시" required />

      <label for="author">작성자</label>
      <input type="text" id="author" name="author" value="홍길동" readonly />

      <label for="content">내용</label>
      <textarea id="content" name="content" rows="10" required>게시글 내용 예시입니다...</textarea>

      <div class="form-buttons">
        <button type="submit" class="btn">저장</button>
        <a href="board_view.jsp" class="btn btn-cancel">취소</a>
      </div>
    </form>
  </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>
