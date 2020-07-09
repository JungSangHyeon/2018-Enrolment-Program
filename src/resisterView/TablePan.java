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
	Vector<VCLecture> vCLectures;//����Ÿ
	
	JTable table;
	Vector<String> header;
	DefaultTableModel tableModel;
	
	TimeStuff timeStuff;
	JScrollPane scPane;
	
	public TablePan(String[][] colum, ResisterPanActionHandler actionHandler,String tableTitle,String toolTip) {//���̺� 3�� �� ������.�̰� ���۷� �ؼ�.
		super(100,100,tableTitle);
		this.timeStuff = new TimeStuff();
		
		this.cLecture = new CLecture();// ��������
		this.vCLectures = new Vector<VCLecture>();
		
		ComponentHandler componentHandler = new ComponentHandler();
		this.addComponentListener(componentHandler);
		
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//������ ������.(���̾ƿ�)
		
		header = new Vector<String>();//����� ����� �κ�
		for(int i=0;i<Constants.HEADER.length;i++) {header.add(Constants.HEADER[i]);}
		for(int i=0;i<colum.length;i++) {header.add(colum[i][0]);}
		tableModel = new DefaultTableModel(header, 0);//������ ������ ��ư Ŭ���� �ȵȴ�.
		
		table = new JTable(tableModel);
		table.setToolTipText(toolTip);
		table.setFillsViewportHeight(true);//��� �ػ�
		table.getTableHeader().setReorderingAllowed(false);// colum�̵� �Ұ�
		table.getTableHeader().setResizingAllowed(false);// colum������ ���� �Ұ�
		table.getTableHeader().setBackground(Color.WHITE);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tableCellCenter();
	      
		scPane = new JScrollPane(table);
		this.add(scPane);
		  
		for(int i=0;i<colum.length;i++) {//�ο쿡 ��ư�� �ִ� �κ�
			table.getColumnModel().getColumn(Constants.HEADER.length+i).setCellRenderer(new TableCell(colum[i][1],colum[i][2],actionHandler));
			table.getColumnModel().getColumn(Constants.HEADER.length+i).setCellEditor(new TableCell(colum[i][1],colum[i][2],actionHandler));
		}
	}
	
	@SuppressWarnings("serial")
	class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{//extends�� �������� ���̴°��� ������ ����.
		NiceBTN btn;
        public TableCell(String btnName, String aC, ResisterPanActionHandler actionHandler) {//nicebtn����
        	btn = new NiceBTN(btnName,aC);
        	btn.setBackground(Color.WHITE);
        	btn.setForeground(Color.BLUE);
        	btn.addActionListener(actionHandler);
        }
        public Object getCellEditorValue() {return null;}
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {return btn;}
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {return btn;}
    }
	
	public void tableCellCenter(){// ���̺� ���� ��� �����ϱ�
	      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // ����Ʈ���̺��������� ����
	      dtcr.setHorizontalAlignment(SwingConstants.CENTER); // �������� ���������� CENTER��
	      TableColumnModel tcm = table.getColumnModel(); // ������ ���̺��� �÷����� ������
	      tcm.getColumn(1).setCellRenderer(dtcr);
	      tcm.getColumn(3).setCellRenderer(dtcr);
	    }
	
	public void refresh(String fileName) {//���Ŀ�
		if(fileName==null) {this.vCLectures.removeAllElements();}
		else {this.vCLectures = this.cLecture.getData(fileName);}
		babyrefresh();
	}
	
	public void refreshWithVector(Vector<VCLecture> makeVector) {//�轺Ŷ, ����Ʈ��
		this.vCLectures = makeVector;
		babyrefresh();
	}

	public void babyrefresh() {
		table.selectAll();//nullPointer ������ ����. ��ư Ŭ����, ����� ��Ŀ���� �Ȱ��� ��찡 ����. �ٽ� �ѹ� ���õǰ���.
		tableModel.setRowCount(0);//clear
		Vector<String> rowData = null;
		for (VCLecture vcLecture : vCLectures) {
			rowData = new Vector<String>();
			rowData.addElement(vcLecture.getName());
			rowData.addElement(vcLecture.getProfessorName());
			rowData.addElement(vcLecture.getTime());
			rowData.addElement(vcLecture.getCredit()+"");//���ȼ�
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
			if(timecheck&&!this.timeStuff.sameTime(row,vCLectures,this)) {//�ð��ߺ��� �����ߺ� �n
				vCLectures.add(row);
				babyrefresh();
			}
			if(!timecheck){//�ߺ��� üũ
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
				JOptionPane.showMessageDialog(this, row.getName() + " ���Ǵ� �̹� �ֽ��ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
				table.changeSelection(i, 0, false, false);// ����ֳ� �˷���. ����!
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
