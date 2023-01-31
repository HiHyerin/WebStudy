package com.sist.dao;

import java.util.*;

import com.sist.vo.BoardReplyVO;
import com.sist.vo.FreeBoardVO;

import java.sql.*;

/*
 	select , delete, update, insert
 	인라인뷰 / 조인 / 서브쿼리 
 	
 */
public class FreeBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// 목록 출력 ///////////////////////////////////////////////////
	///////////////////////////////////////////////////
	public List<FreeBoardVO> BoardListData(int page){ // 스프링은 arraylist보단 list를 사용
		List<FreeBoardVO> list = new ArrayList<FreeBoardVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'), hit,num "
					+"from (select no,subject,name,regdate, hit,rownum as num "
					+"from (select /*+INDEX_DESC(project_freeboard pf1_no_pk)*/ no,subject,name,regdate, hit "
					+"from project_freeboard)) "
					+"where num between ? and ?";
			ps = conn.prepareStatement(sql); // sql문장을 오라클로 전송 => 나중에 값을 채워서 실행
			int rowSize = 10;
			int start = (rowSize*page) - (rowSize-1);
			int end = rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery(); // 뭘 실행?
			while(rs.next()) {
				FreeBoardVO vo = new FreeBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				
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
	
	// 총 페이지 ///////////////////////////////////////////////////
	///////////////////////////////////////////////////
	public int boardTotalPage() {
		int total=0;
		try {
			conn = CreateConnection.getConnection();
			String sql = "select ceil(count(*)/10.0) from project_freeboard";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return total;
	}
	
	
	// 추가
	public void boardInsert(FreeBoardVO vo) {
		try {
			conn = CreateConnection.getConnection();
			String sql = "insert into project_freeboard(no,name,subject,content,pwd) "
					+"values(pf_no_seq.nextval,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			// 실행명령
			ps.executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	
	
	// 상세보기////////////////////////////////////////////////////////////////////////////////////
	public FreeBoardVO boardDetailData(int no) {
		FreeBoardVO vo = new FreeBoardVO();
		try {
			conn = CreateConnection.getConnection();
			String sql = "update project_freeboard set "
					+"hit = hit + 1 "
					+"where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			//////////////////////////////////////////조회수 증가
			// 실제 게시물 번호에 해당되는 데이터를 가지고 온다
			sql = "select no, name, subject, content, TO_CHAR(regdate,'YYYY-MM-DD'), hit "
					+"from project_freeboard "
					+"where no = ?";
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
			CreateConnection.disConnection(conn, ps);
		}
		return vo;
	}
	// 수정 ////////////////////////////////////////////////////////////////////////////////////
	public FreeBoardVO boardUpdateData(int no) {
		FreeBoardVO vo = new FreeBoardVO();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select no, name, subject, content "
					+"from project_freeboard "
					+"where no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return vo;
	}
	
	public boolean boardUpdate(FreeBoardVO vo) {
		boolean bCheck = false;
		try {
			conn = CreateConnection.getConnection();
			String sql = "SELECT pwd FROM project_freeboard "
					+"where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(vo.getPwd())) {
				bCheck = true;
				sql = "update project_freeboard SET "
						+"name=?, subject=?, content=? "
						+"where no=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				ps.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return bCheck;
	}
	
	
	// 삭제 ////////////////////////////////////////////////////////////////////////////////////
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck = false;
		try {
			conn = CreateConnection.getConnection();
			String sql = "SELECT pwd FROM project_freeboard "
					+"where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(pwd)) {
				bCheck = true;
				sql = "delete from project_freeboard  "
						+"where no=?";
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, no);
				ps.executeUpdate();
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return bCheck;
		
	}
	// 댓글 (pr_rno_seq)////////////////////////////////////////////////////////////////////////////////////
	public void replyInsert(BoardReplyVO vo) {
		try {
			conn = CreateConnection.getConnection();
			String sql = "insert into project_reply(rno, bno, id, name, msg, group_id) "
					+"values(pr_rno_seq.nextval,?,?,?,?,(select nvl(max(group_id)+1,1) from project_reply))";
			//															----------- --
			//															null일 때    null아닐때
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getBno());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getMsg());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	
	// 댓글 읽기 ///////////////////////////////////////////////////////////////////////////
	public List<BoardReplyVO> replyListData(int bno){
		List<BoardReplyVO> list = new ArrayList<BoardReplyVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select rno, bno,id, name, msg, group_tab, TO_CHAR(regdate,'YYYY-MM-DD HH24:MI;SS') "
					+"from project_reply "
					+"where bno=? "
					+"order by group_id desc, group_step asc"; //그룹별로 모아서 그룹안에 순서대로 출력
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BoardReplyVO vo = new BoardReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setBno(rs.getInt(2));
				vo.setId(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setMsg(rs.getString(5));
				vo.setGroup_tab(rs.getInt(6));
				vo.setDbday(rs.getString(7));
				list.add(vo);
			}
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	
	
	public void replyUpdate(int rno, String msg) { // DAO는 모델을 통해서 호출
		try {
			conn = CreateConnection.getConnection();
			String sql = "update project_reply set "
					+"msg=? "
					+"where rno=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, msg);
			ps.setInt(2, rno);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	
	/*							gi	gs	gt		id, step, tab			
	 * 
	 	AAAAAAAAA				2	0	0
	 		=> BBBBBBBBBB		2	1	1
	 			=> CCCCCCCCC	2	2	2
	 		=> EEEEEEE			2	1	1
	 	
	 	DDDDDDDDDD				
	 		=> KKKKKKKK
	 */
	
	public void replyReplyInsert(int root, BoardReplyVO vo) {
		try {
			conn = CreateConnection.getConnection();
			conn.setAutoCommit(false);  // 트랜잭션 프로그램(여러 sql문장을 동시에 수행할 때 사용-일괄처리  ,  sql문장이 비정상적으로 수행하면 rollback시켜줌)
										// select 여러개인건 괜찮음 insert,update..들이 문제
			// 1. root가 가지고 있는 group_id, group_step, group_tab을 가지고 온다
			String sql = "select group_id, group_step, group_tab "
					+"from project_reply "
					+"where rno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, root);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int gi = rs.getInt(1);
			int gs = rs.getInt(2);
			int gt = rs.getInt(3);
			rs.close();
			
			// 2. group_step 증가 //////////////////
			sql = "update project_reply set "
					+"group_step = group_step+1 "
					+"where group_id=? and group_step>?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeUpdate();
			
			// 3. insert //////////////////
			sql = "insert into project_reply(rno,bno,id,name,msg,group_id,group_step,group_tab,root) "
					+"values(pr_rno_seq.nextval,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getBno());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getMsg());
			ps.setInt(5, gi);
			ps.setInt(6, gs+1);
			ps.setInt(7, gt+1);
			ps.setInt(8, root);
			ps.executeUpdate();
			
			
			// 4. update (depth증가) //////////////////
			sql = "update project_reply set "
					+ "depth = depth+1 "
					+"where rno=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, root);
			ps.executeUpdate();
			
			
			conn.commit(); // 정상수행하면 commit
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();	// 비정상 수행하면 rollback
			} catch (Exception e2) {
				
			}
		}finally {
			try {
				conn.setAutoCommit(true);
			} catch (Exception e) {
				
			}
			CreateConnection.disConnection(conn, ps);
		}
	}
		
		public void replyDelete(int rno) {
			try {
				conn = CreateConnection.getConnection();
				conn.setAutoCommit(false);
				// 1. root, depth
				String sql = "SELECT root, depth FROM project_reply "
						+"where rno = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, rno);
				ResultSet rs = ps.executeQuery();
				rs.next();
				int root = rs.getInt(1);
				int depth = rs.getInt(2);
				rs.close();
				// 2. depth = 0 (Delete), depth!=0(update)
				if(depth == 0) { // depth가 감소되는 상태
					sql = "delete from project_reply "
							+"where rno = ?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, rno);
					ps.executeUpdate();
					
					// depth가 감소 => root
					sql = "update project_reply set "
							+"depth = depth-1 "
							+"where rno=?";
					ps = conn.prepareStatement(sql);
					ps.setInt(1, root);
					ps.executeUpdate();
				}else {
					String msg = "관리자가 삭제한 댓글입니다";
					sql = "update project_reply set "
							+ "msg=? "
							+"where rno=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, msg);
					ps.setInt(2, rno);
					ps.executeUpdate();
				}
				// 3. depth 감소
				conn.commit();
			}catch(Exception ex) {
				ex.printStackTrace();
				try {
					conn.rollback();
				}catch (Exception e) {
					// TODO: handle exception
				}
			}finally {
				try {
					conn.setAutoCommit(true);
					CreateConnection.disConnection(conn, ps);
				} catch (Exception e) {}
				
			
		}
	
	}
	
}





