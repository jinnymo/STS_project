package client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomListPanel extends JPanel {

	private JList<String> jList;
	private JScrollPane scrolled;
	private DefaultListModel model;
	private PanelAdapter panelAdapter;
	private List<String[]> targets;
	private List<ChatPanel> chatPanels;
	private String[] tempArray;

	public RoomListPanel(PanelAdapter panelAdapter) {
		initData();
		setInitLayout();
		this.panelAdapter = panelAdapter;
	}

	private void initData() {
		targets = new ArrayList<>();
		chatPanels = new ArrayList<>();
		model = new DefaultListModel();
		jList = new JList<String>(model);
		scrolled = new JScrollPane(jList);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void setInitLayout() {
		setLayout(null);
		setSize(400, 550);
		setLocation(0, 50);
		setBackground(Color.black);
		scrolled.setSize(400, 550);
		scrolled.setLocation(0, 0);
		add(scrolled);
	}
	public String returnJListText(int i) {
		return model.get(i).toString();
	}
	public ChatPanel returnChatPanel() {
		int i = jList.getSelectedIndex();
		System.out.println(chatPanels.get(i).toString());
		return chatPanels.get(i);
	}
	//TODO 중복되는 채팅방은 못만들게 해야 하는데 .......나중에.....
	public void addData(String[] selectUser ) {
		targets.add(selectUser);
		model.addElement(Arrays.toString(selectUser));
	}
}
