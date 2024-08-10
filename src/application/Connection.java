package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	private BufferedReader in = null;
	private PrintWriter out = null;
	private Socket connection = null;
	public Connection(String ip, int port) throws UnknownHostException {
		try {
			// try to make socket connection with ip + port
			Socket connection = new Socket(ip, port);
			
			// set connection
			setConnection(connection);
			
			// create input and output streams
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
			
			// set input and output streams
			setIn(in);
			setOut(out);
			
			System.out.println("here");
			
			
		} catch (ConnectException err) {
			try {
				// if can't connect start listening as a server socket
				ServerSocket server = new ServerSocket(port);
				
				System.out.println("listening");
				// listen for socket connection
				Socket connection = server.accept();
				
				// set connection
				setConnection(connection);
				
				// create input and output streams
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
				
				// set input and output streams
				setIn(in);
				setOut(out);
				
			} catch (IOException err1) {
				err1.printStackTrace();
			}
			
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	public void disconnect() {
		try {
			getIn().close();
			getOut().close();
			getConnection().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// getters and setters
	public void setIn(BufferedReader in) {
		this.in = in;
	}
	public BufferedReader getIn() {
		return this.in;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
	public PrintWriter getOut() {
		return this.out;
	}
	public void setConnection(Socket connection) {
		this.connection = connection;
	}
	public Socket getConnection() {
		return this.connection;
	}
}
