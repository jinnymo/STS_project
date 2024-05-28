package client;

import java.awt.Color;

import javax.swing.JPanel;

public class ChatPanel extends JPanel{

	public ChatPanel() {
		initData();
		setInitLayout();
	}

	private void initData() {
		
		
	}

	private void setInitLayout() {
		setLayout(null);
		setSize(400,550);
		setLocation(0,50);
		setBackground(Color.green);

	}
}
