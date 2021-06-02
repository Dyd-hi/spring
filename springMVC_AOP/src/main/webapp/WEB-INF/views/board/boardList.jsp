<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>글쓴이</th>			
			<th>작성일</th>
		</tr>
		<c:forEach items="${list }" var="board" varStatus="i">
		<tr>			
			<th>${board.boardNo}</th>
			<th><a href="/boardView.do?boardNo=${board.boardNo}">${board.boardTitle }</a></th>
			<th>${board.boardWriter }</th>			
			<th>${board.boardDate }</th>
		</tr>
		</c:forEach>
	</table>
</body>
</html>