package com.app.budget.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Locale;

import javafx.util.StringConverter;

public class DecimalStringConverter extends StringConverter<BigDecimal>{

	private Locale locale = Locale.getDefault();
	private DecimalFormatSymbols dfs = new DecimalFormatSymbols(locale);
	private DecimalFormat df = new DecimalFormat();
	
	public DecimalStringConverter(int precision){
		if(precision == 3){
			df.applyPattern("0.000");
		}else{
			df.applyPattern("0.00");
		}
		df.setRoundingMode(RoundingMode.HALF_UP);
		df.setDecimalFormatSymbols(dfs);
		df.setParseBigDecimal(true);
	}

	@Override
	public String toString(BigDecimal value) {
		if(value == null) return df.format(0);
		return df.format(value);
	}

	@Override
	public BigDecimal fromString(String text) {
		if(text == null || text.isEmpty()) return BigDecimal.valueOf(0);
		try {
			return (BigDecimal) df.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
