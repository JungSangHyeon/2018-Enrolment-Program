package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import constants.Constants.MainFrameEnum;

public class MainFrame extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	MainPanel mainPanel;
	int f_width, f_height;
	JPanel basicPanel;
	Thread th;

	public MainFrame() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrameEnum.mainMark.getString()));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);

		ComponentHandler componentHandler = new ComponentHandler();
		this.addComponentListener(componentHandler);

		WindowHandler windowHandler = new WindowHandler();
		this.addWindowListener(windowHandler);

		this.getContentPane().setBackground(new Color(MainFrameEnum.contentPanColor.getInt(),
				MainFrameEnum.contentPanColor.getInt(), MainFrameEnum.contentPanColor.getInt()));
		this.getContentPane().setLayout(null);

		mainPanel = new MainPanel();
		this.add(mainPanel);

		th = new Thread(this);
		th.start();
	}

	class ComponentHandler implements ComponentListener {
		public void componentResized(ComponentEvent e) {
			refresh();
		}

		public void componentHidden(ComponentEvent e) {
		}

		public void componentMoved(ComponentEvent e) {
		}

		public void componentShown(ComponentEvent e) {
		}
	}

	public void refresh() {
		f_width = this.getWidth();
		f_height = this.getHeight();
		mainPanel.setBounds(f_width / 2 - MainFrameEnum.width.getInt() / 2 - 8,
				f_height / 2 - MainFrameEnum.height.getInt() / 2 - 35, MainFrameEnum.width.getInt(),
				MainFrameEnum.height.getInt());
	}

	public void run() {
		while (true) {
			setNewMinimusSize(mainPanel.getNowPan());
			if (this.getSize().getWidth() < this.getMinimumSize().getWidth()) {
				this.setSize((int) this.getMinimumSize().getWidth(), this.getHeight());
			}
			if (this.getSize().getHeight() < this.getMinimumSize().getHeight()) {
				this.setSize(this.getWidth(), (int) this.getMinimumSize().getHeight());
			}
		}
	}

	private void setNewMinimusSize(String pan) {
		if (!pan.equals("resisterPanCard")) {
			refresh();
		}
		if (pan.equals("loginPanCard") || pan.equals("findPanCard")) {
			this.setMinimumSize(
					new Dimension(MainFrameEnum.logfindminiX.getInt(), MainFrameEnum.logfindminiY.getInt()));
		} else if (pan.equals("signUpPanCard")) {
			this.setMinimumSize(new Dimension(MainFrameEnum.singUpminiX.getInt(), MainFrameEnum.singUpminiY.getInt()));
		} else if (pan.equals("resisterPanCard")) {
			mainPanel.setBounds(0, 0, MainFrameEnum.maxWidth.getInt(), MainFrameEnum.maxHeight.getInt());
			this.setExtendedState(MAXIMIZED_BOTH);
		}
	}

	class WindowHandler implements WindowListener {// À¯¾ð
		public void windowClosing(WindowEvent e) {
			mainPanel.save();
		}

		public void windowDeactivated(WindowEvent e) {
		}

		public void windowDeiconified(WindowEvent e) {
		}

		public void windowActivated(WindowEvent e) {
		}

		public void windowIconified(WindowEvent e) {
		}

		public void windowOpened(WindowEvent e) {
		}

		public void windowClosed(WindowEvent e) {
		}
	}
}
