<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
	<link rel="stylesheet" href="/css/style.css">
  	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
 	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
	<%@ include file="header.jsp" %>

<div class="login-container">
  <h2>로그인</h2>
  <div class="login-footer">
    <c:if test="${param.msg == 1 }">
    	<p>아이디 또는 비밀번호가 잘못 되었습니다.</p>
    </c:if>
     <c:if test="${param.msg == 2 }">
    	<p>로그인을 한 유저만 글을 쓸 수 있습니다.</p>
    </c:if>
  </div>
  <form class="login-form" action="loginOk.do" method="post">
    <label for="member_id">아이디</label>
    <input type="text" id="member_id" name="member_id" required autofocus />
    
    <label for="password">비밀번호</label>
    <input type="password" id="member_pw" name="member_pw" required />
    
    <button type="submit">로그인</button>
  </form>
  
  <div class="login-footer">
    <p>계정이 없으신가요? <a href="register.do">회원가입</a></p>
  </div>
</div>
</body>
</html>