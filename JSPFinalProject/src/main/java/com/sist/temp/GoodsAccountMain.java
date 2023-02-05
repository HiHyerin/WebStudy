package com.sist.temp;
import java.util.*;
import java.sql.*;
public class GoodsAccountMain {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public GoodsAccountMain() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception e) {}
	}
	
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void goodsAccountInsert(int no, int account) {
		try {
			getConnection();
			String sql = "update goods_all set "
					+"account=? "
					+"where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, account);
			ps.setInt(2, no);
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
	public static void main(String[] args) {
		GoodsAccountMain gm = new GoodsAccountMain();
		for(int i=1;i<=6184;i++) {
			int rand=(int)(Math.random()*40)+10;
			gm.goodsAccountInsert(i, rand);
			try {
				Thread.sleep(30); // 0.03초
			} catch (Exception e) {}
			System.out.println("i="+i);
		}
		System.out.println("수량 추가 종료!!");
	}
}





























