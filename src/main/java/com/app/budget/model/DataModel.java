package com.app.budget.model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.xml.bind.JAXBException;

import com.app.budget.dao.ExpenseDAO;
import com.app.budget.dao.IExpenseDAO;
import com.app.budget.dao.IItemDAO;
import com.app.budget.dao.IUserDAO;
import com.app.budget.dao.txt.TxtItemDAO;
import com.app.budget.dao.txt.TxtUserDAO;

import javafx.collections.ObservableList;

public class DataModel {

	private IItemDAO<Item> itemDAO;
	private IUserDAO<User> userDAO;
	private IExpenseDAO<Expense> expenseDAO;
	
	public DataModel() throws IOException, JAXBException{
		//itemDAO = XmlItemDAO.getInstance(new File("f:/items.xml"));
		//userDAO = TxtUserDAO.getInstance(new File("f:/users.txt"));
		itemDAO = TxtItemDAO.getInstance(new File("/run/media/actimize/BC3C84CD3C8483DC/items.txt"));
		userDAO = TxtUserDAO.getInstance(new File("/run/media/actimize/BC3C84CD3C8483DC/users.txt"));
		expenseDAO = ExpenseDAO.getInstance(itemDAO, userDAO);
	}
	
	public void close(){
		System.out.println("Closing data model");
	}
	
	public ObservableList<Expense> getExpensesByDate(LocalDate localDate){
		return expenseDAO.getByDate(localDate);
	}
	
	public ObservableList<Expense> getExpenses(){
		System.out.println("getExpenses");
		return expenseDAO.getAll();
	}
	
	public ObservableList<Item> getItems() {
		System.out.println("getItems");
		return itemDAO.getAll();
	}

	public ObservableList<User> getUsers(){
		System.out.println("getUsers");
		return userDAO.getAll();
	}

	public void add(User user) {
		userDAO.add(user);
	}

	public void add(Expense expense) {
		expenseDAO.add(expense);
	}
}
