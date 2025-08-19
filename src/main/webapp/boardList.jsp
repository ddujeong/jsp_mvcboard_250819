<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core"%>
 <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
   
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>모던 게시판</title>
  <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
	
<div class="container">
 <div class="header">
  <div class="board-title">
    <span class="icon">📌</span>
    <h2>게시판</h2>
    <span class="post-count">총 <strong>128</strong>개 글</span>
  </div>

  <div class="board-actions">
    <form class="search-form" action="boardList.do" method="get">
      <input type="text" name="query" placeholder="검색어 입력" />
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
      <th>날짜</th>
      <th>조회수</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${bDtos}" var="bDto">
    <tr>
      <td>${bDto.bnum }</td>
      <td>
      <c:choose>
      	<c:when test="${fn:length(bDto.btitle) > 25}">
     	 <a href="contentView.do">${fn:substring(bDto.btitle,0,25)}...</a>
      	</c:when>
      	<c:otherwise>
      		<a href="contentView.do">${bDto.btitle}</a>
      	</c:otherwise>
      </c:choose>
       </td>
      <td>${bDto.member_id }</td>
      <td>${fn:substring(bDto.bdate,0,10)}</td>
      <td>${bDto.bhit }</td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</div>
</body>
</html>
