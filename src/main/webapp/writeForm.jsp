<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>글쓰기</title>
  <link rel="stylesheet" href="css/style.css" />
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet" />
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;600&display=swap" rel="stylesheet" />
</head>
<body>

<div class="container">

  <div class="header">
    <div class="board-title">
      <span class="icon">📝</span>
      <h2>글쓰기</h2>
    </div>
    <a href="boardList.do" class="write-btn">← 목록으로</a>
  </div>

  <form class="post-form" action="writeOk.do" method="post">
    <label for="btitle">제목</label>
    <input type="text" id="btitle" name="btitle" required>

    <label for="member_id">작성자</label>
    <input type="text" id="member_id" name="member_id" value="${sessionScope.session_id }" readonly="readonly">

    <label for="bcontent">내용</label>
    <textarea id="bcontent" name="bcontent" rows="10" required></textarea>

    <div class="form-actions">
      <button type="submit" class="action-btn">작성 완료</button>
      <a href="boardList.do" class="action-btn delete">취소</a>
    </div>
  </form>

</div>

</body>
</html>
