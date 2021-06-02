<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- JSTL Core 태그 -->
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전체회원 조회</h1>
	<hr>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>성별</th>
		</tr>
		<c:forEach items="${list }" var="member" varStatus="i">
		<tr>
			<th>${i.count }</th>
			<th>${member.memberId }</th>
			<th>${member.memberName }</th>
			<th>${member.phone }</th>
			<th>${member.address }</th>
			<th>${member.gender }</th>
		</tr>
		</c:forEach>
	</table>
</body>
</html>