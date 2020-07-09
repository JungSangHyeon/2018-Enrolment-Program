package view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import resisterView.ResisterPan;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	String nowOnPan;
	CardLayout cardLayout;
	ResisterPan resisterPan;
	SignUpPan signUpPan;
	LoginPan loginPan;
	FindPan findPan;
	
	public MainPanel() {
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		
		MainPanActionHandler actionHandler = new MainPanActionHandler();
		KeyHandler keyHandler = new KeyHandler();
		
		loginPan = new LoginPan(actionHandler, keyHandler);
		resisterPan = new ResisterPan(actionHandler);
		signUpPan = new SignUpPan(actionHandler,keyHandler);
		findPan = new FindPan(actionHandler,keyHandler);
		
		this.add(loginPan, "loginPanCard");//처음 넣은게 처음 보여짐.
		this.add(resisterPan, "resisterPanCard");
		this.add(signUpPan, "signUpPanCard");
		this.add(findPan, "findPanCard");
		
		nowOnPan = "loginPanCard";
	}

	public class KeyHandler implements KeyListener {
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (nowOnPan.equals("loginPanCard")) {loginButtonActivate();}
				else if (nowOnPan.equals("findPanCard")) {findOkButtonActivate();}
				else if (nowOnPan.equals("signUpPanCard")) {signUpButtonActivate();}
			}
		}
	}

	public class MainPanActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
	        String AC = arg0.getActionCommand();
			if (AC.equals("login")) {loginButtonActivate();}
			else if (AC.equals("Logout")) {changeCard("loginPanCard");resisterPan.finish();}
			else if (AC.equals("find")) {changeCard("findPanCard");}
			else if (AC.equals("findOk")) {findOkButtonActivate();}
			else if (AC.equals("findCancle")) {changeCard("loginPanCard");}
			else if (AC.equals("signUp")) {changeCard("signUpPanCard");}
			else if (AC.equals("signUpOk")) {signUpButtonActivate();}
			else if (AC.equals("signUpCancle")) {changeCard("loginPanCard");}
		}
	}
	
	private void changeCard(String panelName) {
		cardLayout.show(this, panelName);
		nowOnPan = panelName;
		if(panelName.equals("loginPanCard")) {loginPan.reset();}
		else if(panelName.equals("findPanCard")) {findPan.reset();}
		else if(panelName.equals("signUpPanCard")) {signUpPan.reset();}
	}
	
	public void loginButtonActivate() {
		if (loginPan.validateUser()) {// login 성공
			resisterPan.initiate(loginPan.getNowID());
			cardLayout.show(this, "resisterPanCard");
			nowOnPan = "resisterPanCard";
		}
	}
	
	private void signUpButtonActivate() {
		if(signUpPan.NoEmpty()) {
		if (!signUpPan.idOverlapCheck()) {
			if (signUpPan.validateUser() && signUpPan.pwSamePW()) {
				signUpPan.save();
				changeCard("loginPanCard");
			}
		}else {changeCard("loginPanCard");} // 계정 있음. 계정 찾으쇼.
		}
	}
	
	private void findOkButtonActivate() {if(findPan.noEmpty()&&findPan.findIDPW()) {changeCard("loginPanCard");}}
	public String getNowPan() {return nowOnPan;}
	public void save() {resisterPan.saveAll();}
}

