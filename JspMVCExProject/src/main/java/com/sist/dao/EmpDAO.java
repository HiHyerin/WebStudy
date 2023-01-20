package com.sist.dao;
import java.util.*;
import java.sql.*;

public class EmpDAO {
	// 드라이버 등록
	public ArrayList<EmpVO> empListData(){
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			conn = DriverManager.getConnection(url,"hr","happy");
			String sql = "select empno, ename, hiredate, sal "
					+"FROM emp ORDER BY empno ASC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				
				list.add(vo);
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return list;
	}
	
	// 상세보기 ////////////////////////////////////////////////////////////
	/*
	 *  getConnection()
	    disConnection() ------> 계속 중복
	 	한 클래스 안에서 중복 => 메소드(JDBC)
	 	여러 클래스에서 중복 => 클래스(클래스)
	 */
	public EmpVO empDetailData(int empno) {
		EmpVO vo = new EmpVO();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			// 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			conn = DriverManager.getConnection(url,"hr","happy");
			String sql = "select empno, ename, hiredate, sal, comm, mgr, deptno "
					+"FROM emp WHERE empno=" + empno;
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
				
			rs.next();
			vo.setEmpno(rs.getInt(1));
			vo.setEname(rs.getString(2));
			vo.setJob(rs.getString(3));
			vo.setHiredate(rs.getDate(4));
			vo.setSal(rs.getInt(5));
			vo.setComm(rs.getInt(6));
			vo.setMgr(rs.getInt(7));
			vo.setDeptno(rs.getInt(8));
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		return vo;
	}
	
}

















