package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.model.DeleteModel;
import com.sist.model.InsertModel;
import com.sist.model.ListModel;
import com.sist.model.UpdateModel;

/*
 	MVC
 	  M(Model) => dao, vo, service, manager => 사용자 요청을 받아서 처리하고 결과값을 넘겨준다
 	  V(View) => JSP (전송받은 데이터를 출력만 하는 기능)
 	  C(Controller) => 요청받고 Model을 이용해서 처리 => 처리 결과를 view에게 전송
 	  *** 요청받기 : 브라우저에서(JSP,Servlet)
 	  				jsp : 화면 출력
 	  				servlet : 기능처리(보안) : 스프링은 이미 제작(servlet)
 	  실행순서
 	    JSP
 	      *요청하는 부분			===>	Controller 		===> 	Model의 메소드 찾기
 	      <a href="">									<===
 	      <form action="">								요청->결과값
 	      ajax
 	  
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//  /Controller?cmd=list
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	// doGet() / doPost() => 통합 service()
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cmd = request.getParameter("cmd");
		if(cmd.equals("list")) {
			ListModel model = new ListModel();
			model.execute(request);
		}else if(cmd.equals("update")) {
			UpdateModel model = new UpdateModel();
			model.execute(request);
		}else if(cmd.equals("delete")) {
			DeleteModel model = new DeleteModel();
			model.execute(request);
		}else if(cmd.equals("insert")) {
			InsertModel model = new InsertModel();
			model.execute(request);
		}
		
		// 해당 JSP로 값을 전송(request)
		RequestDispatcher rd = request.getRequestDispatcher("view/"+cmd+".jsp");
		rd.forward(request, response);
	}

}







