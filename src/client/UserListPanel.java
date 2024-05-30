package client;

import java.awt.Color;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class UserListPanel extends JPanel implements ListSelectionListener {

	private JList<String> jList;
	private JScrollPane scrolled;
	private DefaultListModel model;
	private PanelAdapter panelAdapter;
	public UserListPanel(PanelAdapter panelAdapter) {
		this.panelAdapter = panelAdapter;
		initData();
		setInitLayout();
	}

	private void initData() {
		model = new DefaultListModel();
		jList = new JList<String>(model);
		scrolled = new JScrollPane(jList);
		jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	}

	private void setInitLayout() {
		setLayout(null);
		setSize(400, 550);
		setLocation(0, 50);
		setBackground(Color.blue);

		scrolled.setSize(400, 550);
		scrolled.setLocation(0, 0);

		add(scrolled);

	}

	public void upDateUser(String[] users) {
		model.removeAllElements();
		for (int i = 0; i < users.length; i++) {
			model.addElement(users[i]);
		}
	}

	public String[] returnSelectUser() {
		List<String> selectlist = jList.getSelectedValuesList();
		
		String[] selectUser = new String[selectlist.size()];

		for (int i = 0; i < selectlist.size(); i++) {
			selectUser[i] = selectlist.get(i);
		}
		
		
		//panelAdapter.getRoomListPanel().addData(selectUser);
		return selectUser;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			System.out.println(e.getSource().toString());
			List<String> ls = jList.getSelectedValuesList();
			for (String value : ls) {
				System.out.println(value);
			}
		}
		System.out.println(e.getSource().toString());
		System.out.println(jList.getSelectedValue());

	}
}
