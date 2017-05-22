package com.app.budget.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RootOverviewController {

	public RootOverviewController(Stage window) {
		window.setOnCloseRequest(e -> {
			//model.close();
			System.out.println("Close requested, " + e);
		});
	}
	
	@FXML
	void initialize(){
	}
	
	@FXML
	private void onClose(){
		Platform.exit();
		System.exit(0);
	}
}
