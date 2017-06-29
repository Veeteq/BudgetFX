package com.app.budget;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBException;

import com.app.budget.model.DataModel;
import com.app.budget.model.User;
import com.app.budget.util.AlertDisplay;
import com.app.budget.view.BudgetOverviewController;
import com.app.budget.view.RootOverviewController;
import com.app.budget.view.UserEditDialogController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AppMain extends Application {

	private static final String APP_TITLE = "Budget";
	private final static Logger LOGGER = Logger.getLogger(AppMain.class.getName());
	
	private Stage window;
	private BorderPane rootOverview;
	private RootOverviewController rootController;
		
	@Override
	public void start(Stage window) {
		LOGGER.info("AppMain: start");
		this.window = window;
		this.window.setTitle(APP_TITLE);
		this.window.getIcons().add(new Image("file:src/main/resources/budget.png"));
		
		initRootOverview();
		initBudgetOverview();
	}

	private void initRootOverview() {
		LOGGER.info("AppMain: initRootOverview");
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("view/RootOverview.fxml"));
			loader.setControllerFactory(cf -> buildController(window));
			rootOverview = loader.load();
			
			rootController = loader.getController();
			
			Scene scene = new Scene(rootOverview);
			window.setScene(scene);
			window.show();
			
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
	}

	
	public void initBudgetOverview(){
		LOGGER.info("AppMain: initBudgetOverview");
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(AppMain.class.getResource("view/BudgetOverview.fxml"));
			
			AnchorPane budgetOverview = loader.load();
			rootOverview.setCenter(budgetOverview);
			
			BudgetOverviewController controller = loader.getController();
			controller.setDataModel(rootController.getDataModel());
			
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, e.getMessage());
		}
	}

	private RootOverviewController buildController(Stage window){
		LOGGER.info("AppMain: buildController");
		try{
			return new RootOverviewController(this, buildDataModel(), window);			
		}catch(IOException | JAXBException | SQLException e){
			LOGGER.log(Level.WARNING, e.getMessage());
			AlertDisplay.displayAlert(AlertType.ERROR, e.getMessage());
			System.exit(1);
		}
		return null;
	}

	private DataModel buildDataModel() throws IOException, JAXBException, SQLException{
		LOGGER.info("AppMain: buildDataModel");
		return new DataModel();
	}
		
	public boolean openUserEditDialog(User user){
		LOGGER.info("AppMain: openUserEditDialog");
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
			LOGGER.log(Level.WARNING, "AppMain: error in openUserEditDialog");
			return false;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

} 