package com.app.budget.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class IItemDAO<T> implements IDAO<T>{

	protected ObservableList<T> items = FXCollections.observableArrayList();
}
