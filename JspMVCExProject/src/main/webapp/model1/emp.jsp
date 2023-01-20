<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%--
	
 --%>  
 
 <%
 	EmpDAO dao = new EmpDAO();
 	// 데이터를 읽고 출력한다
 	ArrayList<EmpVO> list = dao.empListData();
 	
 %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul>
	<%
		for(EmpVO vo : list){
	%>
		  <li><%=vo.getEname() %> - <%=vo.getJob() %></li>
	<%
		}
	%>
	</ul>
</body>
</html>