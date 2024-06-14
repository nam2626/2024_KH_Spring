<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	window.onload = () => {
		document.querySelector('.true').addEventListener("click",function(){
			fetch("/map").then(response => response.json())
			.then(result => {
				let tag = '';
				tag += `<p>resultCount : \${result.resultCount}</p>`;
				tag += `<p>msg : \${result.msg}</p>`;
				result.list.forEach(item => {
					tag += `<p>`;
					tag += `\${item.memberId},\${item.name},\${item.age}`;
					tag += `</p>`;
				});
				document.querySelector("#result").innerHTML = tag;
			});
		}) ;
	}
</script>
</head>
<body>
	<button type="button" class="true">정상 데이터 호출</button>
	<button type="button" class="false">Exception 호출</button>
	<hr>
	<div id="result"></div>
</body>
</html>










