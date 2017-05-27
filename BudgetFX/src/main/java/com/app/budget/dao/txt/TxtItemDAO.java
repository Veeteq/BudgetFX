package com.app.budget.dao.txt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.app.budget.dao.IItemDAO;
import com.app.budget.model.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TxtItemDAO implements IItemDAO {

	private static TxtItemDAO instance;
	private ObservableList<Item> items = FXCollections.observableArrayList();
	
	private TxtItemDAO(File itemFile){
		try {
			FileReader itemFileReader = new FileReader(itemFile);
			BufferedReader br = new BufferedReader(itemFileReader);
			String lineTxt = br.readLine();
			String[] lineData;
			while(lineTxt != null){
				lineData = lineTxt.split("\\t");
				Item item = new Item(Integer.parseInt(lineData[0]), lineData[2]);
				items.add(item);
				lineTxt = br.readLine();
			}
			br.close();
			itemFileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static IItemDAO getInstance(File itemFile) {
		System.out.println("ItemDAO: getInstance");		
		if (instance == null){
			return new TxtItemDAO(itemFile);
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
	public void save(Item item) {
		// TODO Auto-generated method stub
		
	}

}
