package resisterView;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import constants.Constants;
import control.CLecture;
import myAmazingThings.MovingPanel;
import myAmazingThings.NiceBTN;
import resisterView.ResisterPan.ResisterPanActionHandler;
import valueObject.VCLecture;

public class TablePan extends MovingPanel {
	private static final long serialVersionUID = 1L;

	CLecture cLecture;
	Vector<VCLecture> vCLectures;//데이타
	
	JTable table;
	Vector<String> header;
	DefaultTableModel tableModel;
	
	TimeStuff timeStuff;
	JScrollPane scPane;
	
	public TablePan(String[][] colum, ResisterPanActionHandler actionHandler,String tableTitle,String toolTip) {//테이블 3개 다 나눌까.이걸 슈퍼로 해서.
		super(100,100,tableTitle);
		this.timeStuff = new TimeStuff();
		
		this.cLecture = new CLecture();// 연결유지
		this.vCLectures = new Vector<VCLecture>();
		
		ComponentHandler componentHandler = new ComponentHandler();
		this.addComponentListener(componentHandler);
		
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//없으면 터진다.(레이아웃)
		
		header = new Vector<String>();//헤더를 만드는 부분
		for(int i=0;i<Constants.HEADER.length;i++) {header.add(Constants.HEADER[i]);}
		for(int i=0;i<colum.length;i++) {header.add(colum[i][0]);}
		tableModel = new DefaultTableModel(header, 0);//편집을 막으면 버튼 클릭이 안된다.
		
		table = new JTable(tableModel);
		table.setToolTipText(toolTip);
		table.setFillsViewportHeight(true);//배경 휜색
		table.getTableHeader().setReorderingAllowed(false);// colum이동 불가
		table.getTableHeader().setResizingAllowed(false);// colum사이즈 조정 불가
		table.getTableHeader().setBackground(Color.WHITE);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tableCellCenter();
	      
		scPane = new JScrollPane(table);
		this.add(scPane);
		  
		for(int i=0;i<colum.length;i++) {//로우에 버튼을 넣는 부분
			table.getColumnModel().getColumn(Constants.HEADER.length+i).setCellRenderer(new TableCell(colum[i][1],colum[i][2],actionHandler));
			table.getColumnModel().getColumn(Constants.HEADER.length+i).setCellEditor(new TableCell(colum[i][1],colum[i][2],actionHandler));
		}
	}
	
	@SuppressWarnings("serial")
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{//extends는 쓸데없이 보이는것을 없에기 위함.
		NiceBTN btn;
        public TableCell(String btnName, String aC, ResisterPanActionHandler actionHandler) {//nicebtn쓸까
        	btn = new NiceBTN(btnName,aC);
        	btn.setBackground(Color.WHITE);
        	btn.setForeground(Color.BLUE);
        	btn.addActionListener(actionHandler);
        }
        public Object getCellEditorValue() {return null;}
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {return btn;}
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {return btn;}
    }
	
	public void tableCellCenter(){// 테이블 내용 가운데 정렬하기
	      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
	      dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
	      TableColumnModel tcm = table.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
	      tcm.getColumn(1).setCellRenderer(dtcr);
	      tcm.getColumn(3).setCellRenderer(dtcr);
	    }
	
	public void refresh(String fileName) {//렉쳐용
		if(fileName==null) {this.vCLectures.removeAllElements();}
		else {this.vCLectures = this.cLecture.getData(fileName);}
		babyrefresh();
	}
	
	public void refreshWithVector(Vector<VCLecture> makeVector) {//배스킷, 리절트용
		this.vCLectures = makeVector;
		babyrefresh();
	}

	public void babyrefresh() {
		table.selectAll();//nullPointer 에러를 없엠. 버튼 클릭시, 제대로 포커스가 안가는 경우가 있음. 다시 한번 선택되게함.
		tableModel.setRowCount(0);//clear
		Vector<String> rowData = null;
		for (VCLecture vcLecture : vCLectures) {
			rowData = new Vector<String>();
			rowData.addElement(vcLecture.getName());
			rowData.addElement(vcLecture.getProfessorName());
			rowData.addElement(vcLecture.getTime());
			rowData.addElement(vcLecture.getCredit()+"");//꼬옴수
			tableModel.addRow(rowData);
		}
		this.updateUI();
	}
	
	public VCLecture getRow() {return vCLectures.get(table.getSelectedRow());}
	
	public void deleteRow() {
		vCLectures.remove(table.getSelectedRow());
		babyrefresh();
	}
	
	public void addRow(VCLecture row, boolean timecheck) {
		if(!samecheck(row)) {
			if(timecheck&&!this.timeStuff.sameTime(row,vCLectures,this)) {//시간중복과 강좌중복 쳌
				vCLectures.add(row);
				babyrefresh();
			}
			if(!timecheck){//중복만 체크
				vCLectures.add(row);
				babyrefresh();
			}
		}
	}
	
	private boolean samecheck(VCLecture row) {
		boolean ihaveit = false;
		int i = 0;
		for (VCLecture nowrow : vCLectures) {
			if (nowrow.getId() == row.getId()) {
				ihaveit = true;
				JOptionPane.showMessageDialog(this, row.getName() + " 강의는 이미 있습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				table.changeSelection(i, 0, false, false);// 어디있나 알려줌. 좋음!
			}
			i++;
		}
		return ihaveit;
	}

	public Vector<VCLecture> getAllData() {return vCLectures;}
	
	class ComponentHandler implements ComponentListener{
		public void componentResized(ComponentEvent e) {
			scPane.setBounds(10,20,f_width-20, f_height-30);
			table.setBounds(10,20,f_width-20, f_height-30);		
		}
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
	}
}
