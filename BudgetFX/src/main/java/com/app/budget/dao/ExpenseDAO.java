package com.app.budget.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.app.budget.dao.IExpenseDAO;
import com.app.budget.dao.IItemDAO;
import com.app.budget.model.Expense;
import com.app.budget.model.Item;
import com.app.budget.model.User;

import javafx.collections.ObservableList;

public class ExpenseDAO extends IExpenseDAO<Expense>{

	private static ExpenseDAO instance;
	
	private ExpenseDAO(IItemDAO<Item> itemDAO, IUserDAO<User> userDAO){
		Expense expense1 = new Expense();
		expense1.setOperDt(LocalDate.of(2017, 5, 18));
		expense1.setExpeItem(itemDAO.getById(3).get());
		expense1.setExpeUser(userDAO.getById(1).get());
		expense1.setExpeItemCount(BigDecimal.valueOf(1.5));
		expense1.setExpeItemPrice(BigDecimal.valueOf(15));
		expenses.add(expense1);
		
		Expense expense2 = new Expense();
		expense2.setOperDt(LocalDate.of(2017, 5, 18));
		expense2.setExpeItem(itemDAO.getById(1).get());
		expense2.setExpeUser(userDAO.getById(1).get());
		expense2.setExpeItemCount(BigDecimal.valueOf(2.2));
		expense2.setExpeItemPrice(BigDecimal.valueOf(150));
		expenses.add(expense2);

		Expense expense3 = new Expense();
		expense3.setOperDt(LocalDate.of(2017, 5, 18));
		expense3.setExpeItem(itemDAO.getById(4).get());
		expense3.setExpeUser(userDAO.getById(1).get());
		expense3.setExpeItemCount(BigDecimal.valueOf(4));
		expense3.setExpeItemPrice(BigDecimal.valueOf(10));
		expenses.add(expense3);
	}
	
	public static ExpenseDAO getInstance(IItemDAO<Item> itemDAO, IUserDAO<User> userDAO) {
		System.out.println("ExpenseDAO: getInstance");
		if (instance == null){
			instance = new ExpenseDAO(itemDAO, userDAO);
		}
		return instance;
	}
	
	@Override
	public ObservableList<Expense> getAll(){
		return expenses;
	}

	public ObservableList<Expense> getByDate(LocalDate localDate) {
		return expenses;
	}

	@Override
	public Optional<Expense> getById(long id) {
		return Optional.empty();
	}

	@Override
	public void add(Expense expense) {
		expenses.add(expense);
	}

	@Override
	public void delete(Expense expense) {
	}

	@Override
	public void update(Expense expense) {
	}

	@Override
	public void save() {
	}
}
