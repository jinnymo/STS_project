package client;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	LoginJPanel loginJPanel;
	
	public MainFrame() {
		initData();
		setInitLayout();
		
	}

	private void initData() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,700);
		
		loginJPanel = new LoginJPanel();
		
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
