package myAmazingThings;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class InvisibleBTN extends JButton {
	private static final long serialVersionUID = 1L;
	
	Color basicColor = new Color(255,255,255);
	Color changeColor= new Color(230,240,255);
	Color textColor= new Color(66,133,244);
	
	public InvisibleBTN(String placeHolder, String AC) {
		super(placeHolder);
		MouseHandler mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.setBackground(basicColor);
		this.setForeground(textColor);
		this.setActionCommand(AC);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setFocusable(false);
	}
	
	class MouseHandler implements MouseListener {
		public void mouseClicked(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent e) {MouseEnterAction();}
		public void mouseExited(MouseEvent e) {MouseExitAction();}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
	
	private void MouseEnterAction() {this.setBackground(changeColor);}
	private void MouseExitAction() {this.setBackground(basicColor);}
}
