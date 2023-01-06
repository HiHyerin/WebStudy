<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.vo.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="bean" class="com.sist.vo.MyBean">
	<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>

<!--  위의  useBean은 이거랑 똑같은 코딩
MyBean bean = new MyBean();  
			== <jsp:useBean id="bean" class="com.sist.vo.MyBean">
	
	//1. 사용자가 보내준 데이터를 받는다
	// 1-1 한글변환
	request.setCharacterEncoding("UTF-8");
	// 1-2 데이터 받기  =>  <jsp:setProperty name="bean" property="*"/>
	String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	String loc=request.getParameter("loc");
	String content=request.getParameter("content");
	
	bean.setName(name);
	bean.setSex(sex);
	bean.setLoc(loc);
	bean.setContent(content);
	// => bean 역할 : 데이터 모아서 오라클로 보냄
	
	p
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>개인 정보 출력</h1>
	이름 : <jsp:getProperty property="name" name="bean"/><br> <%-- getName() --%>
	성별 : <jsp:getProperty property="sex" name="bean"/><br> <%-- getSex() --%>
	지역 : <jsp:getProperty property="loc" name="bean"/><br> <%-- getLoc() --%>
	소개 : <jsp:getProperty property="content" name="bean"/><br> <%-- getContent() --%>
</body>
</html>