<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
   <%
  	String msg =(String)request.getAttribute("deleteMsg");
  	// 웹 서블릿에서 넘겨준 값을 뺄때는 getAttribute 사용
  	if(msg != null){
  		out.println("<script>alert('" + msg + "');window.location.href='boardList.do';</script>");
  	}
  %> 
    <%-- <% //2번째 방법
  	if( request.getParameter("msg") != null){
  		out.println("<script>alert('해당 글은 존재하지 않는 글입니다!');window.location.href='boardList.do';</script>");
  	}
  	%>  --%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>게시글 상세보기</title>
  <link rel="stylesheet" href="css/style.css" />
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;600&display=swap" rel="stylesheet" />
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">

  <div class="header">
    <div class="board-title">
      <span class="icon">📌</span>
      <h2>게시글 상세보기</h2>
    </div>
    <a href="boardList.do" class="write-btn">목록으로</a>
  </div>

  <article class="post-detail">
    <h1 class="post-title"> ${bDto.btitle}</h1>
    <div class="post-meta">
      <span>작성자: ${bDto.member_id}</span>
      <span>이메일: ${bDto.memberDto.member_email }</span>
      <span>작성일: ${bDto.bdate }</span>
      <span>조회수: ${bDto.bhit }</span>
    </div>
    <hr />
    <div class="post-content">
      <p>
        ${bDto.bcontent }<br>
      </p>
   		
    </div>
   
  <div class="post-actions">
  <a href="boardList.do" class="action-btn secondary">목록으로</a>
  	<c:if test="${sessionScope.session_id == bDto.member_id}">
  		<a href="modifyForm.do?bnum=${bDto.bnum }" class="action-btn">수정</a>
  		<a href="deleteForm.do?bnum=${bDto.bnum }" class="action-btn delete">삭제</a>
  	</c:if>
</div>
  </article>

</div>

</body>
</html>
