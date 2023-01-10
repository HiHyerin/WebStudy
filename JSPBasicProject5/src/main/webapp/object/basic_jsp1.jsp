<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb"%>

<%--
out: JspWriter
1) 출력 스트림 : 브라우저에서 읽어가는 메모리(신뢰성)
=>

---%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int size = out.getBufferSize();
		int re=out.getRemaining();
		out.println("total:"+size);
		out.println("remain:"+re);
	%>
	<br>
	총버퍼 크기(getBufferSize):<%="total:"+size %><br>
	남아있는 버퍼(getRemaining):<%="remain:"+re %><br>
	사용중인 버퍼:<%= size-re %>
</body>
</html>