<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<% // �ڹٿ���
	request.setCharacterEncoding("UTF-8"); // �ѱ� ���� ����
	String name=request.getParameter("name"); // name �޾ƿ���
			

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