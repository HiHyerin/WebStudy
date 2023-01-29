<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<!-- 주의점 : 버전이 충돌시에는 실행이 안된다 : main.jsp -->
<script type="text/javascript">
/*
 	jquery : 자바스크립트 라이브러리 => 표준화 (개발자가 전체 공통으로 사용)
 	-------------------------- 태그 제어 (css적용 , 화면출력, 자바연동(Ajax)) => 연산자, 제어문, 변수는 자바스크립트를 이용
 	
 	jquery의 핵심 : selector (태그 선택자)
 				   document.querySelector("선택자") = $("선택자")
 				   window.onload=function(){} => $(function(){}), $(document).ready(function(){})
 				   													--------------------------->> 요즘엔 잘 안씀
 				   jquery ui => 애니메이션
 	------------------------------------------------------------------------------------------------------------
 	태그제어
 	val()  =>  <input>,<select>의 값을 가지고올 때(getter) setter(val(값))
 	text() => <a href=""> 값 </a> => innerText => text(값)
 	attr() => <img src = "값"> => attr("src") => attr("src","값")
 	html() => JSON 
 	append() => 태그를 첨부할 때
 	
 	이벤트처리
 	click(function(){})
 	on("click", function(){})
 	change(function(){})
 	keyup(function(){})
 	hover(function(){})
 	mousedown(function(){})
 	
 	디자인
 	  css("속성","값")
 	  css({"속성":값, "속성":값...})
 	  
 	 
 */
 $(function(){// window.onload=function(){}, mounted = function(){} componentDidMount() useEffect(()=>{})
//																	-------------------	----------------
//																	   react class		 react function
// selector => p.371 $("선택자").이벤트처리
//					 $("선택자").값읽기/값설정
//					 $("선택자").CSS설정 => $ => EL <c:out/>
// $.ajax(), $.getJson(), $.JSON.parse()
	$('.a').css("color","green");
	$('#b').css("color","lightpink").css("backgroundColor","orange");
	$('#b').css({"color":"lightpink"},{"backgroundColor":"yellow"});
	document.querySelectorAll(".c").style.color="cyan";
	// DOMScript
 })
</script>
</head>
<body>
	<h1 class="c">Hello Jquery</h1>
	<h1 class="a">Hello Jquery</h1>
	<h1 class="c">Hello Jquery</h1>
	<h1 class="a">Hello Jquery</h1>
	<h1 id="b">Hello Jquery</h1>
	<h1 class="a">Hello Jquery</h1>
</body>
</html>


















