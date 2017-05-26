package com.app.budget.model;

import javafx.collections.ObservableList;

public interface IUserDAO {

	public ObservableList<User> getAll();
	public User getById(int id);
}
