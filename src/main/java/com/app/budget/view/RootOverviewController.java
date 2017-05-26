package com.app.budget.view;

import com.app.budget.AppMain;
import com.app.budget.model.DataModel;
import com.app.budget.model.User;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class RootOverviewController {

	private AppMain appMain;
	private DataModel dataModel;
	
	public RootOverviewController(AppMain appMain, DataModel dataModel, Stage window) {
		this.appMain = appMain;
		this.dataModel = dataModel;
		window.setOnCloseRequest(e -> dataModel.close());
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
			dataModel.getUsers().add(user);
		}
	}

	@FXML
	private void onEditCategories(){
	}
	
	public DataModel getDataModel(){
		return dataModel;
	}
}
