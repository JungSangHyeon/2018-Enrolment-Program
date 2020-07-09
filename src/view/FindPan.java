package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.CSignUpInfo;
import control.CValidateUser;
import myAmazingThings.AwesomeTextField;
import myAmazingThings.NiceBTN;
import valueObject.VCSignUpInfo;
import view.MainPanel.MainPanActionHandler;
import view.MainPanel.KeyHandler;

public class FindPan extends JPanel {
	private static final long serialVersionUID = 1L;

	AwesomeTextField studentIDTxt, nameTxt;
	CValidateUser cValidateUser;

	public FindPan(MainPanActionHandler actionHandler, KeyHandler keyHandler) {
		this.setLayout(null);
		cValidateUser = new CValidateUser();
		
		studentIDTxt = new AwesomeTextField("Student ID"," 학번을 입력 하세요",370,keyHandler);
		studentIDTxt.setLocation(375,350);
		this.add(studentIDTxt);
		
		nameTxt = new AwesomeTextField("Name"," Name을 입력 하세요",370,keyHandler);
		nameTxt.setLocation(375,450);
		this.add(nameTxt);

		NiceBTN okBtn = new NiceBTN("Ok","findOk");
		okBtn.setBounds(555, 560, 90, 35);
		okBtn.addActionListener(actionHandler);
		this.add(okBtn);

		NiceBTN cancleBtn = new NiceBTN("Cancle","findCancle");
		cancleBtn.setBounds(655, 560, 90, 35);
		cancleBtn.addActionListener(actionHandler);
		this.add(cancleBtn);
	}
	 
	public void reset() {
		studentIDTxt.requestFocus();
		studentIDTxt.reset();
		nameTxt.reset();
	}
	
	public boolean noEmpty() {
		boolean reasult = true;
		if(nameTxt.getText().equals("")) {nameTxt.errorControl(2);reasult=false;}
		if(studentIDTxt.getText().equals("")) {studentIDTxt.errorControl(2);reasult=false;}
		return reasult;
	}
	
	public boolean findIDPW() {
		boolean reasult = false;
		if(cValidateUser.validateUser(studentIDTxt.getText(),nameTxt.getText())) {
			try {
			 CSignUpInfo cSignUpInfo= new CSignUpInfo();
			 VCSignUpInfo vCSignUpInfo = cSignUpInfo.getSignUpInfo(studentIDTxt.getText());
			 JOptionPane.showMessageDialog(this, "해당 정보는 다음과 같습니다."+ "\r\n"+"ID : "+vCSignUpInfo.getID()+ "\r\n"+"PW : "+vCSignUpInfo.getPW(), "승인", JOptionPane.INFORMATION_MESSAGE);
		     }catch(Exception e) {JOptionPane.showMessageDialog(this, "가입된 계정이 없습니다." + "\r\n"+"먼저 회원가입 하세요.", "에러", JOptionPane.ERROR_MESSAGE);}
			reasult = true;
		}else {JOptionPane.showMessageDialog(this, "입력된 학생 정보는 등록되어 있지 않습니다.", "에러", JOptionPane.ERROR_MESSAGE);}
		return reasult;
	}

	public void paintComponent(Graphics g) {
		try {
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(ImageIO.read(new File("LoginBG.png")), 345, 70, null);
			g.drawImage(ImageIO.read(new File("명지대마크.png")), 425, 130, null);
			g.setColor(new Color(180, 209, 248));
			g.setFont(new Font(null, Font.PLAIN, 34));
			g.drawString("Find", 370, 130);
		} catch (IOException e) {e.printStackTrace();}
	}
}
