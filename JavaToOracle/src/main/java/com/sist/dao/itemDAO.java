package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.sist.vo.itemVO;

public class itemDAO {
	
	private Connection conn;
	private PreparedStatement ps; // sql문을 실행할 때 사용하는 인터페이스
	private String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 생성자 => 오라클 드라이버 접속??
	public itemDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
		
	}
	
	// db연결
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	
	// db연결 해제
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	
	// db에 값 넣기
	public void InsertItemDetail(itemVO vo) {
		try {
			// 1. 연결
			getConnection();
			
			// 2. sql문장 제작
			String sql="INSERT INTO item VALUES(?,?)";
			
			// 3. sql문장 전송
			ps=conn.prepareStatement(sql);
			
			// 4. ?에 값 넣기
			ps.setString(1, vo.getName());
			ps.setInt(2, vo.getPrice());
			
			// 5. sql문장 실행 요청 => 결과값 저장(commit)
			ps.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
	
	// db에서 값 가져오기
	
}
















