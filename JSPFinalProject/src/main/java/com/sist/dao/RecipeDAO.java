package com.sist.dao;
import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

import com.sist.vo.RecipeVO;

import java.sql.*;
public class RecipeDAO {
	private Connection conn;
	private PreparedStatement ps;
	public List<RecipeVO> recipeListData(int page){
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select no, title, poster, chef, num "
					+"from (select no, title, poster, chef, rownum as num "
					+"from (select /*+ INDEX_ASC(recipe recipe_no_pk)*/ no, title, poster, chef "
					+"from recipe)) "
					+"where num between ? and ?";
			ps = conn.prepareStatement(sql);
			// ?에 값 채우기
			int rowSize = 20;
			int start = (rowSize*page) - (rowSize-1);
			int end = rowSize * page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RecipeVO vo = new RecipeVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setChef(rs.getString(4));
				
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
	
	public int recipeRowCount() { // 개수출력, 총페이지
		int count = 0;
		try {
			conn = CreateConnection.getConnection();
			String sql = "select count(*) from recipe";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return count;
	}
}
