<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	// 한줄주석 (p.289)
	/*
		여러줄 주석
	*/
	window.onload = function(){
		// main함수
		// 1. 변수선언
		let a = 10;
		let b = 10.5;
		let c = 'ABC'; 
		let d = "ABC";
		let e = [1, 2, 3, 4, 5, "홍길동"]; // Array배열은 Object형이기 때문에 다른 데이터형 첨부도 가능
		let f = {name:"홍길동", sex:"남자", age:20};
		// ;은 생략 가능, 대소문자 구분 , 스크립트 = 단순한 언어
		
		// 출력
		console.log("a="+a);
		console.log("b="+b);
		console.log("c="+c);
		console.log("d="+d);
		console.log("e="+e);
		console.log("f="+f);
		
		// 출력 => 브라우저에 출력
		document.write("a="+a+"<br>");
		document.write("b="+b+"<br>");
		document.write("c="+c+"<br>");
		document.write("d="+d+"<br>");
		document.write("e="+e+"<br>");
		document.write("f="+f+"<br>");
		document.write("<hr>");
		
		document.write("a="+typeof a+"<br>")
		document.write("b="+typeof b+"<br>")
		document.write("c="+typeof c+"<br>")
		document.write("d="+typeof d+"<br>")
		document.write("e="+typeof e+"<br>")
		document.write("f="+typeof f+"<br>")
		let i
		document.write("i="+typeof i+"<br>")//undefined
		document.write("j="+typeof j+"<br>")//undefined
		let k='';
		document.write("k="+typeof k+"<br>")//string
		
		let o = 10
		document.write("o="+typeof o +"<br>")
		
		o ='ABC';
		document.write("o="+typeof o +"<br>")
		
		o={name : "홍길동"}
		document.write("o="+typeof o +"<br>")
			
	}
</script>
</head>
<body>

</body>
</html>