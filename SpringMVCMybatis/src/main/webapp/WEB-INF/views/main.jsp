<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function eventDelete(){
		//삭제 버튼을 클릭했을때, 클릭한 버튼의 학생번호를 경고창으로 출력
		document.querySelectorAll('.btn_delete').forEach(item => {
			item.onclick = () => {
				const studentNo = item.parentNode.parentNode.querySelector('input[name=studentNo]').value;
				/* alert(studentNo); */
				fetch("/student/delete/"+studentNo,{
					method : "DELETE"
				}).then(response => response.text())
				.then(result => {
					alert(result);
					location.reload();
				});
				
			}
		})
	}
	function eventUpdate(){
		document.querySelectorAll('.btn_update').forEach(item => {
			item.onclick = () => {
				let value = {};
				item.parentNode.parentNode.querySelectorAll('input').forEach(item => {
					console.log(item.name, item.value)
					//[]로 묶어야 변수에 있는 값으로 키값으로 지정
					value = {...value, [item.name] : item.value};
				});
				console.log(value);
				fetch('/student/update/'+value.studentNo,{
					method:"PUT",
					headers:{
						"Content-Type": "application/json"
					},
					body : JSON.stringify(value)
				}).then(response => response.json())
				.then(result => {
					alert(result.msg);
				})
			}
		});
	}
	window.onload = () => {
		eventDelete();
		eventUpdate();
		
		document.querySelector("#btnSearch").onclick = () => {
			const kind = document.querySelector("#kind").value;
			const search = document.querySelector("#search").value;
			
			const url = `/student/search?kind=\${kind}&search=\${search}`;
			fetch(url).then(response => response.json())
			.then(result => {
				//검색결과를 tbody에 출력
				console.log(result);
				let tag = '';
				result.forEach(item => {
					console.log(item)
					tag += '<tr>';
					tag += `<td><input type="text" name="studentNo" value="\${item.studentNo}"></td>`;
					tag += `<td><input type="text" name="studentName" value="\${item.studentName }"></td>`;
					tag += `<td><input type="text" name="studentScore" value="\${item.studentScore }"></td>`;
					tag += `<td><input type="text" name="majorNo" value="\${item.majorNo}"></td>`;
					tag += `<td><input type="text" name="studentGender" value="\${item.studentGender }"></td>`;
					tag += '<td><button class="btn_update">수정</button>'
					tag += '<button class="btn_delete">삭제</button></td>';
					tag += '</tr>';
				});
				`
				document.querySelector('#area').innerHTML = tag;
				
				//동적엘리먼트 이벤트 처리
				eventDelete();
				eventUpdate();
				
			})
						
		}
		
	}

</script>
</head>
<body>
	<hr>
	<table>
		<thead>
			<tr>
				<td colspan="6">
					<!-- 검색창  -->
					<select id="kind">
						<option value="studentNo">학번</option>
						<option value="studentName">이름</option>
						<option value="studentScore">평점</option>
					</select>
					<input type="text" id="search">
					<button id="btnSearch">검색</button>				
					<span>|</span>
					<a href="/addStdView">학생정보 추가</a>
				</td>
			</tr>
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>평점</th>
				<th>학과번호</th>
				<th>성별</th>
				<th>비고</th>
			</tr>
		</thead>
		<tbody id="area">
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