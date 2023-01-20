package com.sist.dao;
import java.util.*;

import com.sist.vo.SeoulVO;

import java.sql.*;
public class SeoulDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// 기능////////////////////////////////////////////////////////////
	
	// 1.1 목록 ////////////////////////////////////////////////////////////
	public ArrayList<SeoulVO> seoulListData(int page, int type){
		ArrayList<SeoulVO> list = new ArrayList<SeoulVO>();
		String[] table = {"","location","nature","shop","hotel","guest"};
		try {
			conn = CreateConnection.getConnection();
			String sql = "SELECT no, title, poster, num "
					+"from (select no, title, poster, rownum as num "
					+"from (select no, title, poster "
					+"from seoul_"+table[type]+" ORDER BY no ASC)) "
					+"where num between ? and ?";
			ps = conn.prepareStatement(sql);
			int rowSize=20;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				SeoulVO vo = new SeoulVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				
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
	
	
	// 1.1-1 총페이지 ////////////////////////////////////////////////////////////
	public int seoulTotalPage(int type) {
		int total = 0;
		String[] table = {"","location","nature","shop","hotel","guest"};
		try {
			conn = CreateConnection.getConnection();
			
			String sql = "select ceil(count(*)/20.0) from seoul_" + table[type];
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return total;
	}
	
	// 1.2 상세 ////////////////////////////////////////////////////////////
	
	
}
