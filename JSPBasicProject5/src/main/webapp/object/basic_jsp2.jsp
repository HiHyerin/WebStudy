<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	내부객체 : request,response,session
	= application : 서버관리
					=> log, getInitParameter, getRealPath()
		1) 서버 정보 => getServerInfo()
		2) 버전 정보 => getMajorVersion() , minor
						3.0
					   -- --
					   major minor
	= pageContext : 각 객체 생성
					include , forward
					<jsp:include> <jsp:forward> => 페이지 흐름을 나타내줌
	= config, exception, page(=this)
	  ------- ---------(try~catch)
	  환경설정(web.xml)
	  		
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>application 객체(ServletContext)</h1>
	서버이름:<%=application.getServerInfo() %><br>
	버전:<%=application.getMajorVersion()+"."+application.getMinorVersion() %><br>
	**실제경로명:<%= application.getRealPath("/") %>
	<%--log => web.xml을 읽어 온다(xml이나, 한글변환 등록) --%>
	<!-- 
	<context-param>
	  	<param-name>driver</param-name> ==> key
	  	<param-value>oracle.jebc.driver.OracleDriver</param-value>  ==> value
  	</context-param>
	 -->
	<% //web.xml에 등록된 값을 읽을 수 있다
		String driver=application.getInitParameter("driver");
		String url=application.getInitParameter("url");
		String username=application.getInitParameter("username");
		String password=application.getInitParameter("password");
			  
		application.log("드라이버명:"+driver);
		application.log("오라클연결주소:"+url);
		application.log("사용자:"+username);
		application.log("비밀번호:"+password);
	%>
</body>
</html>





















