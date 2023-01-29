<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/*
	1. 일반 for문
	2. forEach
		for(let data of 배열) => 배열에 실제 값을 가지고 온다
		{
			
		}
	3. forEach
		for(let data in 배열) => 인덱스번호(0,1,2,3...)
	
	4. map
		배열명.map(function(data){
			실제 저장된 데이터
		})
		=> 가장 많이 사용되는 형식
		배열명.map((data)=>{
			
		})
	5. forEach
		배열명.forEach(function(data){
			실제 저장된 데이터
		})
		=> 가장 많이 사용되는 형식
		배열명.forEach((data)=>{
			
		})
	
	****** 자바스크립트 (Front) => 출력 for, if
*/

window.onload=function(){
	const names=["사과","배","포도","딸기","귤"]; // 데이터 변경이 없는 경우 
	document.write("<h1>===== 일반 for문 사용법 ====</h1>")
	for(let i=0;i<names.length;i++){
		document.write(names[i]+"&nbsp;")
	}
	
	document.write("<h1>===== 일반 for of문 사용법 ====</h1>")
	// for(String s:names)
	for(let f of names){
		document.write(f+"&nbsp;")
	}
	
	document.write("<h1>===== 일반 for in문 사용법 ====</h1>")
	// for(String s:names)
	for(let f in names){
		document.write(names[f]+"&nbsp;") // 인덱스 번호 읽기
	}
	
	document.write("<h1>===== 일반 map 사용법(1) ====</h1>")
	// for(String s:names)
	names.map(function(f){
		document.write(f+"&nbsp;")
	})
	
	document.write("<h1>===== 일반 map 사용법(2) ====</h1>")
	// for(String s:names)
	names.map((f)=>{
		document.write(f+"&nbsp;")
	})
	
	document.write("<h1>===== 일반 forEach 사용법(1) ====</h1>")
	// for(String s:names)
	names.forEach(function(f){
		document.write(f+"&nbsp;")
	})
	
	document.write("<h1>===== 일반 forEach 사용법(2) ====</h1>")
	// for(String s:names)
	names.forEach((f)=>{
		document.write(f+"&nbsp;")
	})
}
</script>
</head>
<body>

</body>
</html>






