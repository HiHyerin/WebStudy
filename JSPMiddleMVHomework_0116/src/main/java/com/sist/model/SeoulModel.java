package com.sist.model;
import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.SeoulDAO;
import com.sist.vo.SeoulVO;
public class SeoulModel {
	public void seoulListData(HttpServletRequest request, HttpServletResponse response) {
		// 자바에서 오라클에 있는 데이터를 읽어 온다
		// 1. 사용자가 보내준 값을 받는다 : page
	 	String strPage=request.getParameter("page");
		if(strPage==null) // 첫 페이지
			strPage="1";
		
		
		int curpage=Integer.parseInt(strPage);
		SeoulDAO dao = new SeoulDAO();
		ArrayList<SeoulVO> list = dao.seoulListData(curpage, "location");
		
		request.setAttribute("list", list);
		
		// 총페이지
		int totalpage = dao.seoulTotalPage("location");
		
		// 블록별 페이지
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		if(endPage>totalpage)
			endPage=totalpage;
	}
	
}
