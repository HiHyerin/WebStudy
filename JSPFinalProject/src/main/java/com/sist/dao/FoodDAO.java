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
			String sql = "select cil(count(*)/20.0) from food_location "
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
	
	
	
}
