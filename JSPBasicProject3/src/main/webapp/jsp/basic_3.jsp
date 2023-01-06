<%--
	1. jsp
		자바 : 데이터 관리(오라클 연동)
		html : 브라우저에 출력할 용도 (데이터 관리, 오라클 연동 불가)
		--------------------------------------------------
		자바/html 구분
		------------
		  => jsp의 스크립트 요소가 등장
		  	1) 선언문 p.119 : 메소드 , 멤버변수 => 사용빈도 거의x
		  					<%! %>
		  	2) 스크립트릿 : 메소드안에 들어가는 소스코딩
		  				  메소드 호출 , 지역변수 , 제어문 , 연산자 ...
		  				  <% %>
		  	3) 표현식 : 브라우저에 자바 관련 내용 출력 , 변수값...
		  			  <%= %>
		  	4) 주석 : <!-- --> : html 주석
		  			 <%! %> , <% %> => 자바문법 사용(주석처리 //,/**/ 
		  			 							  명령문 종료시 ;)
		  			 <%= %> : ; 사용x
		  			 <% %> : 중복되면 오류 발생 (<% <%= %> %> : 오류발생)
		  
		  public void _jspService(){
		  
		  } 230106 11:48 쯤부터
--%>

<%!
	// 자바로 만들때 => 확장성, 재사용
	// 메소드(로직) => 보안성에 문제 발생
	String name="홍길동";  // 멤버변수
	// 메소드 선언
	public int add(int a,int b){
		return a+b;
	}
	public int minus(int a,int b){
		return a-b;
	}
	// 자바 => .class, jsp => 통으로 전송
%>
<%
	String name="홍길동";  // 지역변수
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		선언식에 있는 메소드 호출 (스크립트릿을 이용)
	 --%>
	 <h1><%= name %></h1> <!-- html 출력 위치 (html=<태그>~~~<태그>,<태크 속성="~~">-->
	 <%
	 	int a=10;
	 	int b=20;
	 	int c=add(a,b);
	 	int d=minus(a,b);	
	 	// 주석 문법사항 일반 자바와 동일
	 	/*주석*/
	 %>
	 <h3><%=c %></h3> <%-- ;을 사용 할 수 없다 / out.println(c) --%>
	 <h3>c</h3> <%--일반 문자 "c"가 출력 --%>
	 <h3><%=d %></h3>
</body>
</html>