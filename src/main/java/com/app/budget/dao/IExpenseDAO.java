package com.app.budget.dao;

import java.time.LocalDate;

import com.app.budget.model.Expense;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class IExpenseDAO<T> implements IDAO<Expense>{

	protected ObservableList<Expense> expenses = FXCollections.observableArrayList();
	protected long lastExpeId;
	
	public abstract ObservableList<Expense> getByDate(LocalDate localDate);
	
	protected long getLastExpeId() {
		long expeId = 0;
		long lastExpeId = 0;
		for(Expense expense : expenses){
			expeId = expense.getExpeId();
			lastExpeId = expeId > lastExpeId ? expeId : lastExpeId;
		}
		return lastExpeId;
	}

}
