package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	TextField ipTextField;
	
	public void Connect(ActionEvent event) throws IOException {
		String ip = ipTextField.getText();
		
		// get CreateConnection method from ChatController
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chat.fxml"));
		root = loader.load();
		ChatController chatController = loader.getController();
		
		// call CreateConnection method with ip specified by user
		chatController.CreateConnection(ip);
		
		// switch scene to chat
		Parent root = FXMLLoader.load(getClass().getResource("/Chat.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
