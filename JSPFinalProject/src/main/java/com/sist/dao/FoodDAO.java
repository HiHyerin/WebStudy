package com.sist.dao;
import java.util.*;

import org.eclipse.jdt.internal.compiler.apt.dispatch.AnnotationDiscoveryVisitor;

import java.sql.*;
import com.sist.vo.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	//기능
	// 1) 카테고리 읽기
	public ArrayList<CategoryVO> foodCategoryData(){
		ArrayList<CategoryVO> list = new ArrayList<CategoryVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql = "select /*+INDEX_ASC(project_category pc_cno_pk)*/cno,title,subject,poster "
					+"from project_category";
			ps = conn.prepareStatement(sql);
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
			CreateConnection.disConnection(conn, ps);
		}
		
		return list;
		
	}
	
	// 2) 검색
	public ArrayList<FoodVO> foodLocationFindData(int page, String ss){
		ArrayList<FoodVO> list = new ArrayList<FoodVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="select fno,name,poster, num "
					+"from (select fno,name,poster, rownum as num "
					+"from (select fno,name,poster "
					+"from food_location "
					+"where address like '%'||?||'%')) "
					+"where num between ? and ?";
			
			ps = conn.prepareStatement(sql);
			int rowSize=20;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			ps.setString(1,ss);
			ps.setInt(2, start);
			ps.setInt(3, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				String poster = rs.getString(3);
				poster = poster.substring(0,poster.indexOf("^")); // 사진 5개가 묶여있어서 자른거
				poster = poster.replace("#", "&");
				vo.setPoster(poster);
				
				list.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		
		return list;
	}
	
	public int foodLocationTotalPage(String ss) {
		int total=0;
		try {
			conn=CreateConnection.getConnection();
			String sql = "select ceil(count(*)/20.0) from food_location "
					+"where address like '%' ||?|| '%'";
					// where regexp_like(address,?); => 최근엔 이렇게 코딩
			ps=conn.prepareStatement(sql);
			ps.setString(1, ss);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return total;
	}
	
	// 매개변수는 주로 물음표 값 (ex. ?cno=10)
	// public List<FoodVO> findByCno(int cno);
	public ArrayList<FoodVO> foodListData(int cno){
		ArrayList<FoodVO> list = new ArrayList<FoodVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select fno,cno,name,address,tel,type,poster,score "
					+"from project_food "
					+"where cno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				FoodVO vo = new FoodVO();
				vo.setFno(rs.getInt(1));
				vo.setCno(rs.getInt(2));
				vo.setName(rs.getString(3));
				String addr = rs.getString(4);
				addr =addr.substring(0,addr.lastIndexOf("지"));
				vo.setAddress(addr.trim());
				vo.setTel(rs.getString(5));
				vo.setType(rs.getString(6));
				String poster=rs.getString(7);
				poster =poster.substring(0,poster.indexOf("^"));
				vo.setPoster(poster);
				vo.setScore(rs.getInt(8));
				
				list.add(vo);
			}
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	
	
	
	public CategoryVO categoryInfoData(int cno) {
		CategoryVO vo = new CategoryVO();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select title,subject from project_category "
					+ "where cno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setTitle(rs.getString(1));
			vo.setSubject(rs.getString(2));
			rs.close();
		}catch(Exception e) {
			
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return vo;			
	}
	
	// MVC (1.링크 => .do) => @requestMaping() 메소드 만들기 => dao => jsp에서 출력
	// 상세보기
	public FoodVO food_detail(int fno) {
		FoodVO vo = new FoodVO();
		try {
			conn = CreateConnection.getConnection();
			// 트리거 (좋아요, 찜)
			String sql = "update project_food set "
					+"hit=hit+1 "
					+"where fno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ps.executeUpdate();
			
			sql="select fno,cno,name,tel,score,poster,address,type,time,parking,menu,price,"
					+ "good, soso, bad "
					+"from project_food "
					+"where fno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setFno(rs.getInt(1));
			vo.setCno(rs.getInt(2));
			vo.setName(rs.getString(3));
			vo.setTel(rs.getString(4));
			vo.setScore(rs.getDouble(5));
			vo.setPoster(rs.getString(6));
			vo.setAddress(rs.getString(7));
			vo.setType(rs.getString(8));
			vo.setTime(rs.getString(9));
			vo.setParking(rs.getString(10));
			vo.setMenu(rs.getString(11));
			vo.setPrice(rs.getString(12));
			vo.setGood(rs.getInt(13));
			vo.setSoso(rs.getInt(14));
			vo.setBad(rs.getInt(15));
			
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return vo;
	}
	
	// 관련 레시피 출력
	public List<RecipeVO> food_recipe_data(String type){
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select title, poster, chef, rownum "
					+"from recipe "
					+"where regexp_like(title, ?) and rownum <= 5";
			ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RecipeVO vo = new RecipeVO();
				vo.setTitle(rs.getString(1));
				vo.setPoster(rs.getString(2));
				vo.setChef(rs.getString(3));
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	
}
























