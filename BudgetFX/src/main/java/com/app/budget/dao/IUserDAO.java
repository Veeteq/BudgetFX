package com.app.budget.dao;

import com.app.budget.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class IUserDAO<T> implements IDAO<User>{

	protected ObservableList<User> users = FXCollections.observableArrayList();
	
	protected long lastUserId;
	
	protected long getLastUserId(){
		long userId = 0;
		long lastUserId = 0;
		for(User user: users){
			userId = user.getUserId();
			lastUserId = userId > lastUserId ? userId : lastUserId;
		}
		return lastUserId;
	}

}
