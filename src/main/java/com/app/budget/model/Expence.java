package com.app.budget.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;

public class Expence {

	private ObjectProperty<LocalDate> operDt;
	private ObjectProperty<Item> expeItem;
	private ObjectProperty<BigDecimal> expeItemCount;
	private ObjectProperty<BigDecimal> expeItemPrice;
	
	public Expence(){
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
	
	
}
