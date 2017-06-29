package com.app.budget.dao;

import java.util.Optional;

import com.app.budget.model.User;

import javafx.collections.ObservableList;

public class UserDAO extends IUserDAO<User>{

	private static UserDAO instance;

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
			instance = new UserDAO();
		}
		return instance;
	}
	
	@Override
	public ObservableList<User> getAll() {
		return users;
	}

	@Override
	public Optional<User> getById(long id) {
		for(User user : users){
			if(user.getUserId() == id){
				return Optional.of(user);
			}
		}
		return Optional.empty();
	}

	@Override
	public void add(User user) {
		users.add(user);
	}

	@Override
	public void delete(User t) {
	}

	@Override
	public void update(User t) {
	}

	@Override
	public void save() {
	}
}
