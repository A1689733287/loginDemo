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
	 * 关闭数据库的连接
	 * @param Connection连接参数
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭PreparedStatement预编译对象
	 * @param pstmt PreparedStatement对象
	 */
	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭结果集对象
	 * @param ResultSet结果集
	 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			if (rs != null) {
				rs.close();
			}
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
