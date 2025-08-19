<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <h1 class="post-title">게시글 제목 예시입니다.</h1>
    <div class="post-meta">
      <span>작성자: 홍길동</span>
      <span>작성일: 2025-08-19</span>
      <span>조회수: 128</span>
    </div>
    <hr />
    <div class="post-content">
      <p>
        여기에 게시글 내용이 들어갑니다. HTML 태그 허용 시 적절히 렌더링 되고,
        줄바꿈, 이미지 등도 포함될 수 있습니다.
      </p>
      <p>필요하다면 코드, 표, 인용문 등 다양한 콘텐츠도 포함 가능합니다.</p>
    </div>

   
  <div class="post-actions">
  <a href="boardList.do" class="action-btn secondary">목록으로</a>
  <a href="modifyForm.do?postId=123" class="action-btn">수정</a>
  <a href="deleteForm.do?postId=123" class="action-btn delete">삭제</a>
  
</div>
  </article>

</div>

</body>
</html>
