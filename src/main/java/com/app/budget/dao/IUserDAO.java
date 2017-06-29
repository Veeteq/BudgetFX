package com.app.budget.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class IUserDAO<T> implements IDAO<T>{

	protected ObservableList<T> users = FXCollections.observableArrayList();
}
