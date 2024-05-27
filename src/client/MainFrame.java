package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import Header.ObjectMessage;
import lombok.Getter;

public class MainFrame extends JFrame {

	Output output;
	InputThread inputThread;
	LoginJPanel loginJPanel;
	ObjectMessage objectMessage = new ObjectMessage();
	Socket socket;
	ObjectOutputStream oos;
	BufferedReader reader;
	
	
	public Output getOutput() {
		return output;
	}

	
	public MainFrame() {
		initData();
		setInitLayout();
		
		
		// Socket socket = new Socket("localhost", 5000);

	}

	public void connectServer() {
		
		try {
			socket = new Socket("localhost", 5000);
			
			oos = new ObjectOutputStream(socket.getOutputStream());	
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			output = new Output(oos, objectMessage);
			
			
			inputThread = new InputThread(reader);
			Thread input = new Thread(inputThread);
			
			
			
			input.start();
			
			try {
				
				input.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initData() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 700);

		loginJPanel = new LoginJPanel(this);

	}

	private void setInitLayout() {

		setLayout(null);
		setResizable(false);
		setLocation(0, 0);
		setVisible(true);

		add(loginJPanel);

	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
