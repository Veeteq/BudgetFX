package com.app.budget.model;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

	private List<Item> itemList;
	
	public ItemDAO(){
		itemList = new ArrayList<>();
		
		Item item1 = new Item(1, "Chleb");
		Item item2 = new Item(2, "PepsiCola");
		Item item3 = new Item(3, "Benzyna");
		Item item4 = new Item(4, "Kosmetyki");
		Item item5 = new Item(5, "Sukienka");
		
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		itemList.add(item4);
		itemList.add(item5);
	}

	public List<Item> getAll(){
		return itemList;
	}
	
	public Item getItemById(int i) {
		return itemList.get(i);
	}
}
