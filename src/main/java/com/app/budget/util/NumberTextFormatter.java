package com.app.budget.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParsePosition;

import javafx.scene.control.TextFormatter;

public class NumberTextFormatter {

	public static TextFormatter<?> getNumberTextFormatter() {
		DecimalFormat df = new DecimalFormat("0.###");
		df.setRoundingMode(RoundingMode.HALF_UP);

		ParsePosition parsePosition = new ParsePosition(0);
		TextFormatter<String> textFormatter = new TextFormatter<>(
				TextFormatter.IDENTITY_STRING_CONVERTER, "", change -> {
					if (change.getControlNewText().isEmpty()) {
						return change;
					}
					parsePosition.setIndex(0);
					Object object = df.parseObject(change.getControlNewText(), parsePosition);
					if (object == null || parsePosition.getIndex() < change.getControlNewText().length()) {
						return null;
					} else {
						return change;
					}
				});
		return textFormatter;
	}

}
