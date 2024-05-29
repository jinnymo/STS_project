package client;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Header.ObjectMessage;
import Header.UserInfo;

public class LoginJPanel extends JPanel {

	
	private UserInfo userInfo;
	private MainFrame mContext;
	
	private ImageIcon logoImg;
	private ImageIcon serverImg;
	private ImageIcon serverImgClick;
	private JLabel logoLabel;
	private JLabel textLabel;
	private JTextField idField;
	private JTextField pwdField;
	private JButton submitBtn;

	private InetAddress local;

	private String ip;

	

	public LoginJPanel(MainFrame mContext) {
		this.mContext = mContext;
		logoImg = new ImageIcon("img/chat_logo.png");
		serverImg = new ImageIcon("img/enter_server.png");
		serverImgClick = new ImageIcon("img/enter_server_2.png");
		logoLabel = new JLabel(logoImg);
		textLabel = new JLabel(
				"<html>" + " &nbsp; &nbsp; &nbsp; 아이디를 신중하게 작성해주세요." +
		"<br>" + "서버 초기화 전까지 수정이 불가합니다." + "</html>");
		idField = new JTextField("아이디 입력");
		pwdField = new JTextField("비밀번호 입력");
		submitBtn = new JButton(serverImg);

		ip = getMyIp();

		initData();
		setInitLayout();
		addEventListner();
	}

	private void initData() {
		setSize(400, 700);
		setLocation(0, 0);
		setBackground(Color.yellow);
		setVisible(true);

		logoLabel.setSize(150, 120);
		logoLabel.setLocation(125, 150);

		textLabel.setSize(300, 300);
		textLabel.setLocation(50, 250);
		textLabel.setHorizontalAlignment(JLabel.CENTER);
		// textLabel.setHorizontalTextPosition(JLabel.CENTER);

		idField.setSize(150, 50);
		idField.setLocation(125, 430);
		idField.setHorizontalAlignment(JLabel.CENTER);
		
		pwdField.setSize(150,50);
		pwdField.setLocation(125,500);
		pwdField.setHorizontalAlignment(JLabel.CENTER);

		submitBtn.setSize(150, 70);
		submitBtn.setLocation(125, 580);
		submitBtn.setHorizontalAlignment(JLabel.CENTER);
		submitBtn.setRolloverIcon(serverImgClick);

	}

	private void setInitLayout() {
		setLayout(null);
		add(logoLabel);
		add(textLabel);
		add(idField);
		add(pwdField);
		add(submitBtn);
	}

	private void addEventListner() {

		submitBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TO-DO 서버접속 버튼 누른 후 액션 서버로 현재ip및 이름 푸시
				mContext.connectServer();
				System.out.println("데이터 전송");
				userInfo = new UserInfo(idField.getText(),pwdField.getText());
				ObjectMessage om = new ObjectMessage(1,userInfo);
				mContext.output.checkUser(om);

			}
		});

	}

	private String getMyIp() {//굳이 사용안해도 될거같음;;;;

		try {
			// 1. 시스템의 모든 네트워크 인터페이스를 나열합니다.
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			// 2. 네트워크 인터페이스 목록을 순회합니다.
			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				// 3. 각 네트워크 인터페이스에 할당된 IP 주소를 나열합니다.
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
				// 4. IP 주소 목록을 순회합니다.
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					// 5. inetAddress가 IPv4 주소이고 루프백 주소가 아닌지 확인합니다.
					if (inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress()) {
						String ipAddress = inetAddress.getHostAddress();
						System.out.println("IP Address: " + ipAddress);
						// 7. 조건에 맞는 IP 주소를 출력합니다.
						return ipAddress;
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;

	}
}
