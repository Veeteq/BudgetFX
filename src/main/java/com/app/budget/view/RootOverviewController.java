package com.app.budget.view;

import com.app.budget.AppMain;
import com.app.budget.model.User;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RootOverviewController {

	private AppMain appMain;
	
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
	
	@FXML
	private void onEditItems(){
	}

	@FXML
	private void onEditUsers(){
		User user = new User();
		boolean okClicked = appMain.openUserEditDialog(user);
		System.out.println(user);
		if(okClicked){
			appMain.getUsers().add(user);
		}
	}

	@FXML
	private void onEditCategories(){
	}
	
	public void setAppMain(AppMain appMain){
		System.out.println("Set AppMain");
		this.appMain = appMain;
	}
}
