package com.sist.dao;
import java.util.*;

import com.sist.vo.NoticeVO;

import java.sql.*;
public class NoticeDAO {
	private Connection conn;
	private PreparedStatement ps;
	
	// footer에 저장///////////////////////////////////////////////////////
	public List<NoticeVO> noticeTop5(){
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select no, name, subject, TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'), rownum "
					+"from (select no, name, subject, regdate "
					+"from project_notice ORDER BY hit DESC) " // 상위순위를 이용할 땐 inline view?
					+"where rownum <= 5";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setDbday(rs.getString(4));
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
	 /*
	    *   type 
	    *     1 [일반공지]
	    *     2.[이벤트 공지]
	    *     3.[맛집 공지]
	    *     4.[스토어 공지]
	    *     5.[서울여행 공지]
	    */
	// List, Detail => user/admin이 동일
	public List<NoticeVO> noticeListData(int page){
		List<NoticeVO> list = new ArrayList<NoticeVO>();
		try {
			conn = CreateConnection.getConnection();
			String sql = "select no, type, name, subject, TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'), hit, num "
					+"from (select no, type, name, subject, regdate, hit, rownum as num "
					+"from (select /*+ INDEX_DESC(project_notice pro_no_pk)*/no, type, name, subject, regdate, hit " 
					+"from project_notice)) "
					+"where num between ? and ?";
			
			ps = conn.prepareStatement(sql);
			int rowSize=10;
			int start = (rowSize*page)-(rowSize-1);
			int end = rowSize * page;
			
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				NoticeVO vo = new NoticeVO();
				vo.setNo(rs.getInt(1));
				vo.setType(rs.getInt(2));
				vo.setName(rs.getString(3));
				vo.setSubject(rs.getString(4));
				vo.setDbday(rs.getString(5));
				vo.setHit(rs.getInt(6));
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
	
	public int noticeTotalPage() {
		int total = 0;
		try {
			conn = CreateConnection.getConnection();
			String sql = "select ceil(count(*)/10.0) from project_notice";
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
	
	/*
	 이름      널?       유형             
	------- -------- -------------- 
	NO      NOT NULL NUMBER         
	TYPE             NUMBER         
	NAME    NOT NULL VARCHAR2(34)   
	SUBJECT NOT NULL VARCHAR2(1000) 
	CONTENT NOT NULL CLOB           
	REGDATE          DATE           
	HIT              NUMBER
	
	 * 오라클 : join(inner, outer), Subquery
	 		  view(inline view) => Top n
	 
	 * 자바 : 변수, 연산자, 제어문, 메소드
	 		 라이브러리 : Collection, java.util.*, java.lang.*
	 
	 * JSP : 내장객체 (request, response, session, cookie)
	 		 el, jstl, mvc
	 
	 * JS : 변수, 연산자, 제어문, 함수, JQuery, Ajax
	 
	 * DAO(DBCP) => mybatis => jpa
	 * MVC => Spring => Spring-Boot
	 * Jquery => Vue => react
	*/
	
	public void noticeInsert(NoticeVO vo) {
		try {
			conn = CreateConnection.getConnection();
			
			String sql = "insert into project_notice values( "
					+"(select nvl(max(no)+1,1) from project_notice),?,?,?,?,SYSDATE, 0)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getType());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getContent());
			ps.executeUpdate(); //commit
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
		
		public void noticeDelete(int no) {
			try {
				conn = CreateConnection.getConnection();
				
				String sql = "delete from project_notice "
						+"where no = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				
				ps.executeUpdate(); //commit
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CreateConnection.disConnection(conn, ps);
			}
		}
		
		public NoticeVO noticeUpdateData(int no) {
			NoticeVO vo = new NoticeVO();
			try {
				conn = CreateConnection.getConnection();
				
				String sql = "select no,name, subject, content, type "
						+"from project_notice "
						+"where no=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				
				ResultSet rs = ps.executeQuery();
				rs.next();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setType(rs.getInt(5));
				rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CreateConnection.disConnection(conn, ps);
			}
			return vo;
		}
		
		public void noticeUpdate(NoticeVO vo) {
			try {
				conn = CreateConnection.getConnection();
				String sql = "update project_notice set "
						+"type=?, subject=?, content=? "
						+"where no=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, vo.getType());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				ps.executeUpdate();
						
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CreateConnection.disConnection(conn, ps);
			}
		}
		
		public NoticeVO noticeDetailData(int no) {
			NoticeVO vo = new NoticeVO();
			try {
				conn = CreateConnection.getConnection();
				
				String sql = "update project_notice set "
						+"hit = hit+1 "
						+"where no=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
				
				
				sql = "select no,name, subject, content, type, hit, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') "
						+"from project_notice "
						+"where no=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, no);
				
				ResultSet rs = ps.executeQuery();
				rs.next();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setType(rs.getInt(5));
				vo.setHit(rs.getInt(6));
				vo.setDbday(rs.getString(7));
				rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CreateConnection.disConnection(conn, ps);
			}
			return vo;
		}

}
