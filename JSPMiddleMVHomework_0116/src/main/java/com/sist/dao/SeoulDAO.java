package com.sist.dao;

import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import com.sist.vo.SeoulVO;

public class SeoulDAO {
	private Connection conn; // 톰캣에 의해 미리 연결됨
	private PreparedStatement ps; // 송수신

	
	// Connection의 주소 얻기
	public void getConnection() {
		//탐색기 형식 => 탬색기 열기 => C드라이브 접근 => Connection 객체주소 가지고 오기
		try {
			Context init = new InitialContext();
			Context c = (Context)init.lookup("java://comp/env");
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 반환
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
			// pool에 반환 (POOL : Connection객체가 저장된 메모리 공간
			
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*
	 이름      널?       유형             
------- -------- -------------- 
NO      NOT NULL NUMBER         
TITLE   NOT NULL VARCHAR2(200)  
POSTER  NOT NULL VARCHAR2(500)  
MSG     NOT NULL VARCHAR2(4000) 
ADDRESS NOT NULL VARCHAR2(300)  
HIT              NUMBER  
	 */
	public ArrayList<SeoulVO> seoulListData(int page, String tab) {
		ArrayList<SeoulVO> list = new ArrayList<SeoulVO>();
		try {
			getConnection();
			String sql="select no,title,poster, num "
					+"from (select no,title,poster,rownum as num "
					+"from (select no,title,poster "
					+"from seoul_" + tab + " order by no ASC)) "
					+"where num between ? and ?";
					
			
			// 오라클 전송
			ps = conn.prepareStatement(sql);
			
			// ?에 값
			int rowSize=20;
			int start=(rowSize*page) - (rowSize-1); // 1 , 21 , 41, ...
			int end = rowSize * page; //20 , 40 , 50
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			// 결과값 받기
			ResultSet rs = ps.executeQuery();
			// arraylist값
			while(rs.next()) {
				SeoulVO vo = new SeoulVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				
				list.add(vo);
			}
			rs.close();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	
	// 총페이지//////////////////////////
		public int seoulTotalPage(String tab) {
			int total=0;
			try {
				getConnection();
				String sql="select ceil(count(*)/20.0) "
						+"from seoul_"+tab;
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				rs.next();
				total=rs.getInt(1);
				rs.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				disConnection();
			}
			return total;
		}
}
