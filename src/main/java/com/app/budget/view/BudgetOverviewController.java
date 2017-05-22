package com.app.budget.view;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.app.budget.AppMain;
import com.app.budget.model.Expence;
import com.app.budget.model.Item;
import com.app.budget.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BudgetOverviewController {
	
	@FXML
	private DatePicker expeOperDtComboBox;
	
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
	
	@FXML
	private TextField expeItemCountTextField;
	
	@FXML
	private TextField expeItemPriceTextField;
	
	@FXML
	private TextField expeSumAmountTextField;
	
	@FXML
	private ComboBox<Item> expeUserIdComboBox;
	
	@FXML
	private ComboBox<Item> expeItemIdComboBox;

	private AppMain appMain;
	
	public BudgetOverviewController(){
		System.out.println("controller");
	}
	
	@FXML
	public void initialize(){
		System.out.println("init");
		expeOperDtComboBox.setConverter(DateUtil.getConverter());
		expeOperDtComboBox.setValue(LocalDate.now());
		
		expeItemColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeItemProperty());
		expeItemCountColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeItemCountProperty());
		expeItemPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeItemPriceProperty());
		expeSumAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeSumAmountProperty());
		
		showExpenceDetails(null);
		expenceTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> showExpenceDetails(newVal));
		
		expeItemCountTextField.textProperty().addListener((obs, oldVal, newVal) -> {
			if(expeItemPriceTextField.getText() != null && !expeItemPriceTextField.getText().isEmpty()){
				BigDecimal itemCount = new BigDecimal(newVal);
				BigDecimal itemPrice = new BigDecimal(expeItemPriceTextField.textProperty().get());
				expeSumAmountTextField.setText(itemCount.multiply(itemPrice).toString());
			}
		});
		
		expeItemPriceTextField.textProperty().addListener((obs, oldVal, newVal) -> {
			if(expeItemCountTextField.getText() != null && !expeItemCountTextField.getText().isEmpty()){
				BigDecimal itemPrice = new BigDecimal(newVal);
				BigDecimal itemCount = new BigDecimal(expeItemCountTextField.textProperty().get());
				expeSumAmountTextField.setText(itemCount.multiply(itemPrice).toString());			
			}
		});
	}
	
	public void setAppMain(AppMain appMain){
		System.out.println("setAppMain");
		this.appMain = appMain;
		
		expeItemIdComboBox.setItems(this.appMain.getItems());
		expeItemIdComboBox.getSelectionModel().select(0);
		expenceTable.setItems(this.appMain.getExpences(expeOperDtComboBox.getValue()));
	}
	
	private void showExpenceDetails(Expence expence) {
		if(expence == null){
			expeItemCountTextField.setText("");
			expeItemPriceTextField.setText("");
		}else{
			expeItemCountTextField.setText(expence.getExpeItemCount().toString());
			expeItemPriceTextField.setText(expence.getExpeItemPrice().toString());
			expeItemIdComboBox.getSelectionModel().select(expence.getExpeItem());
		}
		
	}
}
