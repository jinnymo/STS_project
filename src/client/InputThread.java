package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Header.ObjectMessage;

public class InputThread  implements Runnable{

	private ObjectInputStream ois;
	private Socket socket;
	
	public InputThread(Socket socket) {
		this.socket = socket;
		
	}
	
	@Override
	public void run() {

		try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
			ObjectMessage objectMessage;
			System.out.println(":SAdf");
			while((objectMessage = (ObjectMessage)ois.readObject())!= null) {
				System.out.println("sadfasdf");
				if (objectMessage.getUserInfo() ==null && objectMessage.getStatusCode()!=null) {
					System.out.println(objectMessage.getStatusCode().getCode());
					System.out.println(objectMessage.getStatusCode().getStatusMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
	}

}
