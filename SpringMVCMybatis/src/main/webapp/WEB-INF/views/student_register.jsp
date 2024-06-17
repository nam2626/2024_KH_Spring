<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	h2{
		text-align: center;
	}
	form{
		width: 700px;
		margin : 0 auto;
		display: flex;
		flex-flow: column nowrap;
	}
	input[type=text],input[type=password],select {
		height: 70px;
		border:1px solid black;
		border-radius: 10px;
		margin-bottom: 5px;
		margin-top: 5px;
		font-size: 20px;
		padding-left: 20px;
	}
	button{
		margin-bottom: 5px;
		margin-top: 5px;
		height: 70px;
		background-color: #e9e9e9;
		border:1px solid #191919;
		border-radius: 10px;
	}
	.gender_bar{
		font-size: 20px;
	}
</style>
</head>
<body>
	<h2>학생정보 등록 페이지</h2>
	<form action="/student/insert" method="post">
		<!-- 학번, 이름, 평점, 성별, 학과번호 -->
		<input type="text" name="studentNo" placeholder="학번 입력">
		<input type="text" name="studentName" placeholder="학생 이름 입력">
		<input type="text" name="studentScore" placeholder="학생 평점 입력">
		<div class='gender_bar'>
			<input type="radio" name="studentGender" value="M" id="g1">
			<label for="g1">남</label>		
			<input type="radio" name="studentGender" value="F" id="g2">		
			<label for="g2">여</label>		
		</div>
		<select name="majorNo">
			<c:forEach var="m" items="${requestScope.majorList }">
				<option value="${m.majorNo }">${m.majorName }</option>
			</c:forEach>
		</select>
		<button>등록하기</button>
		<button type="button">이전</button>
	</form>
</body>
</html>









