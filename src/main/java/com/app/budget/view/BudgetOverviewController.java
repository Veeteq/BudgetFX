package com.app.budget.view;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.app.budget.model.DataModel;
import com.app.budget.model.Expence;
import com.app.budget.model.Item;
import com.app.budget.model.User;
import com.app.budget.util.DateUtil;
import com.app.budget.util.DecimalStringConverter;
import com.app.budget.util.NumberTextFormatter;
import com.app.budget.util.TableCellFormatter;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.transformation.FilteredList;
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

	private DataModel dataModel;
	
	private ObjectProperty<BigDecimal> expeItemCountValue = new SimpleObjectProperty<BigDecimal>();
	private ObjectProperty<BigDecimal> expeItemPriceValue = new SimpleObjectProperty<BigDecimal>();
	private ObjectProperty<BigDecimal> expeSumAmountValue = new SimpleObjectProperty<BigDecimal>();
	
	private FilteredList<Expence> filteredList;
	
	public BudgetOverviewController(){
		System.out.println("controller");
		expeItemCountValue.set(BigDecimal.valueOf(1));
		expeItemPriceValue.set(BigDecimal.valueOf(0));
	}
	
	@FXML
	public void initialize(){
		System.out.println("init");
		expeOperDtComboBox.setConverter(DateUtil.getConverter());
		expeOperDtComboBox.setValue(LocalDate.now());
		expeOperDtComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
			filteredList.setPredicate(expence -> {
				if(expence.getOperDt().equals(newVal)){
					return true;
				}
				return false;
			});
			expenceTable.refresh();
		});
		
		expeItemColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeItemProperty());
		//expeUserColumn.setCellValueFactory(cellData -> cellData.getValue().getExpeUserProperty());
		
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
		expeItemCountTextField.setAlignment(Pos.CENTER_RIGHT);
		
		expeItemPriceTextField.setTextFormatter(NumberTextFormatter.getNumberTextFormatter());
		expeItemPriceTextField.setAlignment(Pos.CENTER_RIGHT);
		
		expeSumAmountTextField.setEditable(false);
		expeSumAmountTextField.setAlignment(Pos.CENTER_RIGHT);
		
		Bindings.bindBidirectional(expeItemCountTextField.textProperty(), expeItemCountValue, new DecimalStringConverter(3));
		Bindings.bindBidirectional(expeItemPriceTextField.textProperty(), expeItemPriceValue, new DecimalStringConverter(2));
		Bindings.bindBidirectional(expeSumAmountTextField.textProperty(), expeSumAmountValue, new DecimalStringConverter(2));
		
		expeItemCountValue.addListener((obs, oldVal, newVal) -> expeSumAmountValue.set(newVal.multiply(expeItemPriceValue.get())));
		expeItemPriceValue.addListener((obs, oldVal, newVal) -> expeSumAmountValue.set(newVal.multiply(expeItemCountValue.get())));
	}
	
	public void setDataModel(DataModel dataModel){
		System.out.println("setAppMain");
		this.dataModel = dataModel;
		
		expeItemIdComboBox.setItems(this.dataModel.getItems());
		expeItemIdComboBox.getSelectionModel().select(0);
		
		expeUserIdComboBox.setItems(this.dataModel.getUsers());
		expeUserIdComboBox.getSelectionModel().select(0);
		
		filteredList = new FilteredList<Expence>(this.dataModel.getExpences(), expence -> {
			if(expence.getOperDt().equals(expeOperDtComboBox.getValue())){
				return true;
			}
			return false;
		});
		expenceTable.setItems(filteredList);
	}
	
	private void showExpenceDetails(Expence expence) {
		if(expence == null){
			//expeItemCountTextField.setText("1.000");
			//expeItemPriceTextField.setText("0.00");
		}else{
			expeItemIdComboBox.getSelectionModel().select(expence.getExpeItem());
			expeUserIdComboBox.getSelectionModel().select(expence.getExpeUser());
			expeItemCountTextField.setText(expence.getExpeItemCount().toString());
			expeItemPriceTextField.setText(expence.getExpeItemPrice().toString());
		}
	}
	
	public void onSave(){
		Expence expence = new Expence();
		expence.setOperDt(expeOperDtComboBox.getValue());
		expence.setExpeItem(expeItemIdComboBox.getValue());
		expence.setExpeUser(expeUserIdComboBox.getValue());
		expence.setExpeItemCount(expeItemCountValue.get());
		expence.setExpeItemPrice(expeItemPriceValue.get());
		dataModel.getExpences().add(expence);
	}
	
	public void onUpdate(){
	}
	
	public void onDelete(){
	}
}