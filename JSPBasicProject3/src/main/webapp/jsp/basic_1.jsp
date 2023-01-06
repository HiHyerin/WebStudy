<%--
	c/s p.17
	---
	client + server
	* client에서만 실행되는 프로그램 = HTML .. (정적페이지)
		브라우저 / 핸드폰 => 요청
	* server에서 결과값 전송 = JSP, PHP, CGI, ASP(결과값이 다 다르기 때문에 동적페이지)
		요청을 받아서 처리
		요청 받는 프로그램
			- 웹서버 : apach(java) / IIS(MS) => 요청만 받아서 응답역할
					 -----------
					 프로그램 번역기능x
			- 웹컨테이너 : tomcat(자바를 번역해서 html,xml로 변환)
						-------
						테스트용 웹서버가 있다.(50명까지 접속 가능)
											그 이상은 aws(리눅스)
		=> servlet
		  --------- server + applet (let : 가벼운 프로그램)
		     ㄴ> 순수하게 자바 => 변경시마다 컴파일을 한다 (실행 변경이 동시에x)
		     ㄴ> jsp는 스프립트 형식으로 제작하여 실행과 동시에 변경 가능
		        ----
		         ㄴ> 자바/html 분리, 보안이 취약하다, 재사용성x, 반복 코딩이 많이 존재
		         	-------------------------------------------------------
		         		ㄴ> 이 단점을 보완한 것이 mvc
		* client : 요청(httpServletRequest_
		* server : 요청 처리 후 음답 (HttpServletResponse)
		
		p.24 (좀 알고가야함)
		 jsp : 웹프로그램 언어는 동적으로 페이지를 생성하기 위한 서버측 스크립트 언어(단순한 언어)
		 		나중에 이력서 쓸 때 사용가능 언어에 쓰지 말고 사용가능기술에 적어야함
		 		사용가능언어 : java, html
		 		사용가능기술 : jsp, servlet, spring
		 		jsp안에서는 java+html
		 				 -----------
		 				 <% %> : 자바코딩
		 				 <%= %> : 브라우저에 출력
		 				 <%! %> : 메소드, 변수선언 --> 영역을 벗어나면 브라우저 일반 텍스트
		 				 
	
		p.71
	
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>