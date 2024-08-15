package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ConnectionHandler extends Thread {
	String ip = null;
	private ChatController chatController = null;
	
	public ConnectionHandler(String ip, ChatController chatController) {
		this.ip = ip;
		this.chatController = chatController;
	}

	public void run() {
		System.out.println(this.ip);
		
		try {
			Connection connection = new Connection(this.ip, 9090);
			
			// set connection in controller
			chatController.out = connection.getOut();
			
			BufferedReader in = connection.getIn();
			
			String readMessage = null;
			while (true) {
				if ((readMessage = in.readLine()) != null) {
					chatController.receivedMessage = readMessage;
					Platform.runLater(() -> {
						chatController.receiveMessage();
					});
					
				}
				
			}
			
			//return;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
