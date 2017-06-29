package com.app.budget.view;

import com.app.budget.AppMain;
import com.app.budget.model.DataModel;
import com.app.budget.model.User;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RootOverviewController {

	private AppMain appMain;
	private DataModel dataModel;
	private Stage window;
	
	public RootOverviewController(AppMain appMain, DataModel dataModel, Stage window) {
		this.appMain = appMain;
		this.dataModel = dataModel;
		this.window = window;

		window.setOnCloseRequest(e -> {
			try{
				dataModel.close();
			}catch(Exception exc){
				exc.printStackTrace();
			}
		});
	}
	
	@FXML
	void initialize(){
	}
	
	@FXML
	private void onClose(){
		window.getOnCloseRequest().handle(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
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
			dataModel.add(user);
		}
	}

	@FXML
	private void onEditCategories(){
	}
	
	public DataModel getDataModel(){
		return dataModel;
	}
}
