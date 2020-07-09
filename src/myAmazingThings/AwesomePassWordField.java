package myAmazingThings;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

import javax.swing.JPasswordField;

public class AwesomePassWordField extends SuperField implements Runnable {
	private static final long serialVersionUID = 1L;

	Thread th = new Thread(this);
	JPasswordField textField;
	
	public AwesomePassWordField(String placeHolderValue,String errorMessageValue, int barWidthValue,  KeyListener keyListener) {
		super(placeHolderValue,errorMessageValue,barWidthValue);
		FocusHandler focusHandler = new FocusHandler();

		textField = new JPasswordField();
		textField.setBounds(0, focusedHolderSize + interval, barWidth, textAreaTextSize+interval);
		textField.addFocusListener(focusHandler);
		textField.addKeyListener(keyListener);
		textField.setFont(new Font(null, Font.PLAIN, focusedHolderSize));
		textField.setOpaque(false);
		textField.setBorder(null);
		this.add(textField);
		repaint();
	}
	
	public void paintComponent(Graphics g) {Animation(g);}
	public void errorControl(int num) {error(num);}
	public String getText() {return makePassword(textField);}
	public void requestFocus() {textField.requestFocus();}
	public void addKeyListener(KeyListener keyListener) {textField.addKeyListener(keyListener);}
	
	public void reset() {
		errorControl(1);
		textField.setText(null);
		status = 5;
		repaint();
		textField.setOpaque(false);
	}
	
	public void run() {
		textField.setOpaque(false);
		while(cnt<time) {
			cnt++;
			repaint();
			try {Thread.sleep(10);}catch (Exception e) {}
		}
		cnt=0;
		textField.setOpaque(true);
	}
	
	class FocusHandler implements FocusListener {
		public void focusGained(FocusEvent arg0) {focusEventControl(1);}
		public void focusLost(FocusEvent arg0) {focusEventControl(2);}
	}

	public void focusEventControl(int i) {
		if (makePassword(textField).equals("")) {
			if(i==1) {status = 1;}
			else {status=2;}	
		} else {
			if(i==1) {status = 4;}
			else {status=3;}	
		}
		th = new Thread(this);
		th.start();
	}
}
