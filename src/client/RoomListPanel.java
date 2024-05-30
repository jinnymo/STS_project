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
	int i = 0;

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
	public ChatPanel returnChatPanel() {
		int i = jList.getSelectedIndex();
		return chatPanels.get(i);
	}
	public void showChatPanel() {
		
	}
	
	//TODO 중복되는 채팅방은 못만들게 해야 하는데 .......
	public void addData(String[] selectUser ) {

		
		// panelAdapter.setTarget(selectUser);
		//System.out.println(selectlist.toString());
		//System.out.println(Arrays.toString(selectUser));
		//[아이디 입력, asfdsadfas, asdfasdsdfasdfas] 이런 형식 출
		targets.add(selectUser);
		model.addElement(Arrays.toString(selectUser));
		//System.out.println(Arrays.deepToString(targets.get(0)));

	}
}
