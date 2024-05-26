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

public class LoginJPanel extends JPanel {

	private ImageIcon logoImg;
	private ImageIcon serverImg;
	private ImageIcon serverImgClick;
	private JLabel logoLabel;
	private JLabel textLabel;
	private JTextField nameField;
	private JButton submitBtn;

	private InetAddress local;
	
	private String ip;

	public LoginJPanel() {
		logoImg = new ImageIcon("img/chat_logo.png");
		serverImg = new ImageIcon("img/enter_server.png");
		serverImgClick = new ImageIcon("img/enter_server_2.png");
		logoLabel = new JLabel(logoImg);
		textLabel = new JLabel(
				"<html>" + " &nbsp; &nbsp; &nbsp; 이름을 신중하게 작성해주세요." + "<br>" + "서버 초기화 전까지 수정이 불가합니다." + "</html>");
		nameField = new JTextField("이름입력");
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

		nameField.setSize(150, 50);
		nameField.setLocation(125, 430);
		nameField.setHorizontalAlignment(JLabel.CENTER);

		submitBtn.setSize(150, 70);
		submitBtn.setLocation(125, 500);
		submitBtn.setHorizontalAlignment(JLabel.CENTER);
		submitBtn.setRolloverIcon(serverImgClick);

	}

	private void setInitLayout() {
		setLayout(null);
		add(logoLabel);
		add(textLabel);
		add(nameField);
		add(submitBtn);
	}

	private void addEventListner() {

		submitBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TO-DO 서버접속 버튼 누른 후 액션 서버로 현재ip및 이름 푸시
				System.out.println(local.getHostAddress());
			}
		});

	}
	
	private String getMyIp() {

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
						// 6. IP 주소가 "172."로 시작하는지 확인합니다.
						if (ipAddress.startsWith("172.")) {
							// 7. 조건에 맞는 IP 주소를 출력합니다.
							System.out.println("IP Address: " + ipAddress);
							return ipAddress;
						}else {
							System.out.println("ip 서칭 오류 발생");
						}
					}
				}
				
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
