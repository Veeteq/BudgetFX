package com.app.budget.dao;

import javafx.collections.ObservableList;

public interface IExpenceDAO<T> {

	public ObservableList<T> getAll();
	public T getById(int id);
}
