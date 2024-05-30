package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Header.ObjectMessage;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PanelAdapter extends JPanel implements ActionListener {

	private JButton showUser;
	private JButton showRoom;
	private JButton back;
	private JButton createRoom;
	private JButton connectRoom;
	private JPanel topPanel;
	private ImageIcon arrow;
	private ImageIcon arrowclick;
	private UserListPanel userListPanel;
	private RoomListPanel roomListPanel;
	private ChatPanel chatPanel;
	private JTextArea nameTextArea;

	private MainFrame mContext;
	//private String[] target;

	PanelStatus panelStatus;

	public PanelAdapter(MainFrame mContext) {
		this.mContext = mContext;
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
		userListPanel = new UserListPanel(this);
		roomListPanel = new RoomListPanel(this);
		//chatPanel = new ChatPanel();
		arrow = new ImageIcon("img/arrow.png");
		arrowclick = new ImageIcon("img/arrow_red.png");
		topPanel = new JPanel();
		showUser = new JButton("유저목록");
		showRoom = new JButton("방 목록");
		createRoom = new JButton("채팅방 생성");
		connectRoom = new JButton("채팅방 접속");
		back = new JButton(arrow);
		
		nameTextArea = new JTextArea("");

		topPanel.setLayout(null);
		topPanel.setSize(400, 50);
		topPanel.setLocation(0, 0);

		back.setSize(50, 50);
		showUser.setSize(200, 100);
		showRoom.setSize(200, 100);
		createRoom.setSize(100,50);
		connectRoom.setSize(100,50);
		nameTextArea.setSize(150,50);
		
		back.setLocation(0, 0);
		showUser.setLocation(0, 600);
		showRoom.setLocation(200, 600);
		createRoom.setLocation(300, 0);
		connectRoom.setLocation(300, 0);
		nameTextArea.setLocation(100,0);
		back.setRolloverIcon(arrowclick);

	}

	private void setInitLayout() {
		// TODO Auto-generated method stub
		setLayout(null);
		add(showUser);
		add(showRoom);
		add(topPanel);
		topPanel.add(back);
		topPanel.add(createRoom,0);
		topPanel.add(connectRoom);
		topPanel.add(nameTextArea);
		add(roomListPanel);
		//add(chatPanel);
		add(userListPanel,0);
		userListPanel.setVisible(true);
		
	}

	private void addEventListener() {
		showUser.addActionListener(this);
		showRoom.addActionListener(this);
		back.addActionListener(this);
		createRoom.addActionListener(this);
		connectRoom.addActionListener(this);
		
	}
	public void addCahtPanel(String[] targets , MainFrame mContext) {
		ChatPanel chatP = new ChatPanel(targets,mContext);
		roomListPanel.getChatPanels().add(chatP);
		add(chatP);
		chatP.setVisible(false);
	}
	public void searchChatPanelAdd(String name, String[] targets, String message) {
		List<ChatPanel> chatPanels = roomListPanel.getChatPanels();
		String sTarget = Arrays.toString(targets);
		for (ChatPanel chatPanel : chatPanels) {
			String sChatTarget = Arrays.toString(targets);
			if (sTarget.equals(sChatTarget)) {
				chatPanel.getModel().addElement(name+"님 : " + message);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == showUser) {
			panelStatus = PanelStatus.USER;
		} else if (e.getSource() == showRoom) {
			panelStatus = PanelStatus.ROOM;
		} else if (e.getSource() == back) {
			panelStatus = PanelStatus.ROOM;
		}else if (e.getSource() == createRoom) {
			panelStatus = PanelStatus.ROOM;
			String[] tgs = userListPanel.returnSelectUser();
			ObjectMessage om = new ObjectMessage(nameTextArea.getText().toString()
					,tgs,"");
			mContext.output.checkUser(om);
			
			
		}else if (e.getSource() == connectRoom) {
			if (chatPanel != null) {
				chatPanel.setVisible(false);
			}
			
			chatPanel = roomListPanel.returnChatPanel();
			//chatPanel.setVisible(true);
			panelStatus = PanelStatus.CHAT;
			
			//to-do
		}
		switchPanel();
		
	}

	private void switchPanel() {
		if (panelStatus == PanelStatus.USER) {
			roomListPanel.setVisible(false);
			//chatPanel.setVisible(false);
			userListPanel.setVisible(true);
			createRoom.setVisible(true);
			connectRoom.setVisible(false);
			back.setVisible(false);
		} else if (panelStatus == PanelStatus.ROOM) {
			//chatPanel.setVisible(false);
			userListPanel.setVisible(false);
			roomListPanel.setVisible(true);
			createRoom.setVisible(false);
			connectRoom.setVisible(true);
			back.setVisible(false);
		} else if (panelStatus == PanelStatus.CHAT) {
			userListPanel.setVisible(false);
			roomListPanel.setVisible(false);
			chatPanel.setVisible(true);
			createRoom.setVisible(false);
			connectRoom.setVisible(false);
			back.setVisible(true);
		}

	}
}
