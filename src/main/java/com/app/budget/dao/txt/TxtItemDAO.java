package com.app.budget.dao.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.app.budget.dao.IItemDAO;
import com.app.budget.model.Item;

import javafx.collections.ObservableList;

public class TxtItemDAO extends IItemDAO<Item> {

	private static TxtItemDAO instance;
	
	private TxtItemDAO(File itemFile) throws IOException{
		try {
			FileReader itemFileReader = new FileReader(itemFile);
			BufferedReader br = new BufferedReader(itemFileReader);
			String lineTxt = br.readLine();
			String[] lineData;
			while(lineTxt != null){
				lineData = lineTxt.split("\\t");
				Item item = new Item(Integer.parseInt(lineData[0]), lineData[2]);
				add(item);
				lineTxt = br.readLine();
			}
			br.close();
			itemFileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(e);
		} 
	}
	
	public static TxtItemDAO getInstance(File itemFile) throws IOException {
		System.out.println("ItemDAO: getInstance");		
		if (instance == null){
			instance = new TxtItemDAO(itemFile);
		}
		return instance;
	}

	@Override
	public ObservableList<Item> getAll() {
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
	public void add(Item item) {
		items.add(item);
	}

	@Override
	public void delete(Item t) {
	}

	@Override
	public void update(Item t) {
	}

	@Override
	public void save() {
		System.out.println("Save items");
	}
}
