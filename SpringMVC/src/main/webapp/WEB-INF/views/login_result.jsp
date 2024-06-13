<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 결과 페이지</h2>
	${sessionScope.id },${sessionScope.pass }
	<c:if test="${sessionScope.id == 'admin'}">
		<p>관리자 로그인</p>
	</c:if>
</body>
</html>