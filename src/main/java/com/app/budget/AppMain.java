package com.app.budget;

import java.io.IOException;
import java.time.LocalDate;

import com.app.budget.model.Expence;
import com.app.budget.model.ExpenceDAO;
import com.app.budget.model.Item;
import com.app.budget.model.ItemDAO;
import com.app.budget.model.User;
import com.app.budget.model.UserDAO;
import com.app.budget.view.BudgetOverviewController;
import com.app.budget.view.RootOverviewController;
import com.app.budget.view.UserEditDialogController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppMain extends Application {

	private static final String APP_TITLE = "Budget";
	
	private Stage window;
	private BorderPane rootOverview;
	
	private ObservableList<Expence> expences = FXCollections.observableArrayList();
	private ObservableList<Item> items = FXCollections.observableArrayList();
	private ObservableList<User> users = FXCollections.observableArrayList();
	
	ExpenceDAO expenceDAO;
	ItemDAO itemDAO;
	UserDAO userDAO;
	
	public AppMain() {
		expenceDAO = new ExpenceDAO();
		itemDAO = new ItemDAO();
		userDAO = new UserDAO(users);
	}
	
	@Override
	public void start(Stage window) {
		this.window = window;
		this.window.setTitle(APP_TITLE);
		this.window.getIcons().add(new Image("file:src/main/resources/budget.png"));
		
		initRootOverview();
		initBudgetOverview();
	}

	private void initRootOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("view/RootOverview.fxml"));
			loader.setControllerFactory(cf -> buildController(window));
			rootOverview = loader.load();
			
			RootOverviewController controller = loader.getController();
			controller.setAppMain(this);
			
			Scene scene = new Scene(rootOverview);
			window.setScene(scene);
			window.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void initBudgetOverview(){
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

	private RootOverviewController buildController(Stage window){
		return new RootOverviewController(window);
	}

	
	public ObservableList<Expence> getExpences(LocalDate localDate){
		expences.clear();
		expences.addAll(expenceDAO.getByDate(localDate));
		return expences;
	}
	
	public ObservableList<Item> getItems() {
		System.out.println("getItems");
		items.clear();
		items.addAll(itemDAO.getAll());
		return items;
	}

	public ObservableList<User> getUsers(){
		System.out.println("getUsers");
		//users.clear();
		//users.addAll(userDAO.getAll());
		return users;
	}
	
	public boolean openUserEditDialog(User user){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("view/UserEditDialog.fxml"));
			AnchorPane userEditDialog = loader.load();
			
			Stage dialogWindow = new Stage();
			dialogWindow.setTitle("Edit User");
			dialogWindow.initModality(Modality.WINDOW_MODAL);
			dialogWindow.initOwner(window);
			
			Scene scene = new Scene(userEditDialog);
			dialogWindow.setScene(scene);
			
			UserEditDialogController controller = loader.getController();
			controller.setDialogWindow(dialogWindow);
			controller.setUser(user);
			
			dialogWindow.showAndWait();
			
			return controller.isOKClicked();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
