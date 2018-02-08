package com.gpg.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	//创建ThreadLocal对象
	private final static ThreadLocal<Connection> LOCAL = new ThreadLocal<>();
	// 声明数据源对象
	private static DataSource dataSource = null; 
	
	// 通过静态代码块初始化数据源
	static {
		dataSource = new ComboPooledDataSource("mysql");
	}
	
	/**
	 * 从数据源中获得 jdbc的连接Connection
	 * @return 返回conn连接对象
	 */
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

		
	/**
	 * 关闭连接
	 * @param conn Connection对象
	 * @param pstmt PreparedStatement对象
	 * @param rs ResultSet结果集对象
	 */
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到数据源
	 * @return 返回数据源对象
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void remove() {
		LOCAL.remove();
	}
	
}
