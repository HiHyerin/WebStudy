<%--
	JSP 동작과정 p.82
	----------
	1. 사용자 브라우저 주소창에서 요청 (사용자 요청은 주소창에서 한다) => 주소창은 반드시 파일명을 마지막에 첨부
		http://localhost:8080/JSPBasicProject3/jsp/basic_2.jsp
							  --------------------------------- basic_2.jsp을 브라우저에 보여달라
		*** 주소창 => 서버가 받아서 처리
	2. Web Server에서 파일을 받아서 (.html, .xml)이 아닌 경우 => 톰캣으로 전송
	3. 톰캣
		1) basic_2.jsp 를 자바로 변경 ---> 서블릿 (웹에서 실행하는 자바파일)
			  자바파일이 없는경우 생성, 이미 있는경우 수정
		2) basic_2.jsp.java ==> 컴파일  ==>  basic_2.jsp.class 파일 생성
		3) JVM
			basic_2.jsp.class 파일을 로딩
			인터프리터를 통해서 번역
			=> out.write("<html") => 메모리 안에 <html>만 출력
		4) 메모리에 저장된 html을 브라우저가 읽어서 출력 
	=========================================================================
		_jspInit() : web.xml(설정)
		_jspService() : 사용자 요청에 의한 html작성 => 메소드 안에 들어가는 소스 코딩을 한다(jsp파일)
		_jsDestroy() : 메모리 해제
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