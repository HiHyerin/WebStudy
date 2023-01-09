<!-- p.171(내장객체) ***** => spring 
	1) 내부객체(내장객체, 기본객체)
		=> 미리 객체를 생성한 다음에 사용이 가능
		=> 9개
		=> jsp 파일 => _jspService()에 필요한 코딩을 하는 파일

	 public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
     {
         final javax.servlet.jsp.PageContext pageContext;
       javax.servlet.http.HttpSession session = null;
       final javax.servlet.ServletContext application;
       final javax.servlet.ServletConfig config;
       javax.servlet.jsp.JspWriter out = null;
       final java.lang.Object page = this;
       javax.servlet.jsp.JspWriter _jspx_out = null;
       javax.servlet.jsp.PageContext _jspx_page_context = null;
       
       // JSP코딩 위치 
     
     }
	
	
	request****
	response****
	pageContext****
	session****
	application****
	config
	out****
	page
	exception
	---------------------내장객체
	
	_jspService(){  => 브라우저 화면에 출력
		JSP파일
	}
	
	
	REQUEST 클래스
	1) 요청관련 데이터 관리, 사용자 브라우저 정보 , 추가기능
		HttpSerletRequest(클래스 이름)
	2) 기능
		- 브라우저 정보 / 서버정보
			http://localhost:8080/JSPBasicProject4/object/jsp_basic1.jsp => URL
			--------------------- --------------------------------------
				   서버정보				사용자 요청 정보(URI)
				   				/----------------/
				   				    ContextPath
				= ***getRequestURL() : http://localhost:8080/JSPBasicProject4/object/jsp_basic1.jsp
				= ***getRequestURI() : JSPBasicProject4/object/jsp_basic1.jsp
				= ***getContextPath() : JSPBasicProject4
				= ***getRemoteAddr() : 사용자의 IP (ex. 조회수 - 같은 ip주소는 조회수1만 오르게)
				= getServerPort() : 8080
				= getServerInfo() : localhost
		
		
		- 요청 관련 정보
			= 사용자가 보내준 데이터(단일데이터) : getParameter()
			= 다중데이터 : checkbox : getParameterValues()
			= 사용자가 보낸 데이터(parameter) : getParameterNames()
			= 브라우저 ===> Java(2byte)
			    1byte => 2byte로 변경(디코딩)
			   java(2byte) => 브라우저(1btye) 
			    2byte 에서 1byte로 변경(인코딩)
				https://www.google.com/search?q=%EC%9E%90%EB%B0%94&oq=%EC%9E%90%EB%B0%94&aqs=chrome..69i57j35i39l2j0i433i512j0i131i433i512j0i512j0i433i512j0i512l3.637j0j15&sourceid=chrome&ie=UTF-8
				 							  --------------------
				 * %EC%9E%90%EB%B0%94(자바(String) -> byte[]로 변경 : 인코딩)
				 	한글 변환 byte[] => String (디코딩)
				 	----------------------------------------
				 	1) byte[] -> String (브라우저에서 값을 받을 때) : 디코딩
				 		request.setCharacterEncoding("UTF-8"); -> post방식에서 디코딩
				 	
				 	2) String -> byte[] (브라우저로 값을 보낼 때) : 인코딩
				 		URLEncoder.encoder(데이터,"UTF-8") => 자바/자바스크립트 동일
				 	------------------------------------------처리방식(GET/POST)
				 	GET 방식 : window10부터는 자동 처리 / 그 아래는 server.xml 파일 63번째줄에 URIEncoding="UTF-8"을 추가
				 	
				 
				 
			*** 데이터 전송
				받는 파일명? 변수명 = 값
						  -----  ---
						   key   value
				ex) a.jsp?no=10
					=> a.jsp
					   request.getParameter("no"); //10
				ex) a.jsp?id=admin&pwd=1234 (값 두개)
						request.getParameter("id"); //admin
						request.getParameter("pwd"); //1234
						*** 받는 모든 데이터값은 String
				ex) a.jsp?hobby=a&hobby=b&hobby=c
						String[] regeust.getParameterValues("hobby")
						
				- getParameterNames() => name값 가져옴(no, id, pwd ...)
	3) 추가기능
		- 사용자가 보낸 데이터 + 필요한 데이터를 추가해서 전송(MVC,Spring)
		- setAttribute(키,값) : object를 첨부 (ArrayList)
		  getAttribute(키)
	
	==================================================================
	==================================================================
	
	RESPONSE 클래스
	- HttpServletResponse
	- JSP 한개에서 한 번만 response를 할 수 있다.
	- html 파일 전송
	- cookie 전송        => html을 보낸거냐 cookie를 보낼거냐 결정(둘 중 하나만)
	
	- setHeader() : 파일 업로드, 다운로드
	- sendRedirect() : 서버에서 다른 파일로 이동     /  (나중에 forward()랑 구분)
	
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 전송값 받기(request) -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
	
}
.row{
	width: 500px;
	margin: 0px auto;
}
h1{
	text-align: center
}
</style>
</head>
<body>
	<div class="container">
		<h1>개인정보 전송</h1>
		<div class="row">
			<form method=post action="output.jsp">
			<!-- 
				method:get/post
				action : 받는 파일 지정
				전송되는 데이터 : input / select / textarea => 입력과 관련된 값
			 -->
			<table class="table">
				<tr>
					<th class="text-center" width="20%">이름</th>
					<td width="80%">
						<input type=text name="name" class="input-sm" size=15>
						<!-- request.getParameter("name") : 입력된 값을 읽어온다
							?name=홍길동&sex=남자...(키=값&키=값...) => map방식
						 -->
					</td>
				</tr>
				<tr>
					<th class="text-center" width="20%">성별</th>
					<td width="80%">
						<input type=radio name=sex checked value="남자">남자
						<input type=radio name=sex value="여자">여자
						<!-- name이 동일(그룹), 반드시 보낼 데이터를 설정한다(value) -->
					</td>
				</tr>
				<tr>
					<th class="text-center" width=20%>지역</th>
					<td width=80%>
						<select name="loc" class="input-sm">
							<option>서울</option>
							<option>강원</option>
							<option>인천</option>
							<option>경기</option>
							<option>제주</option>
							<option>부산</option>
							<!-- 
								<option>서울</option> => "서울"이 값
								<option value="seoul">서울</option> => value가 지정되면 value가 값(seoul)
								<option>이름</option>
								<option value="name">이름</option>
							 -->
						</select>
					</td>
				</tr>
				<tr>
					<th class="text-center" width=20%>취미</th>
					<td width=80%>
						<input type="checkbox" value="등산" name=hobby>등산
						<input type="checkbox" value="여행" name=hobby>여행
						<input type="checkbox" value="독서" name=hobby>독서
						<input type="checkbox" value="영화" name=hobby>영화
						<input type="checkbox" value="사색" name=hobby>사색
						<%-- 전송값 : value, name은 동일해야한다. String[] getParameterValues("hobby")  --%>
						
					</td>
				</tr>
				<tr>
					<th width=20% class="text-center">소개</th>
					<td width=80%>
						<textarea rows="8" cols="35" name="content"></textarea>
					</td>
					
				</tr>
				
				<tr>
					<td colspan="2"	class="text-center">
						<input type=submit value="전송" class="btn btn-sm btn-danger">
					</td>
				</tr>
			</table>
			</form>
		</div>
</body>
</html>



















