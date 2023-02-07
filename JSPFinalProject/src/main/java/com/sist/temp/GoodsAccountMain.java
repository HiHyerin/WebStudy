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
		
//		List<Integer> list = gm.foodNumberDate();
//		int i = 0;
//		for(int fno:list) {
//			gm.foodReserveDayInsert(fno, gm.reserve_day());
//			System.out.println("i="+i);
//		}
//		System.out.println("저장 완료");
//		for(int i=1;i<=6184;i++) {
//			int rand=(int)(Math.random()*40)+10;
//			gm.goodsAccountInsert(i, rand);
//			try {
//				Thread.sleep(30); // 0.03초
//			} catch (Exception e) {}
//			System.out.println("i="+i);
//		}
//		System.out.println("수량 추가 종료!!");
//		String s = gm.reserve_day();
//		System.out.println(s);
		
//		String s = gm.reserve_time();
//		System.out.println(s);
		
		for(int i=1;i<=31;i++) {
			gm.foodReserveTimeInsert(i, gm.reserve_time());
			
		}
		System.out.println("저장 완료");
	}
	
	public void foodReserveTimeInsert(int rdate, String time) {
		try {
			getConnection();
			String sql = "insert into project_reserve_date values("
					+"(select NVL(MAX(dno)+1,1) from project_reserve_date), ?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rdate);
			ps.setString(2, time);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
	// 예약 가능한 날 저장
	public void foodReserveDayInsert(int fno, String reserve_day) {
		try {
			getConnection();
			String sql = "update food_location set "
					+"reserve_day = ? "
					+"where fno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, reserve_day);
			ps.setInt(2, fno);
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
	
	public List<Integer> foodNumberDate(){
		List<Integer> list = new ArrayList<Integer>();
		try {
			getConnection();
			String sql = "select fno from food_location";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	// 예약 가능 날짜 랜덤으로 생성
	public String reserve_day() { 
		String s = "";
		int[] com = new int[(int)(Math.random()*10)+7];
		int su = 0;
		boolean bCheck = false;
		for(int i=0;i<com.length;i++) {
			bCheck = true;
			while(bCheck) {
				su = (int)(Math.random()*30)+1;
				bCheck = false;
				for(int j=0;j<i;j++) {
					if(com[j] == su) {
						bCheck = true;
						break; // 날짜중복 방지
					}
				}
			}
			com[i]=su;
		}
		for(int i:com) {
			s += i + ",";
		}
		s = s.substring(0,s.lastIndexOf(","));
		return s;
	}
	
	// 예약 시간
	public String reserve_time() { 
		String s = "";
		int[] com = new int[(int)(Math.random()*5)+5];
		int su = 0;
		boolean bCheck = false;
		for(int i=0;i<com.length;i++) {
			bCheck = true;
			while(bCheck) {
				su = (int)(Math.random()*16)+1;
				bCheck = false;
				for(int j=0;j<i;j++) {
					if(com[j] == su) {
						bCheck = true;
						break; // 날짜중복 방지
					}
				}
			}
			com[i]=su; // 날짜는 상관 없지만 시간은 시간순으로 정렬
		}
		
		Arrays.sort(com);
		
		for(int i:com) {
			s += i + ",";
		}
		s = s.substring(0,s.lastIndexOf(","));
		return s;
	}
}





























