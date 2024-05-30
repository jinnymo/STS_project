package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import lombok.Getter;
@Getter
public class PanelAdapter extends JPanel implements ActionListener {

	private JButton showUser;
	private JButton showRoom;
	private JButton back;
	private JPanel topPanel;
	private ImageIcon arrow;
	private ImageIcon arrowclick;
	private UserListPanel userListPanel;
	private RoomListPanel roomListPanel;
	private ChatPanel chatPanel;

	PanelStatus panelStatus;

	public PanelAdapter() {
		// TODO Auto-generated constructor stub
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		// TODO Auto-generated method stub
		setSize(400, 700);
		setLocation(0, 0);
		setBackground(Color.cyan);
		setVisible(true);
		userListPanel = new UserListPanel();
		roomListPanel = new RoomListPanel();
		chatPanel = new ChatPanel();
		arrow = new ImageIcon("img/arrow.png");
		arrowclick = new ImageIcon("img/arrow_red.png");
		topPanel = new JPanel();
		showUser = new JButton("유저목록");
		showRoom = new JButton("방 목록");
		back = new JButton(arrow);

		topPanel.setLayout(null);
		topPanel.setSize(400, 50);
		topPanel.setLocation(0, 0);

		back.setSize(50, 50);
		showUser.setSize(200, 100);
		showRoom.setSize(200, 100);

		back.setLocation(0, 0);
		showUser.setLocation(0, 600);
		showRoom.setLocation(200, 600);

		back.setRolloverIcon(arrowclick);

	}

	private void setInitLayout() {
		// TODO Auto-generated method stub
		setLayout(null);
		add(showUser);
		add(showRoom);
		add(topPanel);
		topPanel.add(back);
		add(roomListPanel);
		add(chatPanel);
		add(userListPanel,0);
		userListPanel.setVisible(true);
	}

	private void addEventListener() {
		showUser.addActionListener(this);
		showRoom.addActionListener(this);
		back.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == showUser) {
			panelStatus = PanelStatus.USER;
		} else if (e.getSource() == showRoom) {
			panelStatus = PanelStatus.ROOM;
		} else if (e.getSource() == back) {
			panelStatus = PanelStatus.CHAT;
		}
		switchPanel();
		
	}

	private void switchPanel() {
		if (panelStatus == PanelStatus.USER) {
			roomListPanel.setVisible(false);
			chatPanel.setVisible(false);
			userListPanel.setVisible(true);
		} else if (panelStatus == PanelStatus.ROOM) {
			chatPanel.setVisible(false);
			userListPanel.setVisible(false);
			roomListPanel.setVisible(true);
		} else if (panelStatus == PanelStatus.CHAT) {
			userListPanel.setVisible(false);
			roomListPanel.setVisible(false);
			chatPanel.setVisible(true);
		}

	}
}
