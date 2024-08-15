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
	
	@FXML
	TextField ipTextField;
	
	public void Connect(ActionEvent event) throws IOException {
		String ip = ipTextField.getText();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chat.fxml"));
		Parent root = loader.load();
		ChatController chatController = loader.getController();
		
		// create seperate thread to handle conenction
		new ConnectionHandler(ip, chatController).start();
		
		// switch scene to chat
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
}
