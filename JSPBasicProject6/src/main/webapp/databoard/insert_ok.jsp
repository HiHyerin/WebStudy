<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,com.sist.vo.*"%>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>

<%
	request.setCharacterEncoding("UTF-8"); // 한글변환
	// food_find는 메인으로 보내는거기 때문에 메인에 위의 문장을 써야하고
	// insert는 insert_ok로 보내기 때문에 여기다 한글변환문장을 써야함
	// => 각 jsp마다 request를 가지고 있기 때문에 보내는 jsp에서 한글변환 => form -> action 
	// get은 특별한 경우가 아니면 한글을 보내지 않는다(window10에서는 자동처리)
	// post는 한글처치가 안된다 request.setCharacterEncoding("UTF-8");
	
	String path="c:\\download"; // 업로드된 파일 저장 위치
	int size=1024*1024*100; // 업로드 파일의 최대 크기 : 100MB
	String enctype="UTF-8";	//한글파일명
	MultipartRequest mr = new MultipartRequest(request,path,size,enctype,new DefaultFileRenamePolicy());
	// p.332~335 new DefaultFileRenamePolicy() => 같은 파일명일 때 파일명 변경 a.jpg, a1.jpg, a2.jpg..
	// 업로드는 완료
	// 데이터베이스에 저장
	String name = mr.getParameter("name");
	String subject = mr.getParameter("subject");
	String content = mr.getParameter("content");
	String pwd = mr.getParameter("pwd");
	String filename = mr.getFilesystemName("upload"); // insert에 upload라 써놨음
					//mr.getOriginalFileName("upload") => 실제 사용자가 보내준 파일명
					// mr.getFilesystemName => 변경된 파일명
	DataBoardVO vo = new DataBoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	if(filename==null){ // 업로드 안된거
		vo.setFilename("");
		vo.setFilesize(0);
	}else{ // 업로드 됐을 때
		File file = new File(path+"\\"+filename);
		vo.setFilename(filename);
		vo.setFilesize((int)file.length()); // 실제 저장된 파일의 크기를 읽는다
	}
	
	// vo를 dao를 통해서 오라클로 전송
	DataBoardDAO dao = new DataBoardDAO();
	dao.databoardInsert(vo);
	
	// 화면 이동 => 목록 출력하러
	response.sendRedirect("../main/main.jsp?mode=5"); // mode가 include하는 jsp 구분자
	
%>
