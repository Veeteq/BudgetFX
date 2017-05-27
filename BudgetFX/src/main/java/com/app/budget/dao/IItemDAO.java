package com.app.budget.dao;

import com.app.budget.model.Item;

import javafx.collections.ObservableList;

public interface IItemDAO {

	public ObservableList<Item> getAll();
	public Item getById(long id);
	public void save(Item item);
}
