<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지사항 - CoffeeBean</title>
  <link rel="stylesheet" href="css/header.css" />
  <link rel="stylesheet" href="css/notice.css" />
  <link rel="stylesheet" href="css/footer.css" />
</head>
<body>

<jsp:include page="header.jsp" />

<main>
  <div class="board-container">
    <div class="board-header">
      <h2>공지사항</h2>
    </div>

    <table class="board-table">
      <thead>
        <tr>
          <th>No.</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
          <th>조회수</th>
        </tr>
      </thead>
     <tbody>
		  <tr>
		    <td>3</td>
		    <td><a href="notice_view.jsp?id=3">[안내] 추석 연휴 배송 일정</a></td>
		    <td>관리자</td>
		    <td>2025-09-10</td>
		    <td>3</td>
		  </tr>
		  <tr>
		    <td>2</td>
		    <td><a href="notice_view.jsp?id=2">[공지] 신규 원두 출시 안내</a></td>
		    <td>관리자</td>
		    <td>2025-08-25</td>
		    <td>3</td>
		  </tr>
		  <tr>
		    <td>1</td>
		    <td><a href="notice_view.jsp?id=1">[점검] 사이트 점검 안내</a></td>
		    <td>운영팀</td>
		    <td>2025-08-01</td>
		    <td>3</td>
		  </tr>
	</tbody>

    </table>

    <div class="board-pagination">
      <a href="#">«</a>
      <a href="#" class="active">1</a>
      <a href="#">2</a>
      <a href="#">3</a>
      <a href="#">»</a>
    </div>
  </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>

