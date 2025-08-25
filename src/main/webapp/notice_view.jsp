<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
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
    <h2>${bDto.btitle}</h2>
    <div class="notice-meta">
      <span>작성자: ${bDto.memberid}</span>
      <span>작성일: ${fn:substring(bDto.bdate, 0, 10) }</span>
    </div>
    <div class="notice-content">
      <p>${bDto.bcontent}</p>
    </div>
    <div class="notice-buttons">
      <a href="notice.do" class="btn">목록으로</a>
      
      
      <c:if test="${sessionScope.sessionId == '관리자'}"> <!-- 단 한명만 수정가능하게 하려면 아이디입력 -->
    <a href="edit2.do?bnum=${bDto.bnum}" class="btn btn-edit">수정하기</a>
    <form action="delete.do" method="post" onsubmit="return confirm('정말 삭제하시겠습니까?');" style="display:inline;">
      <input type="hidden" name="bnum" value="${bDto.bnum}" />
      <button type="submit" class="btn btn-delete">삭제하기</button>
      </c:if>
    </div>
  </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>

