package com.sist.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/* p.75
 	MVC  =>  Controler(Servlet) : Spring
 			-------------------
 				요청  =========  요청 처리(Model1) : java
 				응답  =========  화면 출력(View) : jsp
 */

@WebServlet("/hello.do") // => 이름 바꿀 수 있음
// 클라이언트(브라우저)  <==>  서버
//					 주소창(주소창만 브라우저,서버 연결이 가능)

// 사용자가 url주소 마지막에 HelloServlet를 쓰면 톰캣이 HelloServlet를 호출
/*
 	클라이언트에서 어떤 값을 전송 할지 -> front end
 	요청값을 받아서 어떤 화면을 브라우저로 전송 할지 -> back-end
 	
 	ex)
 		로그인 => id,pwd
 		화면 => main
 		
 		명소 => 상세보기
 		  client : 명소 번호 전송
 		  server : 번호에 해당되는 모든 데이터를 읽어서 화면 변경
 		  
 		게시판 => 글쓰기
 		  client : 글쓴 내용을 전송
 		  server : 오라클에 저장, 화면목록으로 이동
 		----------------------------------------- 흐름(클릭 => url 확인 => 화면)
 */

/* p.75
 	서블릿 호출 순서
 	1) http://localhost/JSPBasicProject3/HelloServlet 브라우저에서 요청
 	2) 서버에서 => WAS
 		WAS에서 서블릿 클래스를 찾는다.(HelloServlet)
 		---
 		 ㄴ> web application server : 톰캣,레진..
 	3) init()메소드 호출
 	4) service()메소드 호출(html을 브라우저로 전송)
 		- doGet(), doPost()
 	5) 브라우저, 화면 이동 -> destroy()
 	=========================================================JSP에서도 동일(_jspinit(), _jspservice(), _jspdestroy())
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	// Tomcat에 의해 호출(call back함수)
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 멤버 변수의 초기화, 자동로그인, 파일읽기(COokie) => 생성자 역할
		System.out.println("서블릿 초기화 메소드 호출..");
	}

	
	public void destroy() {
		// TODO Auto-generated method stub
		// 브라우저를 종료, 파일 이동, 새로고침 => 자동 메모리 해제
	}

	
	/*
	 	HTML
	 	 |
	 	 데이터를 전송 -> 서버가 받아서 처리(자바)
	 	 	|
	 	   데이터 전송 방식(get/post/put/delete)	=> 다른 프로그램하고 연동해서 처리(RestFul)
	 	   				-------- --- -------
	 	   				   | 	  ㄴupdate
	 	   				   |
	 	   				get : 데이터 전송 방식 => http://localhost/main/main.jsp?no=10; (url뒤에 데이터를 묶어서 전송)
	 	   															---------------
	 	   															main.jsp로 no값을 전송(메소드가 없기 때문에 매개변수를 이용할 수 없다)
							   형식 : ?no=
							   단순한 검색어, 페이지 번호 전송 등 보안과 관련없는 데이터 전송 시 사용
							   
						post : no=10 을 감춰서 보내준다 (URL 뒤에 데이터가 안보인다)
							   보안과 관련된 데이터(id, pwd, 데이터가 많은 경우..)
	 */
	
	
	// service부분 => 브라우저 화면을 출력하는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1. GET/POST가 지정되지 않은 경우에는 get이 호출된다(default)
			/*
			 	서버로 전송
			 	---------
			 	HTML
			 	  <a>   :  get방식만 사용 가능, 데이터 전송(?변수=값)
			 	  <form> : get/post ***
			 	  
			 	JavaScript
			 	  location.href=""   :  get
			 	  Ajax***
			 	    type=POST,GET...
			 	  VueJS***
			 	    axios.get(), axios.post()
			 	  ReactJS***
			 	    axios.get(), axios.post()
			 	
			 	Java
			 	  sendRedirect()   : get
			 */
		// 1. 브라우저에 보내는 형식을 지정 => text/html , text/xml , text/plain
		response.setContentType("text/html;charset=UTF-8");
		
		// 2. 어떤 브라우저로 전송할 지 설정
		PrintWriter out=response.getWriter();
			// getWriter() : 요청한 브라우저의 정보
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello Servlet</h1>");
		out.println("</body>");
		out.println("</html>");
	}

	
	// service부분 => 브라우저 화면을 출력하는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
