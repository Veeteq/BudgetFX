
import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.app.budget.model.DataModel;
import com.app.budget.model.Item;
import com.app.budget.model.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AppMain2 extends Application {

	@Override
	public void start(Stage window) throws IOException, JAXBException {
		GridPane root = new GridPane();
		
		DataModel dataModel = new DataModel();
		
		ComboBox<User> expeUserIdComboBox = new ComboBox<User>();
		expeUserIdComboBox.setItems(dataModel.getUsers());
		expeUserIdComboBox.setEditable(true);

		ComboBox<Item> expeItemIdComboBox = new ComboBox<Item>();
		expeItemIdComboBox.setItems(dataModel.getItems());
		expeItemIdComboBox.setEditable(true);
		
		new AutoCompleteComboBox<User>(expeUserIdComboBox);
		new AutoCompleteComboBox<Item>(expeItemIdComboBox);
		
		root.add(expeUserIdComboBox,0,0);
		root.add(expeItemIdComboBox,0,1);
		
		Scene scene = new Scene(root,600,400);
		window.setScene(scene);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
