package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller // Model이란걸 알 수 있는 구분자
	public class MainModel {
	@RequestMapping("main/main.do")
	public String main_page(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		session.setAttribute("id", "hong");
//		session.setAttribute("admin", "y"); //header에서 설정
		
		FoodDAO dao = new FoodDAO();
		ArrayList<CategoryVO> list = dao.foodCategoryData();
		request.setAttribute("list", list); // home.jsp에서 처리
		
		
		CommonsModel.footerData(request); // Spring => AOP(인터셉트)
		
		//include할 파일명 전송
		request.setAttribute("main_jsp", "../main/home.jsp"); // main.jsp에서 처리
		//					 --------- main안에 실행되는 include파일
		
		// 쿠키 전송
		Cookie[] cookies = request.getCookies();
		List<FoodVO> cList = new ArrayList<FoodVO>();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(cookies != null) {
			if(id == null) {
				for(int i = cookies.length-1;i>=0;i--) {
					if(cookies[i].getName().startsWith("guest_food")) {
						String fno = cookies[i].getValue();
						System.out.println("i:"+i);
						FoodVO vo = dao.food_detail(Integer.parseInt(fno));
						cList.add(vo);
						System.out.println("id:"+ id);
					}
				}
			}else {
				for(int i = cookies.length-1;i>=0;i--) {
					if(cookies[i].getName().startsWith(id+"_food")) {
						String fno = cookies[i].getValue();
						FoodVO vo = dao.food_detail(Integer.parseInt(fno));
						cList.add(vo);
						System.out.println("id:"+ id);
					}
				}
			}
		}
		request.setAttribute("cList", cList);
		// mail_jsp안에 "../main/home.jsp" 값을 넣은 것
		return "../main/main.jsp";
	}
}
