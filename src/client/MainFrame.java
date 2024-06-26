package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import Header.ObjectMessage;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MainFrame extends JFrame {

	Output output;
	InputThread inputThread;
	LoginJPanel loginJPanel;
	PanelAdapter panelAdapter;

	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Thread input;
	
	private String name;
	
	public Output getOutput() {
		return output;
	}

	public MainFrame() {
		initData();
		setInitLayout();

		// Socket socket = new Socket("localhost", 5000);
		try {
			socket = new Socket("192.168.0.25", 5002);
			oos = new ObjectOutputStream(socket.getOutputStream());
			//ois = new ObjectInputStream(socket.getInputStream());
			output = new Output(oos);
			inputThread = new InputThread(socket, this);
			input = new Thread(inputThread);
			input.start();
			

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
		setSize(416, 739);

		loginJPanel = new LoginJPanel(this);
		panelAdapter = new PanelAdapter(this);
	}

	private void setInitLayout() {

		setLayout(null);
		// setResizable(false);
		setLocation(0, 0);
		setVisible(true);
		add(panelAdapter);
		panelAdapter.setVisible(false);
		add(loginJPanel);

	}

	public static void main(String[] args) {
		new MainFrame();
		
		
		
	}
}
