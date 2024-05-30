package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import Header.ObjectMessage;
import Header.UserInfo;

public class InputThread implements Runnable {

	// private ObjectInputStream ois;
	private Socket socket;
	private MainFrame mContext;
	private UserListPanel userListPanel;

	public InputThread(Socket socket, MainFrame mContext) {
		this.socket = socket;
		this.mContext = mContext;
		this.userListPanel = mContext.panelAdapter.getUserListPanel();
	}

	@Override
	public void run() {

		try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

			//System.out.println(":SAdf");
			ObjectMessage objectMessage;
			while ((objectMessage = (ObjectMessage) ois.readObject()) != null) {
				System.out.println("objectMessage : " + objectMessage.toString());
				String name = objectMessage.getName();
				String[] targets = objectMessage.getTarget();
				String message = objectMessage.getMessage();
				
				if (name == null && targets!=null && message == null) {
					mContext.panelAdapter.getUserListPanel().upDateUser(targets);
				}else if(name == null && targets!=null && message != null) {
					mContext.panelAdapter.getRoomListPanel().addData(targets);
					//mContext.panelAdapter.getRoomListPanel().getChatPanels().add(new ChatPanel(mContext));
					mContext.panelAdapter.addCahtPanel(targets,mContext);
				}else if (name != null && targets!=null && message != null) {
					
					System.out.println(objectMessage.toString());
					mContext.panelAdapter.searchChatPanelAdd(name,targets,message);
				}
//				
//				if (objectMessage.getCode1() == 1) {
//					mContext.loginJPanel.setVisible(false);
//					mContext.panelAdapter.setVisible(true);
//					JOptionPane.showMessageDialog(null, objectMessage.getStatusCode().toString());
//				}else if (objectMessage.getCode1() == 2) {
//					//System.out.println(objectMessage.getUserInfo().getId());
//					userListPanel.upDateUser(2,objectMessage.getUserInfo().getId());
//				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
