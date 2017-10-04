package com.app.budget.dao.xml;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.app.budget.dao.IDAO;
import com.app.budget.dao.IUserDAO;
import com.app.budget.dao.txt.TxtUserDAO;
import com.app.budget.model.User;

import javafx.collections.ObservableList;

public class XmlUserDAO extends IUserDAO<User>{

	private static XmlUserDAO instance;
	private File dataFile;

	private XmlUserDAO(File dataFile) throws IOException, JAXBException {
		this.dataFile = dataFile;
		if(!(dataFile.exists() && !dataFile.isDirectory())) dataFile.createNewFile();
		if(dataFile.length() == 0) return;
		
		JAXBContext context = JAXBContext.newInstance(XmlUserWrapper.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		XmlUserWrapper wrapper = (XmlUserWrapper) unmarshaller.unmarshal(this.dataFile);
		users.addAll(wrapper.getUsers());
		
		this.lastUserId = getLastUserId();
	}
	
	public static XmlUserDAO getInstance(File dataFile) throws IOException, JAXBException{
		if(instance == null){
			instance = new XmlUserDAO(dataFile);
		}
		return instance;
	}
	
	@Override
	public ObservableList<User> getAll() {
		return users;
	}

	@Override
	public Optional<User> getById(long id) {
		for(User user : users){
			if(user.getUserId() == id){
				return Optional.of(user);
			}
		}
		return Optional.empty();
	}

	@Override
	public void add(User user) {
		user.setUserId(++lastUserId);
		users.add(user);
	}

	@Override
	public void delete(User user) {
	}

	@Override
	public void update(User user) {
	}

	@Override
	public void save() throws Exception {
		JAXBContext context = JAXBContext.newInstance(XmlUserWrapper.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		XmlUserWrapper wrapper = new XmlUserWrapper();
		wrapper.setUsers(users);
		
		marshaller.marshal(wrapper, dataFile);
	}
	
	public static void main(String[] args) throws Exception {
		File userFile = new File("f:/users.txt");
		File userFileXml = new File("f:/users.xml");
		IDAO<User> t1 = TxtUserDAO.getInstance(userFile);
		System.out.println(t1.getAll().size());
		IDAO<User> t2 = XmlUserDAO.getInstance(userFileXml);
		System.out.println(t2.getAll().size());
		
		for(User i : t1.getAll()){
			if(!t2.getAll().contains(i)){
				t2.add(i);
			}
		}
		t2.save();
	}
}
