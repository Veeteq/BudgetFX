package com.app.budget.model;

import java.io.File;
import java.time.LocalDate;

import com.app.budget.dao.IItemDAO;
import com.app.budget.dao.txt.TxtItemDAO;

import javafx.collections.ObservableList;

public class DataModel {

	private IItemDAO itemDAO;
	private UserDAO userDAO;
	private ExpenceDAO expenceDAO;
	
	public DataModel(){
		//itemDAO = TxtItemDAO.getInstance(new File("f:/items.txt"));
		itemDAO = TxtItemDAO.getInstance(new File("/run/media/actimize/BC3C84CD3C8483DC/items.txt"));
		userDAO = UserDAO.getInstance();
		expenceDAO = ExpenceDAO.getInstance(itemDAO, userDAO);
	}
	
	public void close(){
		System.out.println("Closing data model");
	}
	
	public ObservableList<Expence> getExpencesByDate(LocalDate localDate){
		return expenceDAO.getByDate(localDate);
	}
	
	public ObservableList<Expence> getExpences(){
		System.out.println("getExpences");
		return expenceDAO.getAll();
	}
	
	public ObservableList<Item> getItems() {
		System.out.println("getItems");
		return itemDAO.getAll();
	}

	public ObservableList<User> getUsers(){
		System.out.println("getUsers");
		return userDAO.getAll();
	}

}
