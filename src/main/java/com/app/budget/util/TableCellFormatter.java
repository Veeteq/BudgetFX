package com.app.budget.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.app.budget.model.Expence;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;

public class TableCellFormatter extends TableCell<Expence, BigDecimal>{

	private DecimalFormat df;
	
	public TableCellFormatter(int precision){
		if(precision == 3){
			df = new DecimalFormat("0.000");
		}else{
			df = new DecimalFormat("0.00");
		}
	}
	
	@Override
	protected void updateItem(BigDecimal item, boolean empty) {
		super.updateItem(item, empty);
		if(item != null){
			this.setText(df.format(item));
			this.setAlignment(Pos.CENTER_RIGHT);
		}
	}
}
