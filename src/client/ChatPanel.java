package client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import Header.ObjectMessage;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ChatPanel extends JPanel implements ActionListener{

	private JList<String> jList;
	private JScrollPane scrolled;
	private DefaultListModel model;
	private PanelAdapter panelAdapter;
	private JPanel bottomPanel;
	private JTextField text;
	

	private JButton sendBtn;
	private String[] targets;
	private MainFrame mContext;

	public ChatPanel(String[] targets, MainFrame mContext) {
		this.targets = targets;
		this.mContext = mContext;
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		model = new DefaultListModel();
		jList = new JList<String>(model);
		scrolled = new JScrollPane(jList);
		// jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		text = new JTextField("",20);
		sendBtn = new JButton("전송");
		bottomPanel = new JPanel();
	}

	private void setInitLayout() {
		setLayout(null);
		setSize(400, 550);
		setLocation(0, 50);
		setBackground(Color.green);

		scrolled.setSize(400, 500);
		scrolled.setLocation(0, 0);

		bottomPanel.setSize(400, 50);
		bottomPanel.setLocation(0, 500);

		text.setSize(250, 90);
		text.setLocation(0, 0);

		sendBtn.setSize(70, 50);
		sendBtn.setLocation(330, 0);
		add(scrolled);
		add(bottomPanel);
		bottomPanel.add(text);
		bottomPanel.setVisible(true);
		bottomPanel.add(sendBtn);

	}
	private void addEventListener() {
		sendBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = mContext.getPanelAdapter().getNameTextArea().getText();
		String message = text.getText();
		if (e.getSource() == sendBtn) {
			
			ObjectMessage om = new ObjectMessage(name,targets,message);
			mContext.output.checkUser(om);
		}
		
	}
	
	
	
}
