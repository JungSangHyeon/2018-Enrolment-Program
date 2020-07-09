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
	
	public ResisterPan(MainPanActionHandler actionHandler) {// 한 패널 위에서 다 있게 하려면 어쩔수 없는 듯 하다.
		this.setBackground(Color.WHITE);
		this.setLayout(null);

		timeTableFrame = new TimeTable("시간표");
		timeTableFrame.setVisible(false);

		ResisterPanActionHandler lowerPanActionHandler = new ResisterPanActionHandler();
		ListSelectionHandler listSelectionListener = new ListSelectionHandler();

		insa = new JLabel();
		insa.setBounds(900, 20, 300, 20);
		this.add(insa);

		toolPan = new ToolPan(actionHandler, lowerPanActionHandler);//셋바운드 리프레시때 해줘서, 여서 안해도 됨.
		this.add(toolPan);

		this.directoryList1 = new DirectoryList(listSelectionListener, "캠퍼스", "캠퍼스를 고르세요.");
		this.directoryList2 = new DirectoryList(listSelectionListener, "대학", "대학을 고르세요.");
		this.directoryList3 = new DirectoryList(listSelectionListener, "학과", "학과를 고르세요");
		this.add(directoryList1);
		this.add(directoryList2);
		this.add(directoryList3);

		String[][] lectureTableHeader = { { "담기", "[담기]", "ToBasket" } };
		String[][] basketTableHeader = { { "신청", "[신청]", "Sincheong" }, { "삭제", "[삭제]", "DeleteBasket" } };
		String[][] resultTableHeader = { { "삭제", "[삭제]", "DeleteReasult" } };
		this.lectureTable = new TablePan(lectureTableHeader, lowerPanActionHandler, "강좌", "강좌들 입니다.");
		this.basketTable = new TablePan(basketTableHeader, lowerPanActionHandler, "배스킷", "배스킷 입니다.");
		this.resultTable = new TablePan(resultTableHeader, lowerPanActionHandler, "신청 됨", "신청된 강좌들 입니다.");
		this.add(lectureTable);
		this.add(basketTable);
		this.add(resultTable);

		makeJComponentVector();
	}
	
	public void initiate(String nowID) {this.nowID = nowID; refresh(this.nowID);}
	
	public void refresh(String nowID) {
		CSIDPersonalInfo cPersonalInfo = new CSIDPersonalInfo();
		VCPersonalInfo vPersonalInfo = cPersonalInfo.getPersonalInfo(nowID);
		insa.setText(vPersonalInfo.getName() + "님 안녕하세요!");

		refreshLecture(null);//강좌선택창 초기화
		
		try {
		cGangJwaInfo = new CGangJwaInfo();
		vCGangJwaInfo = cGangJwaInfo.getGangJwaInfo(nowID);
		basketTable.refreshWithVector(makeVector(vCGangJwaInfo.getBasketData()));//배스킷 불러오기
		resultTable.refreshWithVector(makeVector(vCGangJwaInfo.getReasultData()));//결과 불러오기
		setAllBounds(vCGangJwaInfo.getdotsData(),contents);//배치 불러오기
		}catch(Exception e) {//처음 회원가입하고, 로그인 하는 경우
		basketTable.refreshWithVector(makeVector("0000"));//기본 담기
		resultTable.refreshWithVector(makeVector("0000"));//기본 신청
		setAllBounds("860/140/200/70/360/220/200/200/560/220/200/200/760/220/200/200/360/420/600/200/960/220/600/200/960/420/600/200/",contents);//기본배치
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
		public void valueChanged(ListSelectionEvent e) {refreshLecture(e.getSource());}//명령하는 곳
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
