package view;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.CLogin;
import myAmazingThings.AwesomePassWordField;
import myAmazingThings.AwesomeTextField;
import myAmazingThings.InvisibleBTN;
import myAmazingThings.NiceBTN;
import valueObject.VCSignUpInfo;
import view.MainPanel.KeyHandler;
import view.MainPanel.MainPanActionHandler;

public class LoginPan extends JPanel {
	private static final long serialVersionUID = 1L;

	CLogin cLogin;	
	String nowID;
	AwesomeTextField ATF;
	AwesomePassWordField APWF;
	InvisibleBTN  findBtn, signUpBtn;
	NiceBTN  niceBTN;
	
	public LoginPan(MainPanActionHandler actionHandler, KeyHandler keyHandler) {
		this.setLayout(null);

		ATF = new AwesomeTextField("ID"," ID를 입력 하세요",180,keyHandler);
		ATF.setLocation(375,350);
		this.add(ATF);
		
		APWF = new AwesomePassWordField("Pass Word"," PW를 입력 하세요",180,keyHandler);
		APWF.setLocation(565,350);
		this.add(APWF);
		
		findBtn = new InvisibleBTN("ID나 PW를 잊으셨나요?","find");
		findBtn.setBounds(360,430,170,20);
		findBtn.addActionListener(actionHandler);
	    this.add(findBtn);
	    
		signUpBtn = new InvisibleBTN("계정 만들기","signUp");
		signUpBtn.setBounds(357,570, 110,35);
		signUpBtn.addActionListener(actionHandler);
	    this.add(signUpBtn);
	    
	    niceBTN = new NiceBTN("Login","login");
	    niceBTN.setBounds(655,560, 90,35);
	    niceBTN.addActionListener(actionHandler);
	    this.add(niceBTN);
	}
	
	public void reset() {
		ATF.requestFocus();
		APWF.reset();
		ATF.reset();
	}
	
	public String getNowID() {return nowID;}
	
	public boolean validateUser() {
		boolean result = false;
		try {
			this.cLogin = new CLogin();
			VCSignUpInfo vCSignUpInfo = cLogin.validateUser(ATF.getText(), APWF.getText());
			result = vCSignUpInfo.getLoginSuccess();
			nowID = vCSignUpInfo.getnowID();
		} catch (Exception e) {//null뜨는경우는 로그인 안되는 경우임.
			if (!APWF.getText().equals("")&&!ATF.getText().equals("")&&!result) {
				JOptionPane.showMessageDialog(this, "로그인 실패.", "에러", JOptionPane.ERROR_MESSAGE);
				APWF.errorControl(3);
				ATF.errorControl(3);
			}
			if (APWF.getText().equals("")) {APWF.errorControl(2);}
			if (ATF.getText().equals("")) {ATF.errorControl(2);}
			if (ATF.getText().equals("")) {ATF.requestFocus();}
			else {APWF.requestFocus();}
		}
		return result;
	}
	
	public void paintComponent(Graphics g) {
		try {
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(ImageIO.read(new File("LoginBG.png")), 345, 70, null);
			g.drawImage(ImageIO.read(new File("명지대마크.png")), 425, 130, null);
	    } catch (IOException e) {e.printStackTrace();}
	 }
}
