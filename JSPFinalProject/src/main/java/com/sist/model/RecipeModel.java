package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;
import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class RecipeModel {
	@RequestMapping("recipe/list.do")
	public String recipe_list(HttpServletRequest request, HttpServletResponse response) {
		
		// 사용자가 보내준 페이지를 받는다
		String page = request.getParameter("page");
		if(page == null)
			page = "1";
		int curpage = Integer.parseInt(page); // 현재페이지
		
		// 오라클 연결
		RecipeDAO dao = new RecipeDAO();
		
		// 데이터 읽기
		List<RecipeVO> list = dao.recipeListData(curpage);
		int count = dao.recipeRowCount();
		int totalpage = (int)(Math.ceil(count/20.0));
		
		// 이전/다음 형태로 만들면 안됨 (데이터가 너무 많기 때문에)
		final int BLOCK = 10;
		int startPage = ((curpage-1)/BLOCK*BLOCK) + 1;
		int endPage = ((curpage-1)/BLOCK*BLOCK) + BLOCK;
		
		if(endPage>totalpage)
			endPage = totalpage;
		
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		// request에 담아서 jsp로 전송
		request.setAttribute("main_jsp", "../recipe/list.jsp");
		//_ok : sentRedirect
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
}
