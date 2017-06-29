package com.app.budget.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertDisplay {

	public static void displayAlert(AlertType alertType, String msg){
		Alert alert = new Alert(alertType);
		alert.setTitle("Configuration Error!");
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
