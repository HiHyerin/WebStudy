<%@page import="com.sist.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no=request.getParameter("no");
	String pwd=request.getParameter("pwd");
	// dao
	BoardDAO dao = new BoardDAO();
	boolean bCheck=dao.boardDelete(Integer.parseInt(no),pwd);
	// 이동 => 목록으로 이동
	if(bCheck==true){
		response.sendRedirect("list.jsp");
	}else{
%>
		<script>
		alert("비밀번호가 틀립니다")
		history.back();
		</script>
<%	
	}
%>    
