package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;
public class AllReplyDAO {
	// => 글쓰기, 목록, 수정, 삭제
	private Connection conn;
	private PreparedStatement ps;
	
	// 1. 댓글 올리기 기능
	public void allReplyInsert(AllReplyVO vo) {
		try {
			conn = CreateConnection.getConnection();
			String sql = "insert into project_all_reply values(par_rno_seq.nextval, ?,?,?,?,?,SYSDATE)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getCate_no());
			ps.setInt(2, vo.getNo());
			ps.setString(3, vo.getId());
			ps.setString(4, vo.getName());
			ps.setString(5, vo.getMsg());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	
	// 2. 댓글 목록
	public List<AllReplyVO> allReplyListData(int no, int cate_no){
		/*
		 	1. rno => 댓글 고유번호
		 	2. no
		 	3. cate_no => 구분자(맛집, 명소,상품)
		 */
		List<AllReplyVO> list = new ArrayList<AllReplyVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select rno, cate_no, no, id, name, msg, TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
					+"from project_all_reply "
					+"where no = ? and cate_no = ? "
					+"order by rno desc";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setInt(2, cate_no);
			ps.executeUpdate();
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				AllReplyVO vo = new AllReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setCate_no(rs.getInt(2));
				vo.setNo(rs.getInt(3));
				vo.setId(rs.getString(4));
				vo.setName(rs.getString(5));
				vo.setMsg(rs.getString(6));
				vo.setDbday(rs.getString(7));
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
	
	// 삭제
	public void allReplyDelete(int rno) {
		try {
			conn = CreateConnection.getConnection();
			String sql = "delete from project_all_reply "
					+"where rno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rno);
			ps.executeUpdate();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	
	// 수정
	public void allReplyUpdate(int rno, String msg) {
		try {
			conn = CreateConnection.getConnection();
			String sql = "update project_all_reply set "
					+"msg = ? "
					+"where rno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, msg);
			ps.setInt(2, rno);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CreateConnection.disConnection(conn, ps);
		}
		
	}
}














