package com.app.budget;

import java.io.IOException;

import com.app.budget.model.Expence;
import com.app.budget.model.ExpenceDAO;
import com.app.budget.view.BudgetOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppMain extends Application {

	private static final String APP_TITLE = "Budget";
	
	private Stage window;
	private BorderPane rootOverview;
	
	private ObservableList<Expence> expences = FXCollections.observableArrayList();
	ExpenceDAO expenceDAO;
	
	public AppMain() {
		expenceDAO = new ExpenceDAO();
		expences.addAll(expenceDAO.getAll()); 
	}
	
	@Override
	public void start(Stage window) {
		this.window = window;
		this.window.setTitle(APP_TITLE);
		
		initRootOverview();
	}

	private void initRootOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("view/RootOverview.fxml"));
			rootOverview = loader.load();
			
			Scene scene = new Scene(rootOverview);
			window.setScene(scene);
			window.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void initBudhetOverview(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("view/BudgetOverview.fxml"));
			
			AnchorPane budgetOverview = loader.load();
			rootOverview.setCenter(budgetOverview);
			
			BudgetOverviewController controller = loader.getController();
			controller.setAppMain(this);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<Expence> getExpences(){
		return expences;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
