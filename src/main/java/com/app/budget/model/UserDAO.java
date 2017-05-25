package com.app.budget.model;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private List<User> userList;

	public UserDAO() {
		System.out.println("Constructor 1");
		userList = new ArrayList<User>();
	}

	public UserDAO(List<User> userList) {
		System.out.println("Constructor 2");
		this.userList = userList;
	}

	public List<User> getAll() {
		return userList;
	}

	public User getUserById(int id) {
		return userList.get(id);
	}
}
