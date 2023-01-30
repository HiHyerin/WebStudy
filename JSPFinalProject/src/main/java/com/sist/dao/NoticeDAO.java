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

}
