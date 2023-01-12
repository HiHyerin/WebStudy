package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import com.sist.vo.FoodVO;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// POOL 안에서 Connection 주소를 얻어온다
	public void getConnection() {
		try {
			// java://comp/env => jdbc/oracle
			Context init = new InitialContext();
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");//이름으로 객체 찾기
			/*
			 	A a = new A();
			 	
			 */
			conn=ds.getConnection();
		}catch(Exception ex) {
			
		}
	}
	// Connection 사용이 종료 반환
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	// 사용
	public ArrayList<FoodVO> foodFindData(String addr, int page){
		ArrayList<FoodVO> list = new ArrayList<FoodVO>();
		try {
			getConnection();
			//sql문장
			String sql="select fno,name,poster,num "
					+"from (select fno,name,poster,rownum as num "
					+ "from (select fno,name,poster "
					+"from food_location where address like '%'||?||'%' order by fno asc)) "
					+ "where num between ? and ?";
			ps = conn.prepareStatement(sql);
			int rowSize = 20;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize * page;
			
			ps.setString(1, addr);
			ps.setInt(2, start);
			ps.setInt(3, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				String poster = rs.getString(3);
				poster=poster.substring(0,poster.indexOf("^")).replace("#", "&");
				vo.setPoster(poster);
				
				list.add(vo);
				
			}
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
		// 총페이지
		public int foodFindTotalPage(String addr) {
			int totalpage=0;
			try {
				getConnection();
				String sql="select ceil(count(*)/20.0) from food_location "
						+ "where address like '%'||?||'%'";
				//LIKE CONCAT('%',?,'%') => Mariadb => 회사입사 (mysql , pariadb)
				ps=conn.prepareStatement(sql);
				ps.setString(1, addr);
				ResultSet rs=ps.executeQuery();
				rs.next();
				totalpage=rs.getInt(1);
				rs.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
			disConnection();	
			}
			return totalpage;
		
	}


public int foodFindCount(String addr) {
	int totalpage=0;
	try {
		getConnection();
		String sql="select count(*) from food_location "
				+ "where address like '%'||?||'%'";
		//LIKE CONCAT('%',?,'%') => Mariadb => 회사입사 (mysql , pariadb)
		ps=conn.prepareStatement(sql);
		ps.setString(1, addr);
		ResultSet rs=ps.executeQuery();
		rs.next();
		totalpage=rs.getInt(1);
		rs.close();
	}catch(Exception ex) {
		ex.printStackTrace();
	}finally {
	disConnection();	
	}
	
	return totalpage;
}

public FoodVO foodDetailData(int fno) {
	   FoodVO vo = new FoodVO();
	   try {
		   getConnection();
		   String sql="select fno,name,score,poster,tel,type,time,parking,menu, price,address "
				   + "from food_location "
				   + "where fno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setFno(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setScore(rs.getDouble(3));
			vo.setPoster(rs.getString(4));
			vo.setTel(rs.getString(5));
			vo.setType(rs.getString(6));
			vo.setTime(rs.getString(7));
			vo.setParking(rs.getString(8));
			vo.setMenu(rs.getString(9));
			
			vo.setPrice(rs.getString(10));
			vo.setAddress(rs.getString(11));
			rs.close();
	   }catch(Exception ex) {
		   ex.printStackTrace();
	   }finally {
		   disConnection();
	   }
	   return vo;
}

}







