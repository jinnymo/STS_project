package client;

import java.awt.Color;

import javax.swing.JList;
import javax.swing.JPanel;

public class UserListPanel extends JPanel implements Runnable{

	private JList<String> jList;
	
	
	public UserListPanel() {
		initData();
		setInitLayout();
	}

	private void initData() {
		
		
	}

	private void setInitLayout() {
		setLayout(null);
		setSize(400,550);
		setLocation(0,50);
		setBackground(Color.blue);
		
	}

	@Override
	public void run() {
		
		
	}
}
