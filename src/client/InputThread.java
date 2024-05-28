package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import Header.ObjectMessage;

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
			ObjectMessage objectMessage;
			System.out.println(":SAdf");
			while((objectMessage = (ObjectMessage)ois.readObject())!= null) {
				System.out.println("objectMessage : " + objectMessage);
				
				if (objectMessage.getCode1() == 1 && objectMessage.getStatusCode().getCode() == 601) {
					mContext.loginJPanel.setVisible(false);
					mContext.panelAdapter.setVisible(true);
					JOptionPane.showMessageDialog(null, objectMessage.getStatusCode().toString());
				}else if (objectMessage.getCode1() == 1 &&objectMessage.getStatusCode().getCode() == 600) {
					mContext.loginJPanel.setVisible(false);
					mContext.panelAdapter.setVisible(true);
					JOptionPane.showMessageDialog(null, objectMessage.getStatusCode().toString());
				}else if (objectMessage.getCode1() == 2) {
					System.out.println("ad");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
	}

}
