<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>글 수정</title>
  <link rel="stylesheet" href="css/style.css" />
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;600&display=swap" rel="stylesheet" />
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">

  <div class="header">
    <div class="board-title">
      <span class="icon">✏️</span>
      <h2>글 수정</h2>
    </div>
    <a href="boardList.do" class="write-btn">← 목록으로</a>
  </div>

  <form class="post-form" action="modify.do" method="post">
    <!-- hidden id or postId for backend -->
    <input type="hidden" name="bnum" value="${bDto.bnum }" />

    <label for="title">제목</label>
    <input type="text" id="btitle" name="btitle" value="${bDto.btitle}" required>

    <label for="author">작성자</label>
    <input type="text" id="member_id" name="member_id" value="${bDto.member_id}" readonly required>

    <label for="content">내용</label>
    <textarea id="bcontent" name="bcontent" rows="10" required>${bDto.bcontent }</textarea>

    <div class="form-actions">
      <button type="submit" class="action-btn">수정 완료</button>
      <a href="javascript:history.go(-1)" class="action-btn delete">취소</a>
    </div>
  </form>

</div>

</body>
</html>
