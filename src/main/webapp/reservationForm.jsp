<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방문 에약 방식</title>
</head>
<body>
	<!-- 로그인 한 상태만 예약 가능 -->
	<h2>방문 예약 하기</h2>
	<hr>
	<form action="reservationOk.do" method="post">
		이름 : <input type="text" name="rname" required="required"><br><br>
		전화번호 : <input type="text" name="rphone" required="required"><br><br>
		예약일 : <input type="date" name="rdate" required="required"><br><br>
		예약시간 : <input type="time" name="rtime" required="required"><br><br>
		<input type="submit" value="예약하기">
	</form>
	
</body>
</html>