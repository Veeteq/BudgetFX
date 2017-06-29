package com.app.budget.dao;

import com.app.budget.model.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class IItemDAO<T> implements IDAO<Item>{

	protected ObservableList<Item> items = FXCollections.observableArrayList();
	protected long lastItemId;
	
	protected long getLastItemId(){
		long itemId = 0;
		long lastItemId = 0;
		for(Item item : items){
			itemId = item.getItemId();
			lastItemId = itemId > lastItemId ? itemId : lastItemId;
		}
		return lastItemId;
	}
}
