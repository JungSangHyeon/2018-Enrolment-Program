package resisterView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import control.CGangJwaInfo;
import control.CSIDPersonalInfo;
import valueObject.VCGangJwaInfo;
import valueObject.VCPersonalInfo;
import view.MainPanel.MainPanActionHandler;

@SuppressWarnings("serial")
public class ResisterPan extends SaveLoadStuff{
	
	VCGangJwaInfo vCGangJwaInfo;
	CGangJwaInfo cGangJwaInfo;
	
	DirectoryList directoryList1, directoryList2, directoryList3;
	TablePan lectureTable, basketTable, resultTable;
	TimeTable timeTableFrame;
	ToolPan toolPan;
	
	String nowID;
	JLabel insa;
	
	Vector<JComponent> contents;
	
	public ResisterPan(MainPanActionHandler actionHandler) {// �� �г� ������ �� �ְ� �Ϸ��� ��¿�� ���� �� �ϴ�.
		this.setBackground(Color.WHITE);
		this.setLayout(null);

		timeTableFrame = new TimeTable("�ð�ǥ");
		timeTableFrame.setVisible(false);

		ResisterPanActionHandler lowerPanActionHandler = new ResisterPanActionHandler();
		ListSelectionHandler listSelectionListener = new ListSelectionHandler();

		insa = new JLabel();
		insa.setBounds(900, 20, 300, 20);
		this.add(insa);

		toolPan = new ToolPan(actionHandler, lowerPanActionHandler);//�¹ٿ�� �������ö� ���༭, ���� ���ص� ��.
		this.add(toolPan);

		this.directoryList1 = new DirectoryList(listSelectionListener, "ķ�۽�", "ķ�۽��� ������.");
		this.directoryList2 = new DirectoryList(listSelectionListener, "����", "������ ������.");
		this.directoryList3 = new DirectoryList(listSelectionListener, "�а�", "�а��� ������");
		this.add(directoryList1);
		this.add(directoryList2);
		this.add(directoryList3);

		String[][] lectureTableHeader = { { "���", "[���]", "ToBasket" } };
		String[][] basketTableHeader = { { "��û", "[��û]", "Sincheong" }, { "����", "[����]", "DeleteBasket" } };
		String[][] resultTableHeader = { { "����", "[����]", "DeleteReasult" } };
		this.lectureTable = new TablePan(lectureTableHeader, lowerPanActionHandler, "����", "���µ� �Դϴ�.");
		this.basketTable = new TablePan(basketTableHeader, lowerPanActionHandler, "�轺Ŷ", "�轺Ŷ �Դϴ�.");
		this.resultTable = new TablePan(resultTableHeader, lowerPanActionHandler, "��û ��", "��û�� ���µ� �Դϴ�.");
		this.add(lectureTable);
		this.add(basketTable);
		this.add(resultTable);

		makeJComponentVector();
	}
	
	public void initiate(String nowID) {this.nowID = nowID; refresh(this.nowID);}
	
	public void refresh(String nowID) {
		CSIDPersonalInfo cPersonalInfo = new CSIDPersonalInfo();
		VCPersonalInfo vPersonalInfo = cPersonalInfo.getPersonalInfo(nowID);
		insa.setText(vPersonalInfo.getName() + "�� �ȳ��ϼ���!");

		refreshLecture(null);//���¼���â �ʱ�ȭ
		
		try {
		cGangJwaInfo = new CGangJwaInfo();
		vCGangJwaInfo = cGangJwaInfo.getGangJwaInfo(nowID);
		basketTable.refreshWithVector(makeVector(vCGangJwaInfo.getBasketData()));//�轺Ŷ �ҷ�����
		resultTable.refreshWithVector(makeVector(vCGangJwaInfo.getReasultData()));//��� �ҷ�����
		setAllBounds(vCGangJwaInfo.getdotsData(),contents);//��ġ �ҷ�����
		}catch(Exception e) {//ó�� ȸ�������ϰ�, �α��� �ϴ� ���
		basketTable.refreshWithVector(makeVector("0000"));//�⺻ ���
		resultTable.refreshWithVector(makeVector("0000"));//�⺻ ��û
		setAllBounds("860/140/200/70/360/220/200/200/560/220/200/200/760/220/200/200/360/420/600/200/960/220/600/200/960/420/600/200/",contents);//�⺻��ġ
		}
	}
	
	public void refreshLecture(Object source) {
		if(source==null) {
			this.directoryList1.refresh("root");
			this.directoryList2.refresh(this.directoryList1.getSelectedFile());
			this.directoryList3.refresh(this.directoryList2.getSelectedFile());
			this.lectureTable.refresh(this.directoryList3.getSelectedFile());
		}else if(source.equals(this.directoryList1.list)) {
			this.directoryList2.refresh(this.directoryList1.getSelectedFile());
			this.directoryList3.refresh(this.directoryList2.getSelectedFile());
			this.lectureTable.refresh(this.directoryList3.getSelectedFile());
		}else if(source.equals(this.directoryList2.list)) {
			this.directoryList3.refresh(this.directoryList2.getSelectedFile());
			this.lectureTable.refresh(this.directoryList3.getSelectedFile());
		}else if(source.equals(this.directoryList3.list)) {
			this.lectureTable.refresh(this.directoryList3.getSelectedFile());
		}
	}
	
	class ListSelectionHandler implements ListSelectionListener{
		public void valueChanged(ListSelectionEvent e) {refreshLecture(e.getSource());}//����ϴ� ��
	}
	
	public class ResisterPanActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String AC = e.getActionCommand();
			if (AC.equals("ToBasket")) {basketTable.addRow(lectureTable.getRow(),false);}
			else if (AC.equals("Sincheong")) {resultTable.addRow(basketTable.getRow(), true);}
			else if (AC.equals("DeleteBasket")) {basketTable.deleteRow();}
			else if (AC.equals("DeleteReasult")) {resultTable.deleteRow();}
			else if (AC.equals("time")) {timeTableControl(1);}
			if(AC.equals("Sincheong")||AC.equals("DeleteReasult")) {timeTableControl(0);}
			saveAll();
		}
	}
	
	public void saveAll() {save(nowID,makeSaveIDString(basketTable.getAllData())+" "+makeSaveIDString(resultTable.getAllData())+" "+dotsToString(contents));}
	
	public void finish() {
		saveAll();
		timeTableFrame.setVisible(false);
	}
	
	public void timeTableControl(int i) {
		if(i==1) {timeTableFrame.setVisible(true);}
		timeTableFrame.refreshData(resultTable.vCLectures);
		timeTableFrame.repaint();		
	}
	
	private void makeJComponentVector() {
		contents = new Vector<JComponent>();
		contents.add(toolPan);
		contents.add(directoryList1);
		contents.add(directoryList2);
		contents.add(directoryList3);
		contents.add(lectureTable);
		contents.add(basketTable);
		contents.add(resultTable);
	}
}
