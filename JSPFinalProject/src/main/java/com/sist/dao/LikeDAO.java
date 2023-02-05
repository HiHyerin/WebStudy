package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

/*
try {
	conn = CreateConnection.getConnection();
	String sql = " "
} catch (Exception e) {
	e.printStackTrace();
}finally {
	CreateConnection.disConnection(conn, ps);
}
*/
public class LikeDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	public void likeInsert(LikeVO vo) {
		try {
			conn = CreateConnection.getConnection();
			String sql = "insert into project_like values("
					+"(select nvl(max(lno)+1,1) from project_like),"
					+"?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ps.setString(2, vo.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	
	public int likeCount() {
		int count = 0;
		try {
			conn = CreateConnection.getConnection();
			String sql = "select count(*) from project_like";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return count;
	}
	
	
	public int myLikeCount(int no, String id) { // 한 컨텐츠당 좋아요 하나만
		int count = 0;
		try {
			conn = CreateConnection.getConnection();
			String sql = "select count(*) from project_like "
					+"where no=? and id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return count;
	}
	
	public int foodLikeCount(int fno) {
		int count = 0;
		try {
			conn = CreateConnection.getConnection();
			String sql = "select count(*) from project_like "
					+"where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return count;
	}
	
	
}
