package com.app.budget.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO implements IUserDAO{

	private static UserDAO instance;
	private ObservableList<User> userList = FXCollections.observableArrayList();

	private UserDAO() {
		System.out.println("UserDAO: private Constructor");
		User user1 = new User();
		user1.setUserName("Witek");
		user1.setUserType("Cash");
		user1.setUserDescription("Witek W");
		userList.add(user1);
	}

	public static UserDAO getInstance() {
		System.out.println("UserDAO: getInstance");
		if (instance == null){
			return new UserDAO();
		}
		return instance;
	}
	public ObservableList<User> getAll() {
		return userList;
	}

	public User getById(int id) {
		return userList.get(id);
	}
}
