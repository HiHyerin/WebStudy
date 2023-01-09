<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%--
	화면 출력 부분이 아니고 전송된 데이터를 받아서 요청 처리만 한다
	화면 출력 부분으로 이동을 한다(sendRedirect("list.jsp"))
	요청처리
		=> 목록 (insert, delete할 시)
		=> 상세보기(update)
 --%>   
 
 <jsp:useBean id="dao" class="com.sist.dao.BoardDAO"/><%--BoardDAO dao=new BoardDAO(); --%>
 <%
 	request.setCharacterEncoding("UTF-8");
 
 %>
 
 <jsp:useBean id="vo" class="com.sist.dao.BoardVO">
 	<jsp:setProperty name="vo" property="*"/>
 </jsp:useBean>
 
 <%
 	dao.boardInsert(vo);
 	response.sendRedirect("list.jsp");
 %>
 
 <%-- request
 	// 1. 사용자가 보내준 데이터를 받는다
 	// 1-1. 한글변환
 	request.setCharacterEncoding("UTF-8");
 	// <jsp:setProperty name="vo" property="*"/> jap action tag
 	String name=request.getParameter("name");
 	String subject=request.getParameter("subject");
 	String content=request.getParameter("content");
 	String pwd=request.getParameter("pwd");
 	
 	// 1-2 vo에 값을 채운다
 	BoardVO vo=new BoardVO();
 	vo.setName(name);
 	vo.setSubject(subject);
 	vo.setContent(content);
 	vo.setPwd(pwd);
 	
 	// 1-3 오라클로 전송
 	BoardDAO dao = new BoardDAO();
 	dao.boardInsert(vo);
 	
 	// 1-4 화면 이동(값 받을때는 request 이동할때는 response)
 	response.sendRedirect("list.jsp");
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>