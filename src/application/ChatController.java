package application;

import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChatController {
	@FXML
	private AnchorPane background;
	private double x = 0;
	private double y = 0;
	
	public void makeRectangle(ActionEvent e) {
		int num = 10;
		while (num > 0) {
			Rectangle rec = new Rectangle(100, 100, Color.BLUE);
			rec.setX(x+=100);
			rec.setY(y+=100);
			background.getChildren().add(rec);
			num -= 1;
		}
	}
	
	public void CreateConnection(String ip) {
		System.out.println(ip);
		try {
			Connection connection = new Connection(ip, 9090);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
