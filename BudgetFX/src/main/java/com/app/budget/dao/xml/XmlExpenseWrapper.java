package com.app.budget.dao.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.app.budget.model.Expense;

@XmlRootElement(name="expenses")
public class XmlExpenseWrapper {

	private List<Expense> expenses;

	@XmlElement(name="expense")
	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	
}
