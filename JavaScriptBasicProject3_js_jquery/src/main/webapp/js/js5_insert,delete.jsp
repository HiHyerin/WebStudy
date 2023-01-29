<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// 전역변수 = 자바에서 보내준다
let sawons = [
	{sabun:1, name:"송중기", dept:"개발부", job:"대리", pay:4500},
	{sabun:2, name:"강동원", dept:"건축", job:"과장", pay:5500},
	{sabun:3, name:"서강준", dept:"자재부", job:"사원", pay:3500},
	{sabun:4, name:"송혜교", dept:"총무부", job:"과장", pay:5500},
	{sabun:5, name:"김희어라", dept:"영업부", job:"대리", pay:4500},
]

window.onload=function(){
	sawonList();
	sawonDetail(3);
	sawonInsert();
	sawonDelete();
	sawonSlice();
}

const sawonList=function(){
	console.log(sawons);
}

const sawonDetail=(sabun)=>{
	let sawon = sawons.find(sa=>sa.sabun==sabun); // sa={sabun:1, name:"송중기", dept:"개발부", job:"대리", pay:4500} // find=> JSON
	console.log(sawon);
}

 const sawonInsert=function(){
	 sawons.push({sabun:6, name:"전여빈", dept:"영업부", job:"사원", pay:4500});
	 console.log(sawons);
 }
 
 const sawonDelete=()=>{
	 sawons.pop();
	 console.log(sawons);
 }
 
 const sawonSlice=()=>{
	 let sa = sawons.slice(1,3);
	 //					start end
	 console.log(sa)
	 /* 
	 	let arr=[1,2,3,4,5,6,7]
	 	let a = arr.slice(1,5)
	 	a=[2,3,4,5] => 페이징 할 때 사용
	 */
 }

</script>
</head>
<body>

</body>
</html>