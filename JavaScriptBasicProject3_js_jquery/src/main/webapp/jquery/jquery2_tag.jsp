<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
/*
 	태그  (Jquery, Jsoup, css의 selector)
 	1) 태그명 $('태그명')
 	2) id명 $('#아이디명')
 	3) class명 $('.클래스명')
 	4) 후손 $('태그명 태그명')
 	5) 자손 $('태그명 > 태그명') p.127
 	6) 인접 $('태그명 + 태그명') $('태그명~태그명')
 	7) 가상선택자 : hover, nth-child, eq 
 	
 	------------------------------------------
 	jquery 에서 지원하는 함수, 이벤트 종류
 */
$(function(){
	// img 7개 선택 => this(hover가 된 이미지를 this라 함)
	$('img').css('opacity',0.2);
	$('img').hover(function(){
		$(this).css('opacity',1.0) // if
	}, function(){
		$(this).css('opacity',0.2) // else
	})
})
</script>
<style type="text/css">
img{
	width: 150px;
	height: 220px;
}
</style>
</head>
<body>
	<img src="1.jpg">
	<img src="2.jpg">
	<img src="3.jpg">
	<img src="4.jpg">
	<img src="5.jpg">
	<img src="6.jpg">
	<img src="7.jpg">
	
	
</body>
</html>