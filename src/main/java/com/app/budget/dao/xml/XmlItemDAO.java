package com.app.budget.dao.xml;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.app.budget.dao.IDAO;
import com.app.budget.dao.IItemDAO;
import com.app.budget.dao.txt.TxtItemDAO;
import com.app.budget.model.Item;

import javafx.collections.ObservableList;

public class XmlItemDAO extends IItemDAO<Item>{

	private static XmlItemDAO instance;
	private File dataFile;
	
	private XmlItemDAO(File dataFile) throws IOException, JAXBException {
		System.out.println("file: " + dataFile.getAbsolutePath());
		this.dataFile = dataFile;
		if(!(dataFile.exists() && !dataFile.isDirectory())) dataFile.createNewFile();
		if(dataFile.length() == 0) return;
		
		JAXBContext context = JAXBContext.newInstance(XmlItemWrapper.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		XmlItemWrapper wrapper = (XmlItemWrapper) unmarshaller.unmarshal(this.dataFile);
		items.addAll(wrapper.getItems());
		
		this.lastItemId = getLastItemId();
	}

	public static XmlItemDAO getInstance(File dataFile) throws IOException, JAXBException{
		if(instance == null){
			instance = new XmlItemDAO(dataFile);
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
		System.out.print(item.getItemId() + "\t");
		System.out.println(item.getItemName());

	}

	@Override
	public void delete(Item item) {
	}

	@Override
	public void update(Item item) {
	}

	@Override
	public void save() throws Exception {
		JAXBContext context = JAXBContext.newInstance(XmlItemWrapper.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		XmlItemWrapper wrapper = new XmlItemWrapper();
		wrapper.setItems(items);
		
		marshaller.marshal(wrapper, dataFile);
	}
	
	public static void main(String[] args) throws Exception {
		File itemFile = new File("f:/items.txt");
		File itemFileXml = new File("f:/items.xml");
		IDAO<Item> t1 = TxtItemDAO.getInstance(itemFile);
		System.out.println(t1.getAll().size());
		IDAO<Item> t2 = XmlItemDAO.getInstance(itemFileXml);
		System.out.println(t2.getAll().size());
		
		for(Item i : t1.getAll()){
			if(!t2.getAll().contains(i)){
				t2.add(i);
			}
		}
		t2.save();
	}
}

