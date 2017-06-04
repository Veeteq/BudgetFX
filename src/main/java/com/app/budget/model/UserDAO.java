package com.app.budget.model;

import com.app.budget.dao.IUserDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO implements IUserDAO{

	private static UserDAO instance;
	private ObservableList<User> users = FXCollections.observableArrayList();

	private UserDAO() {
		System.out.println("UserDAO: private Constructor");
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("Witek");
		user1.setUserType("Cash");
		user1.setUserDescription("Witek W");
		users.add(user1);
	}

	public static UserDAO getInstance() {
		System.out.println("UserDAO: getInstance");
		if (instance == null){
			return new UserDAO();
		}
		return instance;
	}
	
	public ObservableList<User> getAll() {
		return users;
	}

	public User getById(int id) {
		for(User user : users){
			if(user.getUserId() == id){
				return user;
			}
		}
		return null;
	}
}
