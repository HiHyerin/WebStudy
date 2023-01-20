package com.sist.model;
import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
/*
 	MVC
 	---
 	Model : 사용자 요청을 처리해서 request에 결과값을 저장하는 역할
 			사용자 요청값, 데이터베이스 연동
 	Controller : 요청을 받는 역할 (여기서 구분자는 .do)
 				 Model 찾고 메소드 호출 (request)
 				 request를 해당 JSP에 전송
 	VIEW : Controller가 보내준 request만 출력
 	JSP (요청 => button, a, form, menu)			->	controller	=> model => dao
 			=> controller에 요청(.do)
 			=> 게시판 목록 : freeboard/list.do
 						   main/main.do
 */
import com.sist.controller.RequestMapping;
@Controller
public class FreeBoardModel {
	// 메소드
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request, HttpServletResponse response) {
		/*
		 	list.do?
		 	--------
		 	 = DispatcherServlet(Controller)
		 	 
		 	 main.do?
		 	 --------
		 	  = DispatcherServlet
		 */
		// 1. 사용자 요청값///////////////////////////////////////
		String page = request.getParameter("page");
		if(page==null)
			page="1";
		
		
		// 현재페이지 지정(사용자가 보는 페이지 지정)
		int curpage = Integer.parseInt(page);
		
		
		// 2. 데이터베이스 연동///////////////////////////////////////
		FreeBoardDAO dao = new FreeBoardDAO();
		List<FreeBoardVO> list = dao.BoardListData(curpage);
		int totalpage = dao.boardTotalPage();
		
		// 3. 결과값을 request에 담아준다///////////////////////////////////////////////////
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		request.setAttribute("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		//============================================JSP출력을 위해 전송되는 데이터
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert.do") // 조건문 (URL => freeboard/insert.do라면)
	public String freeboard_insert(HttpServletRequest request, HttpServletResponse response) {
		
		
		request.setAttribute("main_jsp", "../freeboard/insert.jsp"); // 화면출력
		return"../main/main.jsp"; // request => main.jsp.forward(request)
	}
	
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		// 사용자가 보내준 데이터를 받는다
		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd"); // <input type=password name=pwd>
		// FreeBoardVO에 묶어서 오라클에 전송
		FreeBoardVO vo = new FreeBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		FreeBoardDAO dao = new FreeBoardDAO();
		dao.boardInsert(vo);
		// 화면이동
		return "redirect:list.do"; // sendRedirect("../freeboard/list.do")
	}
	
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request, HttpServletResponse response) {
		String no = request.getParameter("no"); // 상세보기 => 1개만 출력 -> primary key
		// dao로 전송 -> 오라클에서 데이터 읽기
		FreeBoardDAO dao = new FreeBoardDAO();
		FreeBoardVO vo = dao.boardDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/detail.jsp"); // 클릭시마다 데이터가 틀린경우
		return "../main/main.jsp";
	}
}
