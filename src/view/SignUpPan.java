package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.CIDOverlapCheck;
import control.CSaveSignUpInfo;
import control.CValidateUser;
import myAmazingThings.AwesomePassWordField;
import myAmazingThings.AwesomeTextField;
import myAmazingThings.NiceBTN;
import valueObject.VCSignUpInfo;
import view.MainPanel.MainPanActionHandler;
import view.MainPanel.KeyHandler;

public class SignUpPan extends JPanel {
	private static final long serialVersionUID = 1L;

	AwesomeTextField studentIDTxt, nameTxt, idTxt;
	AwesomePassWordField pwTxt, pwCheckTxt;
	CValidateUser cValidateUser;
	CIDOverlapCheck cIDOverlapCheck;
	
	public SignUpPan(MainPanActionHandler actionHandler, KeyHandler keyHandler) {
		this.setLayout(null);
		
		cIDOverlapCheck = new CIDOverlapCheck();
		cValidateUser = new CValidateUser();
		
		studentIDTxt = new AwesomeTextField("Student ID","�й��� �Է� �ϼ���", 180,keyHandler);
		studentIDTxt.setLocation(215,220);
		this.add(studentIDTxt);

		nameTxt = new AwesomeTextField("Name","�̸��� �Է� �ϼ���", 180,keyHandler);
		nameTxt.setLocation(405,220);
		this.add(nameTxt);

		idTxt = new AwesomeTextField("ID","ID�� �Է� �ϼ���", 370,keyHandler);
		idTxt.setLocation(215,320);
		this.add(idTxt);

		pwTxt = new AwesomePassWordField("Pass Word","PW�� �Է� �ϼ���", 180,keyHandler);
		pwTxt.setLocation(215,420);
		this.add(pwTxt);

		pwCheckTxt = new AwesomePassWordField("Check","Check�� �Է� �ϼ���", 180,keyHandler);
		pwCheckTxt.setLocation(405,420);
		this.add(pwCheckTxt);
		
		NiceBTN okBtn = new NiceBTN("Ok","signUpOk");
		okBtn.addActionListener(actionHandler);
		okBtn.setBounds(395,550, 90,35);
		this.add(okBtn);

		NiceBTN cancleBtn = new NiceBTN("Cancle","signUpCancle");
		cancleBtn.addActionListener(actionHandler);
		cancleBtn.setBounds(495,550, 90,35);
		this.add(cancleBtn);
	}
	
	public boolean idOverlapCheck() {
		boolean reasult = cIDOverlapCheck.idOverlapCheck(studentIDTxt.getText());
		if (reasult) {JOptionPane.showMessageDialog(this, "�� �й��� �̹� ȸ������ �Ǿ� �ֽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);}
		return reasult;
	}
	
	public boolean validateUser() {
		boolean reasult = cValidateUser.validateUser(studentIDTxt.getText(), nameTxt.getText());
		if(!reasult){
			JOptionPane.showMessageDialog(this, "�Էµ� �л� ������ ��ϵǾ� ���� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			nameTxt.errorControl(3);
			studentIDTxt.errorControl(3); 
		}
		return reasult;
	}

	public boolean pwSamePW() {
		boolean reasult = true;
		if (!pwTxt.getText().equals(pwCheckTxt.getText())) {
			reasult = false;
			JOptionPane.showMessageDialog(this, "PW�� Check�� ��ġ���� �ʽ��ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			pwCheckTxt.errorControl(3);
			pwTxt.errorControl(3);
		}
		return reasult;
	}

	public boolean NoEmpty() {
		boolean reasult = true;
		if(pwCheckTxt.getText().equals("")) {pwCheckTxt.errorControl(2);reasult=false;}
		if(pwTxt.getText().equals("")) {pwTxt.errorControl(2);reasult=false;}
		if(idTxt.getText().equals("")) {idTxt.errorControl(2);reasult=false;}
		if(nameTxt.getText().equals("")) {nameTxt.errorControl(2);reasult=false;}
		if(studentIDTxt.getText().equals("")) {studentIDTxt.errorControl(2);reasult=false;}
		
		if (studentIDTxt.getText().equals("")) {studentIDTxt.requestFocus();}//�ִϸ��̼� �̻ڰ�, �� �ϱ����� �и��� �ʿ��ϴ�.
		else if (nameTxt.getText().equals("")) {nameTxt.requestFocus();}
		else if (idTxt.getText().equals("")) {idTxt.requestFocus();}
		else if (pwTxt.getText().equals("")) {pwTxt.requestFocus();}
		else if (pwCheckTxt.getText().equals("")) {pwCheckTxt.requestFocus();}
		return reasult;
	}
	
	public void save() {
		VCSignUpInfo vCSignUpInfo = new VCSignUpInfo();
		vCSignUpInfo.setnowID(studentIDTxt.getText());
		vCSignUpInfo.setID(idTxt.getText());
		vCSignUpInfo.setPW(pwTxt.getText());
		CSaveSignUpInfo cSaveSignUpInfo = new CSaveSignUpInfo();
		cSaveSignUpInfo.saveSignUpInfo(vCSignUpInfo);
		JOptionPane.showMessageDialog(this, "ȸ������ �Ǿ����ϴ�.", "ȯ���մϴ�!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void reset() {
		studentIDTxt.requestFocus();
		studentIDTxt.reset();
		nameTxt.reset();
		idTxt.reset();
		pwTxt.reset();
		pwCheckTxt.reset();
	}
	
	public void paintComponent(Graphics g) {
		try {
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(ImageIO.read(new File("WideBG.png")), 175, 70, null);
			g.drawImage(ImageIO.read(new File("�����븶ũ2.png")), 625, 200, null);
			g.setColor(new Color(180, 209, 248));
			g.setFont(new Font(null, Font.PLAIN, 34));
			g.drawString("Create account", 213, 145);
		  } catch (IOException e) {e.printStackTrace();}
     }
}
