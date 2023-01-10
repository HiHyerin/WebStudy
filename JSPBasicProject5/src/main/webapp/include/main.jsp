<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.main{
	margin-top: 50px;
	
}
.row{
	width: 800px;
	margin: 0px auto;
}
h1{
	text-align: center;
}

</style>
</head>
<body>
	<%
		pageContext.include("header.jsp"); // <jsp:include page="header.jsp">
	%>
	<div class="container main">
		<div class="col-sm-3">
			<%
				pageContext.include("login.jsp");
			%>
		</div>
		<div class="col-sm-9">
			<%
				pageContext.include("../food/category.jsp");
			%>
		</div>
	</div>
</body>
</html>