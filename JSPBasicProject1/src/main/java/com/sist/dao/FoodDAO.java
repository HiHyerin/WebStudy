package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	//DBCP : 웹에서만 사용이 가능 (Connection 생성 => 톰캣에서 생성) => 접속시간을 줄일 수 있다(연결속도가 향상), 보안,
	// connection의 개수를 관리할 수 있기 때문에 쉽게 다운로드가 되지 않는다.
	// JDBC = DBCP = ORM(Mybatis,JPA)
	/*
	 	SELECT * FROM project_category
	 	WHERE cno=1;
	 	=> void save()
	 	=> CategoryVO findByCno(int cno);
	 */
	
	public void getConnection() { // 미리 만들어진 connection 객체를 가지고 온다(server.xml resource)
		try {
				// 등록된 위치로 접속(dbcp형식)
				Context init=new InitialContext(); //JNDI(메모리구조를 폴더형식으로 만듬, java naming directory interface)
				Context c=(Context)init.lookup("java://comp/env"); // 폴더명
				DataSource ds=(DataSource)c.lookup("jdbc/oracle"); // 폴더 밑 주소값
				conn=ds.getConnection();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	
	} 
	
	public void disConnection() { // 재사용하기 위해 반환
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	
	public ArrayList<CategoryVO> categoryAllData(){
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		try {
			getConnection(); // connection 가지고 오기
			String sql="select cno,title,subject,poster "
					+"from project_category "
					+"order by cno ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				CategoryVO vo=new CategoryVO();
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
