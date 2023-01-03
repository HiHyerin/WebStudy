<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<% // 자바영역
	request.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지
	String name=request.getParameter("name"); // name 받아오기
			

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<b><%=name %></b> <!-- =syso -->
</body>
</html>