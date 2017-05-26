package com.app.budget.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO implements IUserDAO{

	private ObservableList<User> userList = FXCollections.observableArrayList();

	public UserDAO() {
		User user1 = new User();
		user1.setUserName("Witek");
		user1.setUserType("Cash");
		user1.setUserDescription("Witek W");
		userList.add(user1);
	}

	public ObservableList<User> getAll() {
		return userList;
	}

	public User getById(int id) {
		return userList.get(id);
	}
}
