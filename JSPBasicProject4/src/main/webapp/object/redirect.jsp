<!-- 
	request는 jsp파일 당 하나
	jsp가 바뀌면 request가 초기화
	-> jsp가 바껴도 request 초기화 안되게 하기 위해 session(forward)
 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String id=request.getParameter("id");
	System.out.println("redirect.jsp:id=>"+id);
	// 이동
	response.sendRedirect("basic_jsp5.jsp");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>