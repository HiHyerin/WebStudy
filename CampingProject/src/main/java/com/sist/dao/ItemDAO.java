package com.sist.dao;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.sql.*;
public class ItemDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public ItemDAO() {
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
	

	// 아이템 리스트 추가
	public void itemListInsert(ItemListVO vo) {
		try {
			getConnection();
			String sql="insert into item_list_2 "
					+ "values(item_ilno_seq_2.nextval,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getPrice());
			ps.setString(2, vo.getName());	
			ps.setString(3, vo.getLink());
			
			ps.executeUpdate(); 
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
	
	
	public ArrayList<ItemListVO> itemListInfoData(){
		ArrayList<ItemListVO> list=new ArrayList<ItemListVO>();
		try {
			//1. 연결
			getConnection();
			//2.sql문장제작
			String sql="select ilno,name,link "
					+ "from item_list_2 order by ilno asc";
//			String sql="select ino,image,name,price,description,stock,status,discount,delivery_price,"
//					+ "getLike_cnt,getjjim_cnt,icno,link from item_2 order by ino asc";
			
			//3. sql문장 전송
			ps=conn.prepareStatement(sql);
			//4. sql문장 실행 요청(결과값 저장)
			ResultSet rs=ps.executeQuery();
			//5. arraylist에 담기
			while(rs.next()) {
				ItemListVO vo=new ItemListVO();
				vo.setIlno(rs.getInt(1));
				
				vo.setName(rs.getString(2));
				
				vo.setLink("https://campinglist.co.kr/"+rs.getString(3));
//				vo.setDescription(rs.getString(5));
//				vo.setStock(rs.getInt(6));
//				vo.setStatus(rs.getString(7));
//				vo.setDiscount(rs.getInt(8));
//				vo.setDelivery_price(rs.getInt(9));
//				vo.setLike_cnt(rs.getInt(10));
//				vo.setJjim_cnt(rs.getInt(11));
//				vo.setIcno(rs.getInt(12));
//				vo.setLink(rs.getString(13));;
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
	
	
	
	
	
	
	
	
	
	
	
	public void itemDetailInsert(ItemVO vo) {
		try {
			getConnection();
			String sql="insert into item_22(ino,ilno, image,name,price "
					+ "values(item_ino_seq_22.nextval,?,?,?,?)";
//			String sql="insert into item_2(ino,image,name,price,description,"
//					+ "stock,status,discount,delivery_price,like_cnt,jjim_cnt,icno) "
//					+ "values(item_ino_seq_2.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getIno());
			ps.setInt(2, vo.getIlno());
			ps.setString(3, vo.getImage());
			ps.setString(4, vo.getName());
			ps.setInt(5, vo.getPrice());
			
//			ps.setString(5, vo.getDescription());
//			ps.setInt(6, vo.getStock());
//			ps.setString(7, vo.getStatus());
//			ps.setInt(8, vo.getDiscount());
//			ps.setInt(9, vo.getDelivery_price());
//			ps.setInt(10, vo.getLike_cnt());
//			ps.setInt(11, vo.getJjim_cnt());
//			ps.setInt(12, vo.getIcno());
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
	
	
	
	
	
	
	
	
	
	

	

}















