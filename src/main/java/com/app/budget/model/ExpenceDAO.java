package com.app.budget.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.budget.model.Expence;
import com.app.budget.model.ItemDAO;

public class ExpenceDAO {

	private List<Expence> expences;
	
	public ExpenceDAO(){
		ItemDAO itemDAO = new ItemDAO();
		expences = new ArrayList<>();
		
		Expence expence1 = new Expence();
		expence1.setOperDt(LocalDate.of(2017, 5, 18));
		expence1.setExpeItem(itemDAO.getItemById(3));
		expence1.setExpeItemCount(BigDecimal.valueOf(1.5));
		expence1.setExpeItemPrice(BigDecimal.valueOf(15));
		expences.add(expence1);
		
		Expence expence2 = new Expence();
		expence2.setOperDt(LocalDate.of(2017, 5, 18));
		expence2.setExpeItem(itemDAO.getItemById(1));
		expence2.setExpeItemCount(BigDecimal.valueOf(2.2));
		expence2.setExpeItemPrice(BigDecimal.valueOf(150));
		expences.add(expence2);

		Expence expence3 = new Expence();
		expence3.setOperDt(LocalDate.of(2017, 5, 18));
		expence3.setExpeItem(itemDAO.getItemById(4));
		expence3.setExpeItemCount(BigDecimal.valueOf(4));
		expence3.setExpeItemPrice(BigDecimal.valueOf(10));
		expences.add(expence3);
	}
	
	public List<Expence> getAll(){
		return expences;
	}

	public List<Expence> getByDate(LocalDate localDate) {
		return expences;
	}
}
