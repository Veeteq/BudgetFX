package com.app.budget.view;

import java.math.BigDecimal;

import com.app.budget.AppMain;
import com.app.budget.model.Expence;
import com.app.budget.model.Item;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BudgetOverviewController {

	@FXML
	private TableView<Expence> expenceTable;
	
	@FXML
	private TableColumn<Expence, Item> expeItemColumn;
	
	@FXML
	private TableColumn<Expence, BigDecimal> expeItemCountColumn;
	
	@FXML
	private TableColumn<Expence, BigDecimal> expeItemPriceColumn;
	
	@FXML
	private TableColumn<Expence, BigDecimal> expeSumAmountColumn;
	
	private AppMain appMain;
	
	public BudgetOverviewController(){
	}
	
	@FXML
	public void initialize(){
		expeItemColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeItemProperty());
		expeItemCountColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeItemCountProperty());
		expeItemPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeItemPriceProperty());
		
	}
	
	public void setAppMain(AppMain appMain){
		this.appMain = appMain;
		expenceTable.setItems(this.appMain.getExpences());
	}
}
