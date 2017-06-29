package com.app.budget.dao;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class IExpenseDAO<T> implements IDAO<T>{

	protected ObservableList<T> expenses = FXCollections.observableArrayList();
	
	public abstract ObservableList<T> getByDate(LocalDate localDate);
}
