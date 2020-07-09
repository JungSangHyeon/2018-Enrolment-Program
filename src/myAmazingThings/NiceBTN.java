package myAmazingThings;

import java.awt.Color;

import javax.swing.JButton;

public class NiceBTN extends JButton {
	private static final long serialVersionUID = 1L;
	
	Color textColor = new Color(255,255,255);
	Color basicColor= new Color(66,133,244);
	
	public NiceBTN(String text, String AC) {
		super(text);
		this.setBackground(basicColor);
		this.setForeground(textColor);
		this.setActionCommand(AC);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setFocusable(false);
	}
}
