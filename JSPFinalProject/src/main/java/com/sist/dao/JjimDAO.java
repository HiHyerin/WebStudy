package com.sist.dao;
import java.util.*;
import com.sist.vo.*;
import java.sql.*;
public class JjimDAO {
	private Connection conn;
	private PreparedStatement ps;
	public void jjimInsert(JjimVO vo) {
		try {
			conn = CreateConnection.getConnection();
			String sql = "insert into project_jjim values("
					+"(select nvl(max(jno)+1,1) from project_jjim),?,?)"; // max +1 : 자동증가번호설정
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
	
	// 찜 확인 ///////////////////////////////////////////////////////
	public int jjimCount(int no, String id) {
		int count = 0;
		try {
			conn = CreateConnection.getConnection();
			String sql = "select count(*) from project_jjim "
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
	// 찜 목록 ///////////////////////////////////////////////////////
	public List<JjimVO> jjimListData(String id){
		List<JjimVO> list = new ArrayList<JjimVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select /*+ INDEX_DESC(project_jjim pj_jno_pk)*/jno, no, " // 서브쿼리
					+"(select distinct name from project_food where fno = project_jjim.no), "
					+"(select distinct poster from project_food where fno = project_jjim.no), "
					+"(select distinct address from project_food where fno = project_jjim.no), "
					+"(select distinct tel from project_food where fno = project_jjim.no) "
					+"from project_jjim "
					+"where id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				JjimVO vo = new JjimVO();
				vo.setJno(rs.getInt(1));
				vo.setNo(rs.getInt(2));
				vo.setName(rs.getString(3));
				String poster = rs.getString(4);
				poster = poster.substring(0,poster.indexOf("^"));
				poster = poster.replace("#", "&");
				vo.setPoster(poster);
				vo.setAddress(rs.getString(5));
				vo.setTel(rs.getString(6));
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
	// 찜 취소 ///////////////////////////////////////////////////////
	public void jjimDelete(int jno) {
		try {
			conn = CreateConnection.getConnection();
			String sql = "delete from project_jjim "
					+"where jno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, jno);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
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
}
