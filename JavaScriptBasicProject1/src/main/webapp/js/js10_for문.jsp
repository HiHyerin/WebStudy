<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
window.onload=function(){
	// const => 상수형변수(값 변경x) ex. 자바에서 값을 가지고 오는 경우
	let a = 10;
	console.log("a="+a)
	a="AAA"
	console.log("a="+a)
	a=[1,2,3,4,5,"홍길동"]
	console.log("a="+a)
	a={name:"홍길동", age:20}
	console.log("이름="+a.name + ",나이=" + a.age)
	
}
</script>
<body>

</body>
</html>