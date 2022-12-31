package com.sist.dao;
import java.util.*;
import java.sql.*;
public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public FoodDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}

	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	//맛집 카테고리 추가
	public void foodCategoryInsert(CategoryVO vo) {//30
		try {
			getConnection();
			String sql="INSERT INTO project_category VALUES(pc_cno_seq.nextval,?,?,?,?)";
			//sql문장 전송
			ps=conn.prepareStatement(sql);
			// 실행 전에 ?에 값 채워주기
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getPoster());
			ps.setString(4, vo.getLink());
			// 실행요청
			ps.executeUpdate(); // commit 포함
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
	//==========================================================
	//==========================================================
	//카테고리에서 카테고리 번호, 링크, 제목
	public ArrayList<CategoryVO> foodCategoryInfoData(){ // db에 넣기
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		try {
			//1. 연결
			getConnection();
			//2. sql 문장제작
			String sql="select cno,title,link from project_category order by cno asc";
			//3. sql문장 전송
			ps=conn.prepareStatement(sql);
			//4. sql문장 실행 요청 => 결과값 저장
			ResultSet rs=ps.executeQuery();
			// 5. arraylist에 담기
			while(rs.next()) {
				CategoryVO vo=new CategoryVO();
				vo.setCno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setLink("https://www.mangoplate.com"+rs.getString(3));
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
	
	public void foodDetailInsert(FoodVO vo) {
		try {
			getConnection();
			String sql="insert into project_food(fno,cno,poster,name,score,address,tel,type,price,parking,"
					+"time,menu,good,soso,bad) values(pf_fno_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			// ?에 값을 채운다
			ps.setInt(1, vo.getCno());
			ps.setString(2, vo.getPoster());
			ps.setString(3, vo.getName());
			ps.setDouble(4, vo.getScore());
			ps.setString(5, vo.getAddress());
			ps.setString(6, vo.getTel());
			ps.setString(7, vo.getType());
			ps.setString(8, vo.getPrice());
			ps.setString(9, vo.getParking());
			ps.setString(10, vo.getTime());
			ps.setString(11, vo.getMenu());
			ps.setInt(12, vo.getGood());
			ps.setInt(13, vo.getSoso());
			ps.setInt(14, vo.getBad());
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
}





















