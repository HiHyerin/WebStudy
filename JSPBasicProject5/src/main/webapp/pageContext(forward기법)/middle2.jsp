<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	System.out.println("middle2:id="+id);
	System.out.println("middle2:pwd="+pwd);
	
	pageContext.forward("output.jsp"); // mvc => forward (request를 계속 유지) => url을 유지한 상태로 화면만 이동
%>  