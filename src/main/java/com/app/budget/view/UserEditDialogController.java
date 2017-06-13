package com.app.budget.view;

import com.app.budget.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserEditDialogController {

	@FXML
	private TextField userNameTextField;
	
	@FXML
	private TextField userTypeTextField;
	
	@FXML
	private TextField userDescriptionTextField;
	
	private User user;
	private Stage dialogWindow;
	private boolean okClicked = false;
	
	@FXML
	private void initialize(){
	}
	
	public void setDialogWindow(Stage dialogWindow){
		this.dialogWindow = dialogWindow;
	}
	
	public void setUser(User user){
		this.user= user;
	}
	
	public boolean isOKClicked(){
		return okClicked;
	}
	
	public void onOK(){
		user.setUserName(userNameTextField.getText());
		user.setUserType(userTypeTextField.getText());
		user.setUserDescription(userDescriptionTextField.getText());
		
		okClicked = true;
		dialogWindow.close();
	}
	
	
	public void onCancel(){
		dialogWindow.close();
	}
}
