package com.app.budget.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;

import com.app.budget.AppMain;
import com.app.budget.model.Expence;
import com.app.budget.model.Item;
import com.app.budget.model.User;
import com.app.budget.util.DateUtil;
import com.app.budget.util.NumberTextFormatter;
import com.app.budget.util.TableCellFormatter;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BudgetOverviewController {
	/*
	 * https://dzone.com/articles/javafx-numbertextfield-and
	 */
	
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
	private ComboBox<User> expeUserIdComboBox;
	
	@FXML
	private ComboBox<Item> expeItemIdComboBox;

	@FXML
	private Button saveBtn;
	
	@FXML
	private Button updateBtn;

	@FXML
	private Button deleteBtn;

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
		expeItemCountColumn.setCellFactory(cell -> new TableCellFormatter(3));
		
		expeItemPriceColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeItemPriceProperty());
		expeItemPriceColumn.setCellFactory(cell -> new TableCellFormatter(2));
		
		expeSumAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeSumAmountProperty());
		expeSumAmountColumn.setCellFactory(cell -> new TableCellFormatter(2));
		
		showExpenceDetails(null);
		
		expenceTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> showExpenceDetails(newVal));
		
		/* expeItemCountTextField - settings */
		expeItemCountTextField.setTextFormatter(NumberTextFormatter.getNumberTextFormatter());
		expeItemCountTextField.textProperty().addListener((obs, oldVal, newVal) -> {
			if(expeItemPriceTextField.getText() != null && !expeItemPriceTextField.getText().isEmpty()){
				BigDecimal itemCount = new BigDecimal(newVal);
				BigDecimal itemPrice = new BigDecimal(expeItemPriceTextField.textProperty().get());
				expeSumAmountTextField.setText(itemCount.multiply(itemPrice).toString());
			}
		});
		expeItemCountTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
			DecimalFormat df = new DecimalFormat("0.000");
			df.setRoundingMode(RoundingMode.HALF_UP);
			if(!newVal.booleanValue()){
				BigDecimal itemCount = new BigDecimal(expeItemCountTextField.getText());
				expeItemCountTextField.setText(df.format(itemCount));
			}
		});
		expeItemCountTextField.setAlignment(Pos.CENTER_RIGHT);
		
		/* expeItemPriceTextField - settings */
		expeItemPriceTextField.setTextFormatter(NumberTextFormatter.getNumberTextFormatter());
		expeItemPriceTextField.textProperty().addListener((obs, oldVal, newVal) -> {
			if(expeItemCountTextField.getText() != null && !expeItemCountTextField.getText().isEmpty()){
				BigDecimal itemPrice = new BigDecimal(newVal);
				BigDecimal itemCount = new BigDecimal(expeItemCountTextField.textProperty().get());
				expeSumAmountTextField.setText(itemCount.multiply(itemPrice).toString());			
			}
		});
		expeItemPriceTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
			DecimalFormat df = new DecimalFormat("0.00");
			df.setRoundingMode(RoundingMode.HALF_UP);
			if(!newVal.booleanValue()){
				BigDecimal itemCount = new BigDecimal(expeItemPriceTextField.getText());
				expeItemPriceTextField.setText(df.format(itemCount));
			}
		});
		expeItemPriceTextField.setAlignment(Pos.CENTER_RIGHT);
		
		/* expeSumAmountTextField - settings */
		expeSumAmountTextField.setAlignment(Pos.CENTER_RIGHT);
		expeSumAmountTextField.setEditable(false);
		expeSumAmountTextField.textProperty().addListener((obs, oldVal, newVal) -> {
			DecimalFormat df = new DecimalFormat("0.00");
			df.setRoundingMode(RoundingMode.HALF_UP);
			BigDecimal sumAmount = new BigDecimal(newVal);
			expeSumAmountTextField.setText(df.format(sumAmount));
		});
	}
	
	public void setAppMain(AppMain appMain){
		System.out.println("setAppMain");
		this.appMain = appMain;
		
		expeItemIdComboBox.setItems(this.appMain.getItems());
		expeItemIdComboBox.getSelectionModel().select(0);
		
		expeUserIdComboBox.setItems(this.appMain.getUsers());
		expeUserIdComboBox.getSelectionModel().select(0);
		
		expenceTable.setItems(this.appMain.getExpences(expeOperDtComboBox.getValue()));
	}
	
	private void showExpenceDetails(Expence expence) {
		if(expence == null){
			expeItemCountTextField.setText("1.000");
			expeItemPriceTextField.setText("0.00");
		}else{
			expeItemCountTextField.setText(expence.getExpeItemCount().toString());
			expeItemPriceTextField.setText(expence.getExpeItemPrice().toString());
			expeItemIdComboBox.getSelectionModel().select(expence.getExpeItem());
		}
	}
	
	public void onSave(){
		Expence expence = new Expence();
		expence.setOperDt(expeOperDtComboBox.getValue());
		expence.setExpeItem(expeItemIdComboBox.getValue());
		expence.setExpeItemCount(new BigDecimal(expeItemCountTextField.getText()));
		expence.setExpeItemPrice(new BigDecimal(expeItemPriceTextField.getText()));
		appMain.getExpences(expeOperDtComboBox.getValue()).add(expence);
	}
	
	public void onUpdate(){
	}
	
	public void onDelete(){
	}
}
