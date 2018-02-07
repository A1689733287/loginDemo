package com.gpg.service;

import java.util.List;

import com.gpg.dao.UserDao;
import com.gpg.entity.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public User login(User user){
		return userDao.login(user);
	}
	
	public List<User> getAll() {
		return userDao.getAll();
	}
}
