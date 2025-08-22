<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core"%>
 <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>모던 게시판</title>
  <!-- <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;600&display=swap" rel="stylesheet"> -->
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
 <div class="header">
  <div class="board-title">
    <span class="icon">📌</span>
    <a href="boardList.do"><h2>게시판</h2></a>
    <span class="post-count">총 <strong>${totalBoardCount }</strong>개 글</span>
  </div>

  <div class="board-actions">
    <form class="search-form" action="boardList.do" method="get">
  		<select name="searchType" >
  			<option value="b.btitle" ${searchType == 'b.btitle' ? 'selected' : '' }>제목</option>
  			<option value="b.bcontent" ${searchType == 'b.bcontent' ? 'selected' : '' }>내용</option>
  			<option value="m.member_id" ${searchType == 'm.member_id' ? 'selected': '' }>작성자</option>
  		</select>
    	<input type="text" name="searchKeyword" value="${param.searchKeyword }" placeholder="검색어 입력" />
    	<%-- ${searchKeyword != null ? searchKeyword : ''} --%>
    	<button type="submit">검색</button>
    </form>
    <a href="writeForm.do" class="write-btn">+ 글쓰기</a>
  </div>
</div>

 <table class="board-table">
  <thead>
    <tr>
      <th>번호</th>
      <th>제목</th>
      <th>작성자</th>
      <th>이메일</th>
      <th>날짜</th>
      <th>조회수</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${bDtos}" var="bDto">
    <tr>
      <td>${bDto.bno }</td>
      <td>
      <c:choose>
      	<c:when test="${fn:length(bDto.btitle) > 25}">
     	 <a href="contentView.do?bnum=${bDto.bnum }">${fn:substring(bDto.btitle,0,25)}...</a>
      	</c:when>
      	<c:otherwise>
      		<a href="contentView.do?bnum=${bDto.bnum }">${bDto.btitle}</a>
      	</c:otherwise>
      </c:choose>
       </td>
      <td>${bDto.member_id }</td>
      <td>${bDto.memberDto.member_email }</td>
      <td>${fn:substring(bDto.bdate,0,10)}</td>
      <td>${bDto.bhit }</td>
    </tr>
    </c:forEach>
  </tbody>
</table>
<div class="pagination-wrapper">
  <ul class="pagination">
    <!-- 1페이지로 이동 버튼 -->
    <c:if test="${currentPage > 1}">
      <li><a href="boardList.do?searchType=${searchType }&searchKeyword=${searchKeyword }&page=1">&laquo;</a></li>
     <!-- 이전 그룹으로 이동 버튼 -->
    </c:if>
    <c:if test="${startPage > 1 }">
		<li><a href="boardList.do?searchType=${searchType }&searchKeyword=${searchKeyword }&page=${startPage -1 }">&lt;</a></li>
	</c:if>
    <!-- 페이지 번호 목록 -->
    <c:forEach var="i" begin="${startPage}" end="${endPage}">
      <li class="${i == currentPage ? 'active' : ''}">
        <a href="boardList.do?searchType=${searchType }&searchKeyword=${searchKeyword }&page=${i}">${i}</a>
      </li>
    </c:forEach>

    <!-- 다음 그룹으로 이동 버튼 -->
    <c:if test="${endPage < totalPage}">
      <li><a href="boardList.do?searchType=${searchType }&searchKeyword=${searchKeyword }&page=${endPage + 1}">&gt;</a></li>
    </c:if>
    <!-- 마지막 페이지로 이동 버튼 -->
    <c:if test="${currentPage < totalPage}">
      <li><a href="boardList.do?searchType=${searchType }&searchKeyword=${searchKeyword }&page=${totalPage}">&raquo;</a></li>
    </c:if>
  </ul>
</div>
</div>
</body>
</html>
