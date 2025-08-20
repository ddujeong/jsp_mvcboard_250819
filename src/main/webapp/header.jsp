<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="jakarta.tags.core" %>
    <link rel="stylesheet" href="css/style.css">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;600&display=swap" rel="stylesheet">
<header class="site-header">
  <div class="logo">
    <a href="index.do">📘 BoardZone</a>
  </div>
  <nav class="nav-links">
    <a href="boardList.do">게시판</a>
    <a href="#">소개</a>
    <a href="#">문의</a>
    <c:choose>
    	<c:when test="${not empty sessionScope.session_id }">
    	<a href="logout.do">👤${sessionScope.session_id}님 로그아웃</a>
    	</c:when>
    	<c:otherwise>
    	
    	 <a href="login.do">로그인</a>
    	</c:otherwise>
    </c:choose>
    
  </nav>
  <a href="index.do" class="btn-home" aria-label="홈으로 이동">🏠</a>
</header>