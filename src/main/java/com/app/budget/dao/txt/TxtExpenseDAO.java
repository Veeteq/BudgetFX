package com.app.budget.dao.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import com.app.budget.dao.IDAO;
import com.app.budget.dao.IExpenseDAO;
import com.app.budget.model.Expense;
import com.app.budget.model.Item;
import com.app.budget.model.User;
import com.app.budget.util.DateUtil;

import javafx.collections.ObservableList;
import javafx.util.StringConverter;

public class TxtExpenseDAO extends IExpenseDAO<Expense>{

	private static TxtExpenseDAO instance;
	private File dataFile;
	
	private TxtExpenseDAO(File dataFile, IDAO<Item> itemDAO, IDAO<User> userDAO) throws IOException {
		this.dataFile = dataFile;
		StringConverter<LocalDate> st = DateUtil.getConverter();
		try {
			FileReader expeFileReader = new FileReader(dataFile);
			BufferedReader br = new BufferedReader(expeFileReader);
			String lineTxt = br.readLine();
			String[] lineData;
			while(lineTxt != null){
				lineData = lineTxt.split("\\t");
				Expense expence = new Expense(); 
				expence.setExpeId(Long.parseLong(lineData[0]));
				expence.setOperDt(st.fromString(lineData[1]));
				expence.setExpeItem(itemDAO.getById(Integer.parseInt(lineData[2])).get());
				expence.setExpeItemCount(BigDecimal.valueOf(Double.parseDouble(lineData[3])));
				expence.setExpeItemPrice(BigDecimal.valueOf(Double.parseDouble(lineData[4])));
				expence.setExpeCommTxt(lineData[5]);
				expence.setExpeUser(userDAO.getById(Integer.parseInt(lineData[6])).get());
				add(expence);
				lineTxt = br.readLine();
			}
			br.close();
			expeFileReader.close();
			
			lastExpeId = getLastExpeId();
			
		} catch (IOException e) {
			throw new IOException(e);
		} 

	}

	public static TxtExpenseDAO getInstance(File expeFile, IDAO<Item> itemDAO, IDAO<User> userDAO) throws IOException {
		System.out.println("ExpeDAO: getInstance");
		if (instance == null){
			instance = new TxtExpenseDAO(expeFile, itemDAO, userDAO);
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
	public void delete(Expense expense) {
		
	}

	@Override
	public void update(Expense t) {
		
	}

	@Override
	public void save() throws Exception {
		System.out.println("Save expenses");
		StringConverter<LocalDate> st = DateUtil.getConverter();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataFile),"UTF-8"));
		for(Expense expense : expenses){
			bw.write(String.format("%d\t%s\t%d\t%.3f\t%.2f\t%s\t%d",
					              expense.getExpeId(),
					              st.toString(expense.getOperDt()),
					              expense.getExpeItem().getItemId(),
					              expense.getExpeItemCount(),
					              expense.getExpeItemPrice(),
					              expense.getExpeCommTxt(),
					              expense.getExpeUser().getUserId()));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}

	@Override
	public ObservableList<Expense> getByDate(LocalDate localDate) {
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		StringConverter<LocalDate> st = DateUtil.getConverter();
		File itemFile = new File("f:/items.txt");
		IDAO<Item> i = TxtItemDAO.getInstance(itemFile);
		File userFile = new File("f:/users.txt");
		IDAO<User> u = TxtUserDAO.getInstance(userFile);
		File expenseFile = new File("f:/expences.txt");
		IDAO<Expense> x = TxtExpenseDAO.getInstance(expenseFile, i, u);
		for(Expense e : x.getAll()){
			System.out.println(String.format("%d\t%s\t%d\t%.3f\t%.2f\t%s\t%d",
		              e.getExpeId(),
		              st.toString(e.getOperDt()),
		              e.getExpeItem().getItemId(),
		              e.getExpeItemCount(),
		              e.getExpeItemPrice(),
		              e.getExpeCommTxt(),
		              e.getExpeUser().getUserId()));
		}
	}
}
