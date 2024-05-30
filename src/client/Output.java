package client;

import java.io.IOException;
import java.io.ObjectOutputStream;

import Header.ObjectMessage;

public class Output {

	ObjectOutputStream oos;
	

	public Output(ObjectOutputStream oos) {
		this.oos = oos;

	}
	
	public void checkUser(ObjectMessage objectMessage) {
		try {
			oos.writeObject(objectMessage);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
