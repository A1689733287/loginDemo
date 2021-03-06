package com.gpg.action;

import java.util.List;

import com.gpg.entity.User;
import com.gpg.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private User user;

	private UserService userService = new UserService();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String login() {
		try {
			User userInfo = userService.login(user);
			if (userInfo == null) {
				return "input";
			}
			if (user.getUserName().equals(userInfo.getUserName()) && user.getPwd().equals(userInfo.getPwd())) {
				ActionContext.getContext().getSession().put("user", user);

				return "success";
			}
		} catch (Exception e) {
			return "error";
		}
		return null;
	}

	public String list() {
		try {
			List<User> users = userService.getAll();
			ActionContext.getContext().getContextMap().put("users", users);
			return "listUser";
		} catch (Exception e) {
			return "error";
		}
	}


	private Integer id;
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String del() {
		if (userService.delete(id)) {
			return "delSuccess";
		}return "input";
	}

	

}
