package com.sist.dao;
import java.util.*;
import java.sql.*;
// jdbc(원시소스) => dbcp(p.288 database connection pool) : 미리 만들어 놓고 사용 => orm(MyBatis, JPA, Hibernate)
// Spring : 라이브러리(jar)
public class BoardDAO {
	// 오라클 연결 객체
	private Connection conn;
	
	// 오라클 송수신 객체 (SQL => 데이터값 받기)
	private PreparedStatement ps;
	
	// 오라클 연결 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	// 드라이버 연결 (p.277)
	public BoardDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	// 오라클 연결
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	// 오라클 닫기
	public void disConnection() {
		try{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception ex) {}
	}
	////////////////////////////////MyBatis / JPA => xml에 등록
	// 게시판 관련(curd)
	// 웹프로그램의 비중 => 50%(db) => 자바(20%) , html/css(20%), javaScript(10%) 
	public ArrayList<BoardVO> boardListData(int page){ //사용자가 데이터를 전송하면 처리해야하기 때문에 매개변수 필요
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		try {
			getConnection();
			String sql="SELECT no, subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,num "
					+"FROM (SELECT no,subject,name,regdate,hit, rownum as num "
					+"From (select /*+ index_desc(jsp_board jb_no_pk)*/ no,subject,name,regdate,hit "
					+"from jsp_board)) "
					+"where num between ? and ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10; // 10개씩 가져온다
			int start=(page*rowSize)-(rowSize-1);
			int end=page*rowSize;
			
			// ?에 값을 채운다
			ps.setInt(1, start);
			ps.setInt(2, end);
			
			//실행
			ResultSet rs=ps.executeQuery();
			
			// 값을 arraylist에 저장
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				
				list.add(vo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	// 총페이지 가져오기
	// JSP(model1) => mv 패턴 => mvc 패턴(v)
	// M(model)V(vue) => 자바/html
	// C(controller) => 자바/html 연결
	public int boardTotalPage() {
		int total=0;
		try {
			getConnection();
			String sql="select ceil(count(*)/10.0) from jsp_board"; //총페이지 (ceil=올림)
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery(); // 데이터 값 가져올때(select)
			rs.next();
			total=rs.getInt(1);
			rs.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
		
	}
	// 목록 : 페이징 (인라인뷰) , 출력순서, 블록별(페이지 블록 <[1][2][3][4][5]..>)
	// 상세보기 : rownum(이전/다음)
	public BoardVO boardDetailData(int no) {
		BoardVO vo = new BoardVO();
		try {
			//1. 연결
			getConnection();
			
			//2. sql문장
			String sql="UPDATE jsp_board SET "
					+"hit= hit+1 "
					+"where no=?";
			
			// => 기능 수행 => 한개의 기능에 여러개의 sql문장을 실행 할 수 있다.
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeQuery(); // 여기까지 조회수 증가
			
			// 2-1 사용자가 요청한 게시물 상세보기 가져오기
			sql="select no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD') "
					+"from jsp_board "
					+"where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			// 데이터값을 받아서 저장 => jsp에서 읽기
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setHit(rs.getInt(5));
			vo.setDbday(rs.getString(6));
			rs.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	
	
	
	// 글쓰기 : request
	public void boardInsert(BoardVO vo) {
		try {
			getConnection();
			String sql="insert into jsp_board(no,name,subject,content,pwd) "
					+"values(jb_no_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			
			// ?에 값 채우기
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			// 실행명령
			ps.executeUpdate(); // commit포함 => insert, update, delete할 때
								//ResultSet rs=ps.executeQuery(); // 데이터 값 가져올때(select)
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
	}
	// 글 수정 : pwd일치하면 수정 가능 불일치 시 수정 불가 => javaScript
	// 수정 전에 수정 데이터 읽기
	// 1. Ajax, VueJS, ReactJS, TyemeLeaf
	public BoardVO boardUpdateData(int no) {
		BoardVO vo = new BoardVO();
		try {
			getConnection();
			String sql="select no,name,subject,content "
					+"from jsp_board "
					+"where no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			rs.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	
	
	
	
	// 삭제 => 상동
	// 찾기 => <select>, <checkbox>=> 파일 안에서 처리
	
	
}




















