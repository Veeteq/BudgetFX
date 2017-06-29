package com.app.budget.dao;

import java.util.Optional;

import com.app.budget.dao.IItemDAO;
import com.app.budget.model.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemDAO extends IItemDAO<Item>{

	private static ItemDAO instance;
	private ObservableList<Item> items = FXCollections.observableArrayList();
	
	private ItemDAO(){
		Item item1 = new Item(1, "Chleb");
		Item item2 = new Item(2, "PepsiCola");
		Item item3 = new Item(3, "Benzyna");
		Item item4 = new Item(4, "Kosmetyki");
		Item item5 = new Item(5, "Sukienka");
		
		items.add(item1);
		items.add(item2);
		items.add(item3);
		items.add(item4);
		items.add(item5);
	}

	public static ItemDAO getInstance() {
		System.out.println("ItemDAO: getInstance");
		if (instance == null){
			return new ItemDAO();
		}
		return instance;
	}

	@Override
	public ObservableList<Item> getAll(){
		return items;
	}
	
	@Override
	public Optional<Item> getById(long id) {
		for(Item item : items){
			if(item.getItemId() == id){
				return Optional.of(item);
			}
		}
		return Optional.empty();
	}

	@Override
	public void add(Item item) {
		items.add(item);
	}


	@Override
	public void delete(Item item) {
	}

	@Override
	public void update(Item item) {
	}

	@Override
	public void save() {
	}
}
