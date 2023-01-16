<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String name="홍길동";
//${}를 이용해서 출력하려면 => request나 session을 선택해서 가져와야 한다.(setAttribute())
	request.setAttribute("name", "심청이");
	session.setAttribute("name", "박문수");
	session.setAttribute("name1", "박문수");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>일반 변수일 때 출력</h1>
이름: <%=name %><br>

<h1>EL을 이용</h1>
이름: ${name}<br>
<%--  <%= request.getAttribute("name")%>  --%>
이름 : ${requestScope.name}<br>

<h1>Session에 저장된 데이터 읽기</h1>
이름 : ${sessionScope.name }<br>
이름 : ${name }<br> <%-- => 이렇게하면 request에 저장된 값을 가지고 온다(request가 우선순위) --%>
이름 : ${name1 } 
</body>
</html>