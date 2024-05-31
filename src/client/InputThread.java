package client;

import java.io.ObjectInputStream;
import java.net.Socket;

import Header.ObjectMessage;

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
			ObjectMessage objectMessage;
			while ((objectMessage = (ObjectMessage) ois.readObject()) != null) {
				String name = objectMessage.getName();
				String[] targets = objectMessage.getTarget();
				String message = objectMessage.getMessage();
				
				if (name == null && targets!=null && message == null) {
					mContext.panelAdapter.getUserListPanel().upDateUser(targets);
					//유저 업데이트 정보 get
				}else if(name == null && targets!=null && message != null) {
					mContext.panelAdapter.getRoomListPanel().addData(targets);
					mContext.panelAdapter.addCahtPanel(targets,mContext);
					//채팅방 데이터 get
				}else if (name != null && targets!=null && message != null) {
					System.out.println(objectMessage.toString());
					mContext.panelAdapter.searchChatPanelAdd(name,targets,message);
					//채팅 메세지 get
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
