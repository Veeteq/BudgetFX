package com.app.budget.dao;

import java.util.Optional;

import javafx.collections.ObservableList;

public interface IDAO<T> {

	public ObservableList<T> getAll();
	public Optional<T> getById(long id);
	public void add(T t);
	public void delete(T t);
	public void update(T t);
	public void save() throws Exception;
}