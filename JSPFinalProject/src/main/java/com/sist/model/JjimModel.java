package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.JjimDAO;
import com.sist.vo.JjimVO;

@Controller
public class JjimModel {
	@RequestMapping("jjim/jjim_insert.do")
	public String jjim_insert(HttpServletRequest request, HttpServletResponse response) {
		
		String fno = request.getParameter("fno");
		HttpSession session = request.getSession(); // 아이디 넘기려면 필요
		String id = (String)session.getAttribute("id"); // 아이디 넘김
		JjimVO vo = new JjimVO();
		vo.setNo(Integer.parseInt(fno));
		vo.setId(id);
		
		// 데이터베이스 연동
		JjimDAO dao = new JjimDAO();
		dao.jjimInsert(vo);
		return "redirect:../food/food_detail.do?fno="+fno;
		
	}
}
