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
	<hr>
	<table>
		<thead>
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>평점</th>
				<th>학과번호</th>
				<th>성별</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody>
			<!-- 
				studentList 출력
				input 태그를 이용해서 출력 
			-->
			<c:forEach var="std" items="${studentList }">
				<tr>
					<td>
						<input type="text" name="studentNo" value="${std.studentNo }" readonly>
					</td>
					<td>
						<input type="text" name="studentName" value="${std.studentName }">
					</td>
					<td>
						<input type="text" name="studentScore" value="${std.studentScore }">
					</td>
					<td>
						<input type="text" name="majorNo" value="${std.majorNo}">					
					</td>
					<td>
						<input type="text" name="studentGender" value="${std.studentGender }">
					</td>
					<td>
						<button class="btn_update">수정</button>
						<button class="btn_delete">삭제</button>
					</td>
				
				</tr>
			</c:forEach>			
			
		</tbody>		
		
		
	</table>
</body>
</html>