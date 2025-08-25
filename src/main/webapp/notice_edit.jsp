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
<%
  if (request.getAttribute("error") != null) {
   
    out.println("<script>alert('수정 또는 삭제 권한이 없습니다.'); window.location.href='history.go(-1)';</script>");
  }
%>
  <div class="notice-edit-container">
    <h2>공지사항 수정</h2>
    <form action="update2.do" method="post" class="notice-edit-form">
    <input type="hidden" name="bnum" value="${bDto.bnum }" />
      <label for="title">제목</label>
      <input type="text" id="title" name="title" value="${bDto.btitle}" required />

      <label for="author">작성자</label>
      <input type="text" id="author" name="author" value="${bDto.memberid }" readonly />

      <label for="content">내용</label>
      <textarea id="content" name="content" rows="10" required>${bDto.bcontent }</textarea>

      <div class="form-buttons">
        <button type="submit" class="btn">저장</button>
        <a href="javascript:history.go(-1)" class="btn btn-cancel">취소</a>
      </div>
    </form>
  </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>
