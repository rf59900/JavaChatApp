package application;

import java.io.PrintWriter;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ChatController {
	
	// connection for sending messages
	public volatile PrintWriter out = null;
	
	// field for message received in ConnectionHandler
	public volatile String receivedMessage = null;
	
	// keeps track of where messages should be
	private int y = 0;
	
	@FXML
	private AnchorPane background;
	@FXML
	private TextArea messageBox;
	
	public void showConnection() {
		Rectangle rec = new Rectangle(1000, 1000, Color.RED);
		rec.setX(100);
		rec.setY(100);
		background.getChildren().add(rec);
	}
	
	public void sendMessage(ActionEvent event) {
		String message = messageBox.getText();
		
		// do not allow blank messages
		if (message.length() == 0) {
			return;
		}
		
		Text text = new Text(0, y+=20, message);
		background.getChildren().add(text);
		
		// send message through output stream
		out.println(message);
		
	}
	
	public void receiveMessage() {
		Text text = new Text(500, y+=20, receivedMessage);
		background.getChildren().add(text);
	}
	
	
	
}
