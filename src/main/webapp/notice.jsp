<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>공지사항 - CoffeeBean</title>
  <link rel="stylesheet" href="css/header.css" />
  <link rel="stylesheet" href="css/notice.css?v=2" />
  <link rel="stylesheet" href="css/footer.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
  
</head>
<body>

<jsp:include page="header.jsp" />

<main>
  <div class="board-container">
    <div class="board-header">
      <h2>공지사항</h2>
      <c:if test="${sessionScope.sessionId == '관리자'}"> <!-- 로그인 한 사람만 글쓰기 버튼 표시 -->
     	 <a href="notice_write.do" class="btn-write">글쓰기</a>
      </c:if>
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
       <c:forEach items="${bDtos}" var="bDto" varStatus="status">
       <c:if test="${bDto.memberid == '관리자'}">
        <tr>
          <td>${bDto.bno }</td>
          <td>
          	<c:choose>
          		<c:when test="${fn:length(bDto.btitle) > 10}">
         			 <a href="view2.do?bnum=${bDto.bnum}">${fn:substring(bDto.btitle, 0, 10)}...</a>
          		</c:when>
          		<c:otherwise>
          			 <a href="view2.do?bnum=${bDto.bnum}">${bDto.btitle}</a>
          		</c:otherwise>
          </c:choose>
          </td>
          <td>${bDto.memberid }</td>
          <td>${fn:substring(bDto.bdate, 0 , 10) }</td>
          <td>${bDto.bhit }</td> <!-- 추가 -->
        </tr>
         </c:if>
		 </c:forEach> 
	</tbody>

    </table>

      <div class="pagination">
		          <!-- 페이징 -->
		    <!--  이전 페이지로 이동 -->
	<c:if test="${currentPage > 1}">
		<a href="notice.do?page=1&searchType=${searchType}&searchKeyword=${searchKeyword}"
		   class="page-num"><i class="fa-solid fa-circle-arrow-left"></i></a>
	</c:if>
	<c:if test="${startPage > 1 }">
		<a href="notice.do?page=${startPage-1 }&searchType=${searchType}&searchKeyword=${searchKeyword}"
		class="page-num"><i class="fa-solid fa-arrow-left"></i></a>
	</c:if>
		    <!--  이전 페이지로 이동  끝-->
		    
		    <!-- 그룹 선택 페이징 -->
	<c:forEach begin="${startPage }" end="${endPage}" var="i">  
		<c:choose>
			<c:when test="${i == currentPage}">
			<span class="pagination">${i}</span>
			</c:when>
			<c:otherwise>
			<a href="notice.do?page=${i}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-num">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
		<!-- 그룹 선택 페이징 끝-->
		
		<!--  다음 페이지로 이동 -->
	<c:if test="${endPage < totalPage}">
		<a href="notice.do?page=${endPage + 1}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-num"><i class="fa-solid fa-arrow-right"></i></a>
	</c:if>
	<c:if test="${currentPage < totalPage}">
		<a href="notice.do?page=${totalPage}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-num"><i class="fa-solid fa-circle-arrow-right"></i></a>
	</c:if>
	</div>
  </div>
</main>

<jsp:include page="footer.jsp" />

</body>
</html>

