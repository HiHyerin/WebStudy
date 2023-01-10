package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class FoodDAO {
	// 오라클 연결 객체
		private Connection conn;
		
		// 오라클 송수신 객체 (SQL => 데이터값 받기)
		private PreparedStatement ps;
		
		// 오라클 연결 주소
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		
		// 드라이버 연결 (p.277)
		public FoodDAO(){
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(Exception ex) {}
		}
		// 오라클 연결
		public void getConnection() {
			try {
				conn=DriverManager.getConnection(URL,"hr","happy");
			}catch(Exception ex) {}
		}
		// 오라클 닫기
		public void disConnection() {
			try{
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch(Exception ex) {}
		}
		
		public ArrayList<CategoryVO> categoryListData(int no){
			ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
			try {
				getConnection();
				int s=0,e=0;
				if(no==1) {
					s=1;
					e=12;
					
				}else if(no==2) {
					s=13;
					e=18;
					
				}else if(no==3) {
					s=19;
					e=30;
				}
				String sql="select cno,title,subject,poster "
						+"from project_category "
						+"where cno between ? and ?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, s);
				ps.setInt(2, e);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					CategoryVO vo = new CategoryVO();
					vo.setCno(rs.getInt(1));
					vo.setTitle(rs.getString(2));
					vo.setSubject(rs.getString(3));
					vo.setPoster(rs.getString(4));
					
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
}



















