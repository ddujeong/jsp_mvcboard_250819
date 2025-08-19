<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>글 삭제 확인</title>
  <link rel="stylesheet" href="css/style.css" />
</head>
<body>

<div class="container">

  <div class="header">
    <div class="board-title">
      <span class="icon">❗</span>
      <h2>글 삭제 확인</h2>
    </div>
    <a href="boardList.do" class="write-btn">← 목록으로</a>
  </div>

  <div class="delete-confirm">
    <p>정말 이 글을 삭제하시겠습니까?</p>

    <form action="delete.do" method="post">
      <!-- 삭제할 글 ID, 실제 값 서버에서 동적으로 넣기 -->
      <input type="hidden" name="postId" value="123" />

      <div class="form-actions">
        <button type="submit" class="action-btn delete">삭제하기</button>
        <a href="contentView.do?postId=123" class="action-btn secondary">취소</a>
      </div>
    </form>
  </div>

</div>

</body>
</html>
