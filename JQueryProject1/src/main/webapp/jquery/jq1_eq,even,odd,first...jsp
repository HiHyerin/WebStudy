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
	1. jQuery : 댑분의 브라우저에서 사용 가능 (호환성)
	2. HTML DOM(트리형태)을 쉽게 조작 가능, 간단하게 css 적용 가능
	3. 라이브러리가 많다 , 참조할 문서가 많다
	4. 오픈소스 => 사용이 자유롭다
	5. 자바스크립트를 표준화 => 형식이 동일
	6. 단점 : 현재상태에서 더이상의 업그레이드가 없다 (가상돔 - 가상메모리)
	7. 한국에도 과거 개발된 jquery가 많이 존재 => 유지,보수
	8. 속도가 약간 늦다(라이브러리가 많음 => heavy)
	9. 현재 추세로는 front(reactJS, VueJS, Jquery, TypeScript)
										------>> NodeJS
				  back(Spring(java,jsp,mvc), DB(MyBatis, JPA), 머신러닝, 딥러닝 )
				  		-------------------->> 설정파일(xml) => xml이 없는 경우(Spring-Boot, Spring5)
	========================================================
	[문법형식]
	1. main
	  - js : window.onload=function(){
		  		  js 코드
	  		 }
		
	  - Jquery : $(document).ready(function(){
		  		    jquery 코드
	  			 })	
	  			 ------------------------------
	  			 $(function(){
	  				 jquery 코드
	  			 })
	  			 
	  - vuejs : new Vue({
					mounted:function(){
						  
					  }
				  })
	  			 
	  - ReactJS : class Main extends Component{
				  componentDidMount(){
					  js
					  변수 : let, const
					  map, forEach
					  }
				  }
	  			-----------------------------------
	  				function main(){
	  					useEffect(()=>{
	  						
	  					})
	  				}
	=============================================================
	JQuery : 태그를 제어 (태그를 가지고 와서 처리)
	* 태그를 가지고 오는 방법
	  --- >> 자바의 클래스로 인식  // 속성명 : 멤버변수
	  - $(선택자:CSS) : $('태그명') , $('#Id명') , $('.Class명') , $('태그 > 태그') , $('태그 태그')
	  - 가상선택자
	  	eq() , even(짝) , odd(홀) , first , last , not .. parent , contains , checkbox ...
	  			
*/

$(function(){
	$('h1:eq(2)').css("color","lightblue");
	$('h1:eq(3)').css("color","lightpink");
	$('h1:eq(0),h1:eq(4)').css("color","violet"); // 복합(,로 구분)
	$('h2:first').css("color","olive");
	$('h2:last').css("color","olive");
	$('h3:even').css("color","yellow");
	$('h3:odd').css("color","green");
})
</script>
</head>
<body>
  <h1>Hello Jquery!</h1> <!-- eq(0) -->
  <h1>Hello Jquery!</h1> <!-- eq(1) -->
  <h1>Hello Jquery!</h1> <!-- eq(2) -->
  <h1>Hello Jquery!</h1> <!-- eq(3) -->
  <h1>Hello Jquery!</h1> <!-- eq(4) -->
  
  <h2>Hello Jquery!</h1> <!-- eq(0) -->
  <h2>Hello Jquery!</h1> <!-- eq(1) -->
  <h2>Hello Jquery!</h1> <!-- eq(2) -->
  <h2>Hello Jquery!</h1> <!-- eq(3) -->
  <h2>Hello Jquery!</h1> <!-- eq(4) -->
  
  <h3>Hello Jquery!</h1> <!-- eq(0) -->
  <h3>Hello Jquery!</h1> <!-- eq(1) -->
  <h3>Hello Jquery!</h1> <!-- eq(2) -->
  <h3>Hello Jquery!</h1> <!-- eq(3) -->
  <h3>Hello Jquery!</h1> <!-- eq(4) -->
</body>
</html>











