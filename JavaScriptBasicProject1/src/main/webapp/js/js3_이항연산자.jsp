<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--
	이항연산자
	= 산술연산자 ( + , - , * , / , % )
				/ 나누기
				=> 자바 (정수/정수) => 정수
				=> 자바스크립트  (정수/정수) => 실수
	= 비교연산자 ( ===, !== , < , > , <= , >= ) : 결과값 boolean => 조건문
				== , != (혼용) => 경고 출력
	= 논리연산자 ( && , || )
	= 대입연산자 ( = , += , -= )
  -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let a = 5;
	let b = 2;
	console.log("a+b= " + (a+b));
	console.log("a-b= " + (a-b));
	console.log("a*b= " + (a*b));
	console.log("a/b= " + (a/b)); // 2.5
	console.log("a%b= " + (a%b)); // 1
	
	/*
		a%b   양수
		-a%b  음수
		a%-b  양수
		-a%-b 음수
	*/
	// 문자열일 경우 -> 산술처리 (문자열 결합) => +는 문자열 결합 , 나머지 연산자는 자동으로 숫자변환 후에 연산 처리
	
	// + : 문자열 결합 , 산술처리
	let c = "10";
	let d = '20';
	// => string형
	console.log("c+d=" + (c+d)); // 1020(문자형)
	console.log("c-d=" + (c-d)); // 문자형을 숫자형으로 변환 -10
	console.log("c*d=" + (c*d)); // 200
	console.log("c/d=" + (c/d)); // 0.5
	console.log("c%d=" + (c%d)); // 10
	
	let e = "10"
	let f = 20;
	console.log("e+f=" + (e+f)); // 1020(문자열+숫자 = 문자열)
	
	// 주의점
	let m ='A'
	let n = 10;
	console.log("m+n= "+(m+n)) // A10
	console.log("m-n= "+(m-n)) // 오류(연산결과가 없다 = NaN)
	
	let k =10;
	let p = 0;
	console.log("k/p= "+(k/p)) // infinity => 0으로 나눌 수 없다는 의미
	
	/*  
		undefined => 변수의 초기화가 안된 상태 , 변수선언이 없는 경우
					 let a
					 console.log(j)
		NaN		=> 문자열 + 숫자(연산처리가 안된 상태)
		Infinity => 0으로 나눌 경우
		숫자형 문자열 	=> "10" , '100' : +는 문자열로 결합, 나머지 산술연산자는 숫자로 변환후 처리
		숫자형이 아닌 경우 'AB'+1 	=> NaN
		10/0 => Infinity
		
		=> 자바스크립트 자체에서 데이터를 읽지 못한다 => 자바를 이용해서 전송
		   자바 => 자바스크립트로 전송
		   		=> co , arrayList => {} , [] (= JSON)
		
	*/
	
}
</script>
</head>
<body>

</body>
</html>