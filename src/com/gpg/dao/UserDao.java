package com.gpg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gpg.entity.User;
import com.gpg.utils.JdbcUtils;

public class UserDao {
	Connection conn = JdbcUtils.getConnection();
	List<User> list = new ArrayList<>();

	public User login(User user) {
		String sql = "SELECT * FROM users WHERE username = '"+user.getUserName()+"' and userpwd = '"+user.getPwd()+"'";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<User> getAll() {
		String sql = "SELECT * FROM USERS";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String userpwd = rs.getString("userpwd");
				User user = new User(id, username, userpwd);
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
