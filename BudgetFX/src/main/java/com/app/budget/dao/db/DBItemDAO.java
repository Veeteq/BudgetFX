package com.app.budget.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.sql.DataSource;

import com.app.budget.dao.IItemDAO;
import com.app.budget.model.Item;

import javafx.collections.ObservableList;

public class DBItemDAO extends IItemDAO<Item>{

	private DataSource dataSource;
	private static DBItemDAO instance;
	
	private DBItemDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public static DBItemDAO getInstance(DataSource dataSource) {
		if(instance == null){
			instance = new DBItemDAO(dataSource);
		}
		return instance;
	}
	
	@Override
	public ObservableList<Item> getAll() {
		try{
			Connection connection = getConncection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM ITEMS");
			ResultSet rst = statement.executeQuery();
			return (ObservableList<Item>) StreamSupport.stream(new Spliterators.AbstractSpliterator<Item>(Long.MAX_VALUE, Spliterator.ORDERED) {

				@Override
				public boolean tryAdvance(Consumer<? super Item> action) {
					try {
						if(!rst.next()){
							return false;
						}
						action.accept(createItem(rst));
						return true;
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}
			}, false).onClose(() -> closeConnection(connection))
					 .collect(Collectors.toList());
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Optional<Item> getById(long id) {
		try{
			Connection connection = getConncection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM ITEMS WHERE ITEM_ID=?");
			
			statement.setLong(1, id);
			ResultSet rst = statement.executeQuery();
			if(rst.next()){
				return Optional.of(createItem(rst));
			}else{
				return Optional.empty();
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void add(Item t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Item t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Item t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() throws Exception {
	}

	private Item createItem(ResultSet rst) throws SQLException{
		Item item = new Item(rst.getLong("item_id"), rst.getString("item_name"));
		return item;
	}
	
	private Connection getConncection() throws SQLException{
		return this.dataSource.getConnection();
	}
	
	private void closeConnection(Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
