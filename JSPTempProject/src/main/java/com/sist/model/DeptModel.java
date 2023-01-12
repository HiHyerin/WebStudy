package com.sist.model;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import com.sist.dao.*;
public class DeptModel {
	public void deptListData(HttpServletRequest request) {
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptVO> list=dao.deptListData();
		request.setAttribute("list",list);
	}
		
}
