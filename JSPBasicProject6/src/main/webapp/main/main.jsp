<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.change.*"%>
<%--
	JSP => 자바 코딩영역
		1) <% %> : 스크립트 = 자바 일반 코딩(제어문 , 메소드 호출 , 변수 선언)
					MVC, Spring -> 일반 자바파일로 변경
		2) <%= %> : 표현식 = 변수나 메소드의 리턴값을 출력
					mvc,spring => ${}
		3) 제어문
			<%
				for(){
			%>
			<%
				}
			%>
			=> JSTL
				<c:forEach>
					출력
				<c/forEach>
				
				<%
					if(){
				%>
				<%
					}
				%>
				=> <c:if>
					출력
				   </c:if>
			----------------------------------html에서 제어문 사용(ThymeLeaf)
			
	==> 지시자(선언)
		<%@ %> : 정보, 라이브러리 로딩
		*** page
			contentType="text/html;charset=UTF-8"
			errorPage=""
			isErrorPage=""
			import=""
		*** taglib
			prefix="c" => <c:~> : 접두어
			uri=""
		include => 액션태그로 대체 <jsp:include>
	==> 내부객체
		request
		response
			=> setHeader() : 업로드 / 다운로드
			=> sendRedirect() : 실행할 파일 지정 => request가 초기화
		session : 서버에 클라이언트 정보 저장
			=> setAttribute() 저장
			=> getAttribute() 읽기
			=> invalidate() : 전체해제
			=> get 
		application
		out
		pageContext
			=> 웹 흐름(페이지 연결) => 파일(=페이지)
		---------------MVC , Spring
		exeception
		config
		page
		--------------- 사용빈도 거의 없음
	==> 액션태그
	==> 자바빈 : ~VO(Spring) , ~DTO(MyBatis) , ~Bean => 데이터를 모아서 전송할 목적
				------------------
				캡슐화 방식을 이용
	==> 데이터베이스 / JDBC => DBCP(1차 프로젝트) => 접속속도가 빠르게 (미리 Connection을 만든다)
		----------------- getConnection(), 보안 => 일반화(모든 개발업체에서 사용이 된다) => MyBatis
		----------------------------------------- 일반 JSP(Model1방식 => 사이트 개발용이 아니고 홈페이지) : 2000~2003
		Model2 => 자바 / HTML => 패턴(MVC) => Back-End , Front-End
				  Front-End (ReactJS => Redux) , (VueJs => Vuex)
 --%>
 <%

 // 화면 변경에 대한 요청값을 받는다
 request.setCharacterEncoding("UTF-8");
 String mode = request.getParameter("mode");
 // 처음 한번은 default지정
 if(mode==null)
	 mode="0";
 String jsp=JspChange.change(Integer.parseInt(mode));
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style type="text/css">
  	.container{
  		margin-top:100px;
  	}
  	.row{
  	width: 960px;
  	margin: 0px auto;
  	}
  </style>
</head>
<body>
	<%--
		메뉴 : include(pageContext) : <jsp:include> : pageContext.include() --%>
	 <jsp:include page="header.jsp"></jsp:include>
	 <div class="container">
	 	<%--화면 출력 : 사용자가 메뉴 클릭시마다 화면 변경 --%>
	 	<jsp:include page="<%=jsp %>"></jsp:include>
	 </div>
</body>
</html>