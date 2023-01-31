package com.sist.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class CreateConnection {
	private static Connection conn;
	public  static Connection getConnection() {
		try {
			Context init = new InitialContext();
			Context c = (Context) init.lookup("java://comp/env");
			DataSource ds = (DataSource)c.lookup("jdbc/oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("getconnection에러:"+e);
			// TODO: handle exception
		}

		return conn;
	}

	public static void disConnection(Connection conn, PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
