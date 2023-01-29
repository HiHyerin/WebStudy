<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style type="text/css">
  	.container{
  		margin-top:30px;
  	}
  	.row{
  	width: 960px;
  	margin: 0px auto;
  	}
  </style>
  
 <script type="text/javascript">
 $(function(){
	 $.ajax({
		 type : 'post', // get이냐 post냐
		 url : 'food_location.jsp', // 실행결과를 가지고 오는 url
		 data :{"page":1}, // ?page=1
		 
		 success : function(result){ // html을 읽어온다 => html, json // success : 성공하면
			 alert(result)
			 $('.row').html(result);
		 }
		 
		 error:function(ex){ // error : 실패하면
			 alert("에러발생")
		 }
	 })
 })
 </script>
</head>
<body>
  <div class = "container">
  	<div class="row">
  		
  	</div>
  </div>
</body>
</html>