package com.app.budget.dao.xml;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.app.budget.dao.IExpenseDAO;
import com.app.budget.model.Expense;

import javafx.collections.ObservableList;

public class XmlExpenseDAO extends IExpenseDAO<Expense>{

	
	private static XmlExpenseDAO instance;
	private File dataFile;
	
	private XmlExpenseDAO(File dataFile) throws JAXBException, IOException {
		System.out.println("file: " + dataFile.getAbsolutePath());
		this.dataFile = dataFile;
		if(!(dataFile.exists() && !dataFile.isDirectory())) dataFile.createNewFile();
		if(dataFile.length() == 0) return;
		
		JAXBContext context = JAXBContext.newInstance(XmlExpenseWrapper.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		XmlExpenseWrapper wrapper = (XmlExpenseWrapper) unmarshaller.unmarshal(this.dataFile);
        expenses.addAll(wrapper.getExpenses());
        
        this.lastExpeId = getLastExpeId();
	}

	public static XmlExpenseDAO getInstance(File dataFile) throws JAXBException, IOException {
		if(instance == null){
			instance = new XmlExpenseDAO(dataFile);
		}
		return instance;
	}
	
	@Override
	public ObservableList<Expense> getAll() {
		return expenses;
	}

	@Override
	public Optional<Expense> getById(long id) {
		return null;
	}

	@Override
	public void add(Expense expense) {
		expense.setExpeId(lastExpeId);
		expenses.add(expense);
	}

	@Override
	public void delete(Expense t) {
		
	}

	@Override
	public void update(Expense t) {
		
	}

	@Override
	public void save() throws Exception {
		JAXBContext context = JAXBContext.newInstance(XmlExpenseWrapper.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		XmlExpenseWrapper wrapper = new XmlExpenseWrapper();
		wrapper.setExpenses(expenses);
		
		marshaller.marshal(wrapper, dataFile);
	}

	@Override
	public ObservableList<Expense> getByDate(LocalDate localDate) {
		return null;
	}
}
