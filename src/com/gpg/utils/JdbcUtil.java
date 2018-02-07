package com.gpg.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class JdbcUtil {
	private static DataSource dataSource;
	private static final ThreadLocal<Connection> LOCAL = new ThreadLocal<>();
	static {
		dataSource = new ComboPooledDataSource("oracle");
	}
	
	public static Connection getConnection() {
		Connection conn = LOCAL.get();
		 try {
			conn = dataSource.getConnection();
			LOCAL.set(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close (PreparedStatement pstmt) {
		try {
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close (ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void remove () {
		LOCAL.remove();
	}
	
	public static void main(String[] args) {
		Connection conn = getConnection();
		String sql = "SELECT ID, FIRST_NAME, SALARY FROM S_EMP WHERE SALARY > (SELECT AVG(SALARY) FROM S_EMP)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("FIRST_NAME");
				String salary = rs.getString("SALARY");
				System.out.println(id+"\t"+ name+"\t"+salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rs);
			close(conn);
		}
	}
}
