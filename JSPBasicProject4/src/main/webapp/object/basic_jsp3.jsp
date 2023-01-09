<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	response : 서버 응답에 대한 정보 , 헤더 정보, 변환정보
	=> getContentType() : 실행시 어떤 형식의 데이터인지 확인
		text/html , text/xml, text/plain
		(html)		 (xml)	   (JSON)
	=> getCharacterEncoding
	=> setHeader() -- 업로드/다운로드
	***=> sendRedirect() => get방식, 화면을 서버에서 변경
	*** jsp 한개의 파일에서 한 개만 응답 가능(cookie or html
 --%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>response 메소드 : HttpServletResponse</h1>
	전송 방식 : <%= response.getContentType() %>
	<!-- 전송 방식 : text/html;charset=UTF-8 => html형식으로 브라우저로 보낸다, 한글처리 --><br>
	한글 코드: <%=response.getCharacterEncoding() %>
	<%--
		한글변환
		EUC-KR
			전송 : EUC-KR <-> EUC-KR (window용 , linux에서는 인식하지 못함) => AWS(리눅스)
				  EUC-KR <-> UTF-8
		
		UTF-8
			전송 : UTF-8 <-> EUC-KR (한글 깨짐)	
				  UTF-8 <-> UTF-8
	 --%>
</body>
</html>