<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/upadtePw.do">
	<input type="hidden" value="${sessionScope.m.memberId }"> 
	기존 비밀번호 입력 : <input type="password" name="memberPw"> <input type="submit" value="확인">
	</form>
</body>
</html>