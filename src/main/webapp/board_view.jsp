<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>게시글 상세 - CoffeeBean</title>
  <link rel="stylesheet" href="css/header.css" />
  <link rel="stylesheet" href="css/view.css" />
  <link rel="stylesheet" href="css/footer.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<main>
  <div class="board-view-container">
    <h2>게시글 제목 예시</h2>
    <div class="board-meta">
      <span>작성자: 홍길동</span>
      <span>작성일: 2025-08-20</span>
    </div>
    <div class="board-content">
      <p>게시글 내용 예시입니다. 자유롭게 내용을 작성할 수 있습니다.</p>
    </div>
    <div class="board-buttons">
      <a href="board.jsp" class="btn">목록으로</a>
      <a href="board_edit.jsp" class="btn">수정하기</a>
    </div>
  </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>
