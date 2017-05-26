package com.app.budget.model;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {

	private ObservableList<Expence> expences = FXCollections.observableArrayList();
	private ObservableList<Item> items = FXCollections.observableArrayList();
	
	private ExpenceDAO expenceDAO;
	private ItemDAO itemDAO;
	private UserDAO userDAO;
	
	public DataModel(){
		expenceDAO = new ExpenceDAO();
		itemDAO = new ItemDAO();
		userDAO = new UserDAO();
	}
	
	public void close(){
	}
	
	public ObservableList<Expence> getExpences(LocalDate localDate){
		expences.clear();
		expences.addAll(expenceDAO.getByDate(localDate));
		return expences;
	}
	
	public ObservableList<Item> getItems() {
		System.out.println("getItems");
		items.clear();
		items.addAll(itemDAO.getAll());
		return items;
	}

	public ObservableList<User> getUsers(){
		System.out.println("getUsers");
		return userDAO.getAll();
	}

}
