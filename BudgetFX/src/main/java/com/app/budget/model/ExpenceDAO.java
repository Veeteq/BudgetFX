package com.app.budget.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.app.budget.dao.IItemDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExpenceDAO {

	private static ExpenceDAO instance;
	private ObservableList<Expence> expences = FXCollections.observableArrayList();
	
	private ExpenceDAO(IItemDAO itemDAO, UserDAO userDAO){
		Expence expence1 = new Expence();
		expence1.setOperDt(LocalDate.of(2017, 5, 18));
		expence1.setExpeItem(itemDAO.getById(3));
		expence1.setExpeUser(userDAO.getById(1));
		expence1.setExpeItemCount(BigDecimal.valueOf(1.5));
		expence1.setExpeItemPrice(BigDecimal.valueOf(15));
		expences.add(expence1);
		
		Expence expence2 = new Expence();
		expence2.setOperDt(LocalDate.of(2017, 5, 18));
		expence2.setExpeItem(itemDAO.getById(1));
		expence2.setExpeUser(userDAO.getById(1));
		expence2.setExpeItemCount(BigDecimal.valueOf(2.2));
		expence2.setExpeItemPrice(BigDecimal.valueOf(150));
		expences.add(expence2);

		Expence expence3 = new Expence();
		expence3.setOperDt(LocalDate.of(2017, 5, 18));
		expence3.setExpeItem(itemDAO.getById(4));
		expence3.setExpeUser(userDAO.getById(1));
		expence3.setExpeItemCount(BigDecimal.valueOf(4));
		expence3.setExpeItemPrice(BigDecimal.valueOf(10));
		expences.add(expence3);
	}
	
	public static ExpenceDAO getInstance(IItemDAO itemDAO, UserDAO userDAO) {
		System.out.println("ExpenceDAO: getInstance");
		if (instance == null){
			return new ExpenceDAO(itemDAO, userDAO);
		}
		return instance;
	}
	
	public ObservableList<Expence> getAll(){
		return expences;
	}

	public ObservableList<Expence> getByDate(LocalDate localDate) {
		return expences;
	}
}
