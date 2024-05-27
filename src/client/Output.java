package client;

import java.io.IOException;
import java.io.ObjectOutputStream;

import Header.ObjectMessage;

public class Output {

	ObjectOutputStream oos;
	ObjectMessage objectMessage;

	public Output(ObjectOutputStream oos, ObjectMessage objectMessage) {
		this.oos = oos;
		this.objectMessage = objectMessage;
	}
	
	public void checkUser() {
		try {
			oos.writeObject(objectMessage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
