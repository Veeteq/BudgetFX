package com.app.budget.dao.txt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import com.app.budget.dao.IDAO;
import com.app.budget.dao.IUserDAO;
import com.app.budget.model.User;

import javafx.collections.ObservableList;

public class TxtUserDAO extends IUserDAO<User>{

	private static TxtUserDAO instance;
	private File dataFile;
	
	private TxtUserDAO(File dataFile) throws IOException{
		this.dataFile = dataFile;
		try {
			if(!(dataFile.exists() && !dataFile.isDirectory())){
				dataFile.createNewFile();
			}
			
			FileReader userFileReader = new FileReader(this.dataFile);
			BufferedReader br = new BufferedReader(userFileReader);
			String lineTxt = br.readLine();
			String[] lineData;
			while(lineTxt != null){
				lineData = lineTxt.split("\\t");
				User user = new User();
				
				long userId = Integer.parseInt(lineData[0]);
				
				user.setUserId(userId);
				user.setUserName(lineData[1]);
				
				users.add(user);
				lineTxt = br.readLine();
			}
			
			br.close();
			userFileReader.close();

			lastUserId = getLastUserId();
			
		} catch (IOException e) {
			throw new IOException(e);
		} 
	}
	
	public static TxtUserDAO getInstance(File userFile) throws IOException {
		System.out.println("UserDAO: getInstance");		
		if (instance == null){
			instance = new TxtUserDAO(userFile);
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
	public void delete(User t) {
	}

	@Override
	public void update(User t) {
	}

	@Override
	public void save() throws IOException {
		System.out.println("Save users");
		FileWriter userFileWriter = new FileWriter(dataFile);
		BufferedWriter bw = new BufferedWriter(userFileWriter);
		for(User user : users){
			bw.write(String.format("%d\t%s\t%s\t%s",
					               user.getUserId(),
					               user.getUserName(),
					               user.getUserType(),
					               user.getUserDescription()));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		userFileWriter.close();
	}
	
	public static void main(String[] args) throws IOException {
		File userFile = new File("f:/users.txt");
		IDAO<User> u = TxtUserDAO.getInstance(userFile);
		for(User i : u.getAll()){
			System.out.println(i.getUserName());
		}
	}
}