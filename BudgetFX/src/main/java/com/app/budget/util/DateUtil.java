package com.app.budget.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

public class DateUtil {

	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

	public static String format(LocalDate localDate) {
		if (localDate == null)
			return null;
		return DATE_FORMATTER.format(localDate);
	}

	public static LocalDate parse(String dateString) {
		return DATE_FORMATTER.parse(dateString, LocalDate::from);
	}

	public static LocalDate parse2(String dateString) {
		return LocalDate.parse(dateString, DATE_FORMATTER);
	}

	public static StringConverter<LocalDate> getConverter() {
		return new StringConverter<LocalDate>() {

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return DATE_FORMATTER.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, DATE_FORMATTER);
				} else {
					return null;
				}
			}
		};
	}
}
