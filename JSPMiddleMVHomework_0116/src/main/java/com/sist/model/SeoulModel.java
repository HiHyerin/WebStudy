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
		SeoulDAO dao = new SeoulDAO();
		ArrayList<SeoulVO> list = dao.seoulListData();
	}
	
}
