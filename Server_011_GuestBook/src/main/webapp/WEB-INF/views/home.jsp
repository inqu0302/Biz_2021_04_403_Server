<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="${rootPath}/static/css/home.css?ver2021-05-20-002" rel="stylesheet" />

<style>
	table#gblist tr:hover {
		cursor: pointer;
		background-color: #ddd;
	}
	
</style>

<script>
	document.addEventListener("DOMContentLoaded",function(){
		document.querySelector("table#gblist")
		.addEventListener("click",function(ev){
			
			// 가장 안쪽 tag(TD)의 이름을 가져와라
			
			let tag_name = ev.target.tagName;
			if(tag_name == "TD") {
				let gb_seq = ev.target.closest("TR").dataset.seq
				
				document.location.href ="${rootPath}/guest/view?gb_seq=" + gb_seq
			}
		}) // table의 click
		// 방명록 쓰기 button click 시작
		document.querySelector("button.btn_write")
		.addEventListener("click", function(ev){
			
			// alert("방명록쓰기")
			document.location.href = "${rootPath}/guest/insert"
			
		})
		// 방명록 쓰기 button click end
	})// 전체
</script>
<style>
	section#main{
		width:80%;
		margin:3px auto;
		border: 1px solid green;
		padding:5px
		
		background-color: green;
		/* rgba를 사용하는 대신 바탕색의 투명도를 지정*/
		
		opacity: 03;
	}
	/* form tag block tag type이기 때문에
		button tag 와 한 라인에 배치가 위하여
		dispaly typee 을 inlint-block으로 변환하고 whidth를 제한하여 사용
	
	*/
	section#main form{
		display: inline-block;
		whidth: 40%;
		margin-left: 20px;
	}
	
	section#main input{
		background-colorL while;
		padding: 5px;
		border-radius: 5px;
		border: 1px solid green;
	}
	
	section#main button{
		padding:5px;'		
		outline: 0;
		border: 0;
		border-radius: 5px;
		background-color: rgba(0,100,255,0.35);
	}
	
	section#main button:hover{
		box-shadow: 2px 2px 2px rgba(0,0,0,0.3);
	}
</style>
<body>
	<%@ include file="/WEB-INF/views/include_nav.jsp" %>	
	<section id="main">
		<form>
			<input name = "text">
		</form>
		<button class="btn_write">방명록 쓰기</button>
	</section>
	<table id="gblist">
		<tr>
			<th>작성일</th>
			<th>작성시각</th>
			<th>작성자(email)</th>
		</tr>
		<c:forEach items="${GBLIST}" var="GB">
			<tr data-seq="${GB.gb_seq}">
				<td>${GB.gb_date}</td>
				<td>${GB.gb_time}</td>
				<td>${GB.gb_writer}</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>