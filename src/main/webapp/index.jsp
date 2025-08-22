<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>모던 게시판 - 인덱스</title>
  <!-- <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;600&display=swap" rel="stylesheet"> -->
</head>
<body>
<%@ include file="header.jsp" %>


<main class="hero">
  <div class="hero-inner">
  <c:choose>
    	<c:when test="${not empty sessionScope.session_id }">
    	<h1><i>${sessionScope.session_id}</i> 님 모던 게시판에 오신 것을 환영합니다</h1>
    	<p>심플하고 세련된 UI로 게시글을 관리하고 공유하세요.</p>
    	</c:when>
    	<c:otherwise>
    	<h1> 모던 게시판에 오신 것을 환영합니다</h1>
    	<p> <b><a href="login.do">로그인</a></b> 하신 후 게시글을 관리하고 공유하세요.</p>
    	</c:otherwise>
    </c:choose>
    
    <a href="boardList.do?" class="enter-btn">게시판 바로가기</a>
  </div>
</main>

</body>
</html>
