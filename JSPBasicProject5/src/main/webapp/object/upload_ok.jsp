<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*" %>   

<%
	try{
		// 한글 변환
		request.setCharacterEncoding("UTF-8");
		int size=100*100*100;
		String enctype="UTF-8";
		String path=application.getRealPath("/object");
		MultipartRequest mr = new MultipartRequest(request,path,size,enctype,new DefaultFileRenamePolicy());
		// new DefaultFileRenamePolicy() => 파일명 변경(같은 파일명일 경우 뒤에 (1),(2) 붙여주는 거)
		response.sendRedirect("application.jsp?filename="+mr.getOriginalFileName("upload"));
	}catch(Exception ex){}
%>