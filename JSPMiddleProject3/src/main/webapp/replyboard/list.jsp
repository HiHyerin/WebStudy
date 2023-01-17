<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "com.sist.model.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="model" class="com.sist.model.BoardModel"/>
	<%--BoardModel model = new BoardModel() --%>
	<%-- 
		출력형식 : EL
		${출력물} => 일반 변수가 아니다
					request.getAttribute("key") => request.setAttribute("key","value")
					  => ${requestScope.key}
					  	   ------------
					  	     생략가능 = ${key}
					session.getAttribute("key") => session.setAttribute("key","value")
					  => ${sessionScope.key}
	--%>
<%
	model.boardListData(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style type="text/css">
  	.container{
  		margin-top:100px;
  	}
  	.row{
  	width: 800px;
  	margin: 0px auto;
  	}
  </style>
</head>
<body>
	<div class="container">
     <div class="row">
		<img src="qna.png" style="width: 300px;height: 300px;">
		
	</div>
	<div style="height: 5px"></div>
	<div class="row">
		<table class="table">
			<tr>
				<td>
					<a href="insert.jsp" class="btn btn-sm btn-danger">새글</a>
				</td>
			</tr>
		</table>
		
		<table class="table">
			<tr class="warning">
				<th width="10%" class="text-center">번호</th>
				<th width="45%" class="text-center">제목</th>
				<th width="15%" class="text-center">이름</th>
				<th width="20%" class="text-center">작성일</th>
				<th width="10%" class="text-center">조회수</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td width="10%" class="text-center">${vo.no }</td>
					<td width="45%" >
					<c:if test="${vo.group_tab>0 }">
						<c:forEach var="i" begin="0" end="${vo.group_tab }">
							&nbsp;&nbsp;
						</c:forEach>
						<img src="re_icon.png">
					</c:if>
					
					<c:if test="${vo.subject==msg }">
						<span style="color:gray">${vo.subject }</span>
					</c:if>
					
					<c:if test="${vo.subject!=msg }">
						<a href="detail.jsp?no=${vo.no }">${vo.subject }</a>
					</c:if>
					
					
					<a href="detail.jsp?no=${vo.no}">${vo.subject }</a>
					&nbsp;
					<c:if test="${today==vo.dbday }">
						<sup><img src="new.gif"></sup>
					</c:if>
					</td>
					<td width="15%" class="text-center">${vo.name }</td>
					<td width="20%" class="text-center">${vo.dbday }</td>
					<td width="10%" class="text-center">${vo.hit }</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</body>
</html>