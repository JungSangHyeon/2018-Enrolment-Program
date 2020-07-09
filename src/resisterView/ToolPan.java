package resisterView;

import java.awt.Color;

import javax.swing.JPanel;

import myAmazingThings.MovingPanel;
import myAmazingThings.NiceBTN;
import resisterView.ResisterPan.ResisterPanActionHandler;
import view.MainPanel.MainPanActionHandler;

public class ToolPan extends MovingPanel{
	private static final long serialVersionUID = 1L;

	NiceBTN logOutBTN;
	NiceBTN timeTableBTN;
	
	public ToolPan(MainPanActionHandler actionHandler, ResisterPanActionHandler resisterActionHandler) {
		super(1,1,"µµ±¸");
		
		DoNotResize=true;
	
		JPanel innerPan = new JPanel();
		innerPan.setBackground(Color.white);
		
		logOutBTN = new NiceBTN("Logout","Logout");
		logOutBTN.addActionListener(actionHandler);
		innerPan.add(logOutBTN);
		
		timeTableBTN = new NiceBTN("timeTable","time");
		timeTableBTN.addActionListener(resisterActionHandler);
		innerPan.add(timeTableBTN);
		
		this.add(innerPan);
	}
}
