<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL 연산자<h1>
<h2>산술연산자 (+ - * /(div) %(mod)): 문자열 결합은 +=, null은 자동으로 0으로 인식</h2>
<%--  &#36 = $ --%>
&#36;{10+10}=${10+10 }<br>
&#36;{"10"+10}=${"10"+10 }<br>
<%--&#36;{"10 "+10}=${"10 "+10 }<br>  => 큰따옴표 안에 공백 있으면 오류 --%>

&#36;{null + 100} = ${null + 100 }<br> null값은 0으로 취급

&#36;{"Hello" += "EL"} = ${"Hello" += "EL"} <br>+= : 문자열 결합

&#36;{10-5} = ${10-5 }<br>
&#36;{"10"-5} = ${"10"-5 }<br>
&#36;{"10"*5} = ${"10"*5 }<br>
&#36;{10/3} = ${10/3 }<br> 정수/정수 = 실수
&#36;{10 div 3} = ${10 div 3 }<br> 
&#36;{10 % 3} = ${10 % 3 }<br> 
&#36;{10 mod 3} = ${10 mod 3 }<br>

<h2>비교연산자(결과:true/false - 조건문 사용시)</h2>
<h3>비교연산자 종류(==(eq), !=(ne), <(lt), >(gt), <=(le), >=(ge))</h3>
<h3>비교연산자 주의점 : 문자열, 날짜, 숫자가 동일하다.</h3>
"hong" == "hong"<br>
"hong" != "hong" <br>

&#36;{10==10} = ${10==10 }<br> <%-- 결과값 = true/false --%>
&#36;{10 eq 10} = ${10 eq 10 }<br> 
&#36;{"hong" eq "hong"} = ${"hong" eq "hong"}<br> 
&#36;{"hong" == "hong"} = ${"hong" == "hong"}<br> 
&#36;{"hong" != "hong"} = ${"hong" != "hong"}<br> 
&#36;{"hong" ne "hong"} = ${"hong" ne "hong"}<br> 
&#36;{"hong" < "hong"} = ${"hong" < "hong"}<br> 
&#36;{"hong" lt "hong"} = ${"hong" lt "hong"}<br> 
&#36;{"hong" <= "hong"} = ${"hong" <= "hong"}<br> 
&#36;{"hong" gt "hong"} = ${"hong" gt "hong"}<br> 
&#36;{"hong" < "hong"} = ${"hong" < "hong"}<br> 
&#36;{"hong" le "hong"} = ${"hong" le "hong"}<br> 
&#36;{"hong" >= "hong"} = ${"hong" >= "hong"}<br> 
&#36;{"hong" ge "hong"} = ${"hong" ge "hong"}<br> 

<h2>논리연산자 (&&(and:직렬),||(or:병렬), not())</h2>
&#36;{(10==10) && (10!=10)} = ${(10==10) and (10!=10) }<br>
&#36;{(10==10) || (10!=10)} = ${(10==10) or (10!=10) }<br>
&#36;{(10==10) && (10!=10)} = ${(10==10) and not(10!=10) }<br>
<%-- not(10!=10) : 10과 10이 다르지 않다  --%>

<h2>삼항 연산자 (조건?값1:값2)=if~else => 조건이 true면 값1, false면 값2</h2>
<h3> => page 변경할 때</h3>
&#36;{(10==10)?'A':'B'}=${(10==10)?'A':'B'}
<%--  page=${curpage>1?vurpage-1:curpage} : style적용 --%>
<h1>Empty 연산자 : true/false</h1>
<%
	request.setAttribute("name", "");
%>
&#36;{empty name} = ${empty name }<br>
${name=="" }

</body>
</html>