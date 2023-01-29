<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/* 
	ES6 type="text/babel" js->jsx(vue, react)
	
	* ajax, vue, react의 사용목적
	  -> 사용 전까진 jsp파일이 계속해서 새롭게 생성되었다면
	     사용 후부턴 동일한 jsp파일 유지
	a.jsp (ajax) = 서버 = a.jsp
	-----                -----
			동일한 jsp

	자바스크립트 : 동적(태그제어) => 클릭, 변경, 마우스, 키보드
							=> 데이터를 변경하기 위해서는 어느 태그에 값을 첨부하고 스타일을 변경할건지 구분
	* 태그를 가지고 오는 방법(문서객체 모델 p.345)
	  1) 태그 1개
	  	  => document.getElementByID("아이디명") : 문서 저장(브라우저 : 출력화면)
	  		    		             --------- >> 아이디 중복없는 속성
	  		    			- Element(태그) , Attribute(속성)
	  		    			- 태그 <a href=""> : a는 클래스, href는 멤버변수 => object
	  		    			- 모든 태그명은 클래스로 인식 , 속성은 멤버변수로 인식
	  		    			- let a = document.getElementById("aaa");
	  		    				=> a.href a.target
	  	 => document.querySelector("#아이디명");   **CSS : id= #, class= .
	  	 		- 라이브러리화 Jquery : $('#aaa') : 대부분의 SM(유지보수)회사에서 jquery 사용
	  	  
	  2) 태그 여러개
	  	  => document.getElementsByName("이름")
	  	  				---------- 's'주의
	  	  		ex) <input type=text name="">
	  	  		- name을 동일하게 사용 (checkbox, radio..)
	  	  
	  	  => document.getElementsByClassName("클래스명")
	  	  => document.querySelectorAll(선택자) : 태그 선택 
	  	  => jquery ($()), vue > v-model   ,    react > $ref
	  	  				  ---------------		-------------
	  	  				  	 양방향(MVM)			  단방향(MVC)
	  	  			
	  	  			
*/
window.onload=function(){
	document.querySelector("h1").style.color='lightblue'; // ${"h1"}.css("color","lightblue").css("backgroundColor","orange")
	document.querySelector("h1").style.backgroundColor='orange';
	document.querySelector("h2").style.color='lightpink';
	document.querySelector("#id1").value="admin";
// ------------------------------  ----
//			클래스					변수
	document.getElementById("id").value="hello";
}
</script>
</head>
<body>
	<h1>hello</h1>
	<h2>JavaScript</h2>
	<input type=text id="id" size=20>
	<input type=text id="id1" size=20>
</body>
</html>