<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	window.onload = () => {
		let area = document.querySelector("#result");
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
				area.innerHTML = tag;
			});
		}) ;
		document.querySelector(".false").onclick = () => {
			fetch("/callData?data=false").then(response => response.json())
			.then(result => {
				console.log(result);
				area.innerHTML = result.result;
				if(result.errorCode)
					area.innerHTML += result.errorCode;
			}).catch(error => {
				//요청실패일때 넘어옴
				console.log(error);
			});
		}
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










