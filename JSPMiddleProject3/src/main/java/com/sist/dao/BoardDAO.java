package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import com.sist.vo.BoardVO;

public class BoardDAO {
	private Connection conn; // 톰캣에 의해 미리 연결됨
	private PreparedStatement ps; // 송수신

	
	// Connection의 주소 얻기
	public void getConnection() {
		//탐색기 형식 => 탬색기 열기 => C드라이브 접근 => Connection 객체주소 가지고 오기
		try {
			Context init = new InitialContext();
			Context c = (Context)init.lookup("java://comp/env");
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 반환
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
			// pool에 반환 (POOL : Connection객체가 저장된 메모리 공간
			
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//기능 => jstl + el
	//1 목록
	public ArrayList<BoardVO> boardListData(int page){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			getConnection();
			String sql="select no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,group_tab,num "
					+"from (select no,subject,name,regdate,hit,group_tab,rownum as num "
					+"from (select no,subject,name,regdate,hit,group_tab "
					+"from replyboard ORDER BY group_id DESC,group_step ASC)) "
					+"where num BETWEEN ? AND ?";
			ps = conn.prepareStatement(sql);
			int rowSize=10;
			int start = (rowSize*page)-(rowSize-1); //1page =>1 , 2page => 11부터
			int end = rowSize*page; // 10, 20
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
				list.add(vo);
			}
			rs.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	public int boardRowCount() {
		int count=0;
		try {
			getConnection();
			String sql = "select count(*) from replyBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return count;
	}
	//2 새글
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql = "insert into replyBoard(no,name,subject,content,pwd,group_id) "
					+"values(rb_no_seq.nextval,?,?,?,?,(select NVL(MAX(group_id)+1,1) from replyBoard))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			 
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
	}
	
	
	//3 상세보기 (매개변수 = ? 뒤에 있는 값
	public BoardVO boardDetailData(int no, int type) {
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			String sql = "";
			if(type==1) { // detail(조회수 증가)-> type==1 , update(조회수 증가x) -> type==2
				sql="update replyBoard set "
						+"hit=hit+1 "
						+"where no=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
			}
			sql="select no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD'),hit "
					+ "from replyBoard "
					+ "where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
			
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
		
		return vo;
	}
	
	
	
	//4 답변(*****) => sql 4 개///////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	public void boardReplyInsert(int pno,BoardVO vo) {
		try {
			getConnection();
			// 1. group_id , group_step , group_tab
			String sql = "select group_id, group_step, group_tab "
					+"from replyBoard "
					+"where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			BoardVO pvo = new BoardVO();
			pvo.setGroup_id(rs.getInt(1));
			pvo.setGroup_step(rs.getInt(2));
			pvo.setGroup_tab(rs.getInt(3));
			rs.close();
			
			
			// 2. group_step 조절 => 답변형 게시판의 핵심
			sql = "update replyBoard set "
					+ "group_step=group_step+1 "
					+"where group_id=? and group_step>?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pvo.getGroup_id());
			ps.setInt(2, pvo.getGroup_step());
			ps.executeUpdate();
			
			
			
			// 3. insert
			sql = "insert into replyBoard(no,name,subject,content,pwd,regdate,hit,group_id,group_step,group_tab,"
					+"root, depth) values(rb_no_seq.nextval,?,?,?,?,SYSDATE,0,?,?,?,?,0)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, pvo.getGroup_id());
			ps.setInt(6,pvo.getGroup_step()+1 );
			ps.setInt(7,pvo.getGroup_tab()+1);
			ps.setInt(8, pno);
			ps.executeUpdate();
			
			
			
			// 4. depth 증가
			sql = "update replyBoard set "
					+ "depth = depth+1 "
					+ "where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate();
			
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally{
			
		}
	}
	
	
	
	
	
	//5 수정
	public boolean boardUpdate(BoardVO vo) {
		boolean bCheck=false;
		try {
			getConnection();
			String sql = "select pwd from replyBoard "
					+"where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(vo.getPwd())) {
				bCheck = true;
				// 실제 수정
				sql = "update replyBoard set "
						+ "name=?,subject=?,content=? "
						+"where no=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection(); // 반환 (jdbc는 종료)
		}
		return bCheck;
	}
	
	
	//6 삭제 => sql 4개
	public boolean boardDelete(int no,String pwd) {
		boolean bCheck = false;
		try {
			getConnection();
			String sql = "select pwd, root, depth "
					+"from replyboard "
					+"where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			int root = rs.getInt(2);
			int depth = rs.getInt(3);
			
			rs.close();
			
			if(db_pwd.equals(pwd)) {
				bCheck = true;
				if(depth==0) {//답변이 없는 게시물
					sql="delete from replyBoard "
							+"where no=?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
					
				}else { // 수정 => 관리자가 삭제한 게시물
					String msg = "관리자가 삭제한 게시물입니다.";
					sql="update replyBoard set "
							+ "subject=?,content=? "
							+"where no=?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, msg);
					ps.setString(2, msg);
					ps.setInt(3, no);
					ps.executeUpdate();
				}
				
				
				// depth 감소
				if(root!=0) {
					sql = "update replyBoard SET "
							+"depth=depth-1 "
							+"where no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, root);
					ps.executeUpdate();
				}
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return bCheck;
	}
	
}










