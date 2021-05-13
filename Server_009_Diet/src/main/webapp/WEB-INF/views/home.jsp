<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h1{
		background-color: rgba(40,200,0, 0.7);
		color: white;
		padding: 1.2rem;
		text-align: center;
	}
	a{
		text-decoration: none;
	}
	
	/* 
	CSS3를 사용한 interactive 스타일 지정
	동전(Dynamic) 스타일 지정 
	*/
	/* a tag 에 마우스가 떠 있으면 */
	a:hover{
		text-decoration: underline;
		background-color: rgba(0,0,200, 0.3);
	}
</style>
</head>
<body>
	<h1>다이어트를 도와줘!</h1>
	<%-- /diet/food/search  --%>
	<a href = "${pageContext.request.contextPath}/food/search">섭취정보 등록</a>
</body>
</html>