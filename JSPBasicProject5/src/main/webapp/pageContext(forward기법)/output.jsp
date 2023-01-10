<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id=request.getParameter("id");
	String pwd=pageContext.getRequest().getParameter("pwd");
	// 두개가 의미가 똑같음
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	ID:<%=id %> <br>
	password:<%=pwd %>
</body>
</html>