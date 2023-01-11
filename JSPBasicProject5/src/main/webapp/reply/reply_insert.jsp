<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>

<%
	request.setCharacterEncoding("UTF-8");
	String fno=request.getParameter("fno");
	String msg=request.getParameter("msg");
	String id=(String) session.getAttribute("id");
	String name=(String)session.getAttribute("name");
	
	// session : id, name => 전체 프로그램에서 id를 필요로 하는 경우가 많다 : 브라우저마다 저장 공간을 한개 가지고 있다.
	// 공간을 구분하기 위해 sessionID를 준다 (채팅, 쪽지) => getId()
	/*
		전송하고 값이 필요 없는 경우(일회성) : 게시판 글쓰기 , 댓글 회원 => request
		브라우저 종료시까지 값이 필요한 경우(값 유지) : 장바구니 => session
	*/
	
	// DAO에 전송 => 메소드 제작(매개변수 필요 - 세 개 이상을 넘어가지 않도록한다/ 세 개 이상이면 배열 또는 클래스에 담아서 넘긴다(매개변수 최소화))

	ReplyVO vo=new ReplyVO();
	vo.setFno(Integer.parseInt(fno));
	vo.setId(id);
	vo.setName(name);
	vo.setMsg(msg);
	
	ReplyDAO dao = new ReplyDAO();
	dao.replyInsert(vo);
	
	// 이동
	//response.sendRedirect("../food/food_detail.jsp?fno="+fno);
	response.sendRedirect("../include/main.jsp?mode=2&fno="+fno);

%>