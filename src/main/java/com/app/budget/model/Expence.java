package com.app.budget.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Expence {

	private ObjectProperty<LocalDate> operDt = new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<Item> expeItem = new SimpleObjectProperty<Item>();
	private ObjectProperty<User> expeUser = new SimpleObjectProperty<User>();
	private ObjectProperty<BigDecimal> expeItemCount = new SimpleObjectProperty<BigDecimal>();
	private ObjectProperty<BigDecimal> expeItemPrice = new SimpleObjectProperty<BigDecimal>();
	private ObjectProperty<BigDecimal> expeSumAmonut = new SimpleObjectProperty<BigDecimal>();
	
	public Expence(){
		expeItemCount.addListener((obs, oldVal, newVal) -> {
			if(this.expeItemPrice.get() != null){
				this.expeSumAmonut.set(this.expeItemPrice.get().multiply(newVal));
			}
		});
		expeItemPrice.addListener((obs, oldVal, newVal) -> {
			if(this.expeItemCount.get() != null){
				this.expeSumAmonut.set(this.expeItemCount.get().multiply(newVal));
			}
		});
	}

	public ObjectProperty<LocalDate> getOperDtProperty(){
		return this.operDt;
	}
	
	public LocalDate getOperDt() {
		return operDt.get();
	}

	public void setOperDt(LocalDate operDt) {
		this.operDt.set(operDt);
	}

	public ObjectProperty<Item> getExpeItemProperty(){
		return expeItem;
	}

	public Item getExpeItem() {
		return expeItem.get();
	}

	public void setExpeItem(Item expeItem) {
		this.expeItem.set(expeItem);
	}

	public ObjectProperty<User> getExpeUserProperty(){
		return expeUser;
	}

	public User getExpeUser() {
		return expeUser.get();
	}

	public void setExpeUser(User expeUser) {
		this.expeUser.set(expeUser);
	}
	
	public ObjectProperty<BigDecimal> getExpeItemCountProperty(){
		return expeItemCount;
	}

	public BigDecimal getExpeItemCount(){
		return this.expeItemCount.get();
	}
	
	public void setExpeItemCount(BigDecimal expeItemAmount) {
		this.expeItemCount.set(expeItemAmount);
	}

	public ObjectProperty<BigDecimal> getExpeItemPriceProperty(){
		return expeItemPrice;
	}

	public BigDecimal getExpeItemPrice(){
		return this.expeItemPrice.get();
	}

	public void setExpeItemPrice(BigDecimal expeItemPrice) {
		this.expeItemPrice.set(expeItemPrice);
	}
	
	public ObjectProperty<BigDecimal> getExpeSumAmountProperty(){
		return expeSumAmonut;
	}

	public BigDecimal getExpeSumAmount(){
		return this.expeSumAmonut.get();
	}

	public void setExpeSumAmount(BigDecimal expeSumAmount) {
		this.expeSumAmonut.set(expeSumAmount);
	}	
}
