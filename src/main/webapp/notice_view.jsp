<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지사항 상세 - CoffeeBean</title>
  <link rel="stylesheet" href="css/header.css" />
  <link rel="stylesheet" href="css/view.css" />
  <link rel="stylesheet" href="css/footer.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<main>
  <div class="notice-view-container">
    <h2>공지사항 제목 예시</h2>
    <div class="notice-meta">
      <span>작성자: 관리자</span>
      <span>작성일: 2025-09-10</span>
    </div>
    <div class="notice-content">
      <p>안녕하세요, 추석 연휴 배송 일정 안내드립니다.<br />
      추석 기간에는 배송이 지연될 수 있으니 참고 부탁드립니다.</p>
    </div>
    <div class="notice-buttons">
      <a href="notice.jsp" class="btn">목록으로</a>
      <a href="notice_edit.jsp" class="btn">수정하기</a>
    </div>
  </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>

