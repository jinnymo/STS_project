package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import Header.ObjectMessage;
import Header.UserInfo;

public class InputThread  implements Runnable{

	private ObjectInputStream ois;
	private Socket socket;
	private MainFrame mContext;
	
	public InputThread(Socket socket, MainFrame mContext) {
		this.socket = socket;
		this.mContext = mContext;
	}
	
	@Override
	public void run() {

		try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
			
			System.out.println(":SAdf");
			
			while(true) {
				
				ObjectMessage objectMessage = (ObjectMessage)ois.readObject();
				System.out.println("objectMessage : " );
				
				if (objectMessage.getCode1() == 1) {
					mContext.loginJPanel.setVisible(false);
					mContext.panelAdapter.setVisible(true);
					JOptionPane.showMessageDialog(null, objectMessage.getStatusCode().toString());
				}else if (objectMessage.getCode1() == 2) {
					System.out.println(objectMessage.getUserInfo().getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
	}

}
