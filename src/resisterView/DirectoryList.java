package resisterView;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import control.CDirectory;
import myAmazingThings.MovingPanel;
import valueObject.VCDirectory;

public class DirectoryList extends MovingPanel{
	private static final long serialVersionUID = 1L;

	Vector<String> ListData;
	Vector<VCDirectory> vCDirectories;
	CDirectory cDirectory;
	JList<String> list;
	JScrollPane scPane;
	
	public DirectoryList(ListSelectionListener listSelectionListener, String borderTitle, String toolTip) {
		super(100,100,borderTitle);
		
		this.ListData = new Vector<String>();
		cDirectory = new CDirectory();
		
		ComponentHandler componentHandler = new ComponentHandler();
		this.addComponentListener(componentHandler);
		
		list = new JList<String>();
		list.setToolTipText(toolTip);
		list.setListData(this.ListData);
		list.setBounds(10,20,f_width-20, f_height-30);
		list.addListSelectionListener(listSelectionListener);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 멀티 셀렉션 막음. 
		
		scPane = new JScrollPane(list);
		scPane.setBounds(10,20,f_width-20, f_height-30);
		this.add(scPane);
	}

	public void refresh(String fileName) {
		vCDirectories = cDirectory.getData(fileName);
		this.ListData.clear();
		for (VCDirectory vc : vCDirectories) {this.ListData.add(vc.getText());}
		list.setSelectedIndex(0);
		list.updateUI();
	}
	
	public String getSelectedFile() {return this.vCDirectories.get(list.getSelectedIndex()).getFileName();}
	
	class ComponentHandler implements ComponentListener{
		public void componentResized(ComponentEvent e) {
			scPane.setBounds(10,20,f_width-20, f_height-30);
			list.setBounds(10,20,f_width-20, f_height-30);
		}
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
	}
}
