package com.sist.dao;

import java.util.*;
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
	
	
	
	 public void campingCategoryInsert(ItemCategoryVO vo)
	   {
	      try {
	         // 1. 연결
	         getConnection();
	         // 2. sql문장 전송
	         // nextval  => 다음값읽기
	         // 결과값이 없는 경우 commit 포함 =>executeUpdate()
	         String sql ="INSERT INTO item_category_2 "
	               + "VALUES(ic_icno_seq_2.nextval,?) ";
	         // sql 문장 전송
	         ps=conn.prepareStatement(sql);
	         // ? 값을 넣어 준다
	         ps.setString(1, vo.getName());   
	         // 실행 요청
	         ps.executeUpdate(); //commit       
	               
	      }catch(Exception ex)
	      {
	         ex.printStackTrace();
	      }
	      finally
	      {
	         disConnection();
	      }
	   }
	 
	 
	 public ArrayList<ItemCategoryVO> campingCategoryInfoData(){
		 ArrayList<ItemCategoryVO> list=new ArrayList<ItemCategoryVO>();
		 try {
			 getConnection();
			 //2. sql문장제작
			 String sql="select icno,name from item_category_2 order by icno asc";
			 //3. sql문장 전송
			 ps=conn.prepareStatement(sql);
			//4. sql문장 실행 요청 => 결과값 저장
			ResultSet rs=ps.executeQuery();
			//5. arraylist에 담기
			while(rs.next()) {
				ItemCategoryVO vo=new ItemCategoryVO();
				vo.setIcno(rs.getInt(1));
				vo.setName(rs.getString(2));
				
			}
			rs.close();
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }finally {
				disConnection();
			}
			return list;
	 }
	 /*
	 이름             널?       유형            
	 -------------- -------- ------------- 
	 INO            NOT NULL NUMBER        
	 NAME           NOT NULL VARCHAR2(300) 
	 IMAGE          NOT NULL VARCHAR2(260) 
	 PRICE          NOT NULL NUMBER        
	 DESCRIPTION    NOT NULL CLOB          
	 STOCK                   NUMBER        
	 STATUS         NOT NULL VARCHAR2(200) 
	 DISCOUNT                NUMBER        
	 DELIVERY_PRICE NOT NULL NUMBER        
	 LIKE_CNT                NUMBER        
	 JJIM_CNT                NUMBER        
	 ICNO                    NUMBER
	  */
	 public void itemDetailInsert(ItemVO vo) {
		 System.out.println("itemDetailInsert........");
		 try {
			 getConnection();
			 String sql="insert into item_2(ino,name,image,price,description,stock,status,"
			 		+ "discount,delivery_price,like_cnt,jjim_cnt,icno) "
			 		+ "values(item_ino_seq_2.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			 ps=conn.prepareStatement(sql);
			 // ?에 값 채우기
			 ps.setString(1, vo.getName());
			 ps.setString(2, vo.getImage());
			 ps.setString(3, vo.getPrice());
			 ps.setString(4,vo.getDescription());
			 ps.setInt(5, vo.getStock());
			 ps.setString(6,vo.getStatus());//status
			 ps.setString(7, vo.getDiscount());
			 ps.setString(8, vo.getDelivery_price());
			 ps.setInt(9, vo.getLike_cnt());
			 ps.setInt(10, vo.getJjim_cnt());
			 ps.setInt(11, vo.getIcno());
			 ps.executeUpdate();
					 
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }finally {
			disConnection();
		}
	 }
	
}

