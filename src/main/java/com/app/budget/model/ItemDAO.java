package com.app.budget.model;

import com.app.budget.dao.IItemDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemDAO implements IItemDAO{

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
	public Item getById(long id) {
		for(Item item : items){
			if(item.getItemId() == id){
				return item;
			}
		}
		return null;
	}

	@Override
	public void save(Item item) {
		items.add(item);
	}
}
