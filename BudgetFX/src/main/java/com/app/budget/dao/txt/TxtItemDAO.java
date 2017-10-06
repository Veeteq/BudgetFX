package com.app.budget.dao.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Optional;

import com.app.budget.dao.IItemDAO;
import com.app.budget.model.Item;

import javafx.collections.ObservableList;

public class TxtItemDAO extends IItemDAO<Item> {

	private static TxtItemDAO instance;
	private File dataFile;
	
	private TxtItemDAO(File itemFile) throws IOException{
		this.dataFile = itemFile;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dataFile),"UTF-8"));
			String lineTxt = br.readLine();
			String[] lineData;
			while(lineTxt != null){
				lineData = lineTxt.split("\\t");
				Item item = new Item(Integer.parseInt(lineData[0]), lineData[2]);
				item.setCategoryId(Integer.parseInt(lineData[1]));
				add(item);
				lineTxt = br.readLine();
			}
			br.close();
			
			this.lastItemId = getLastItemId();
			
		} catch (IOException e) {
			throw new IOException(e);
		} 
	}
	
	public static TxtItemDAO getInstance(File itemFile) throws IOException {
		System.out.println("ItemDAO: getInstance");		
		if (instance == null){
			instance = new TxtItemDAO(itemFile);
		}
		return instance;
	}

	@Override
	public ObservableList<Item> getAll() {
		return items;
	}

	@Override
	public Optional<Item> getById(long id) {
		for(Item item : items){
			if(item.getItemId() == id){
				return Optional.of(item);
			}
		}
		return Optional.empty();
	}

	@Override
	public void add(Item item) {
		item.setItemId(++lastItemId);
		items.add(item);
	}

	@Override
	public void delete(Item t) {
	}

	@Override
	public void update(Item t) {
	}

	@Override
	public void save() throws IOException {
		System.out.println("Save items");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataFile),"UTF-8"));
		for(Item item : items){
            bw.write(String.format("%d\t%d\t%s",
                        		  item.getItemId(),
                        		  item.getCategoryId(),
                        		  item.getItemName()));			
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		File itemFile = new File("f:/items.txt");
		TxtItemDAO t = TxtItemDAO.getInstance(itemFile);
		for(Item i : t.getAll()){
			System.out.println(i.getItemId() + "\t" + i.getCategoryId() + "\t" + i.getItemName());
		}
	}
}