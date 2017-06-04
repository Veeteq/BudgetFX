package com.app.budget.dao;

import com.app.budget.model.User;

import javafx.collections.ObservableList;

public interface IUserDAO {

	public ObservableList<User> getAll();
	public User getById(int id);
}
