package resisterView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;

import control.CGangJwaFind;
import valueObject.VCLecture;

@SuppressWarnings("serial")
public class SaveLoadStuff extends JPanel{
	
	protected String dotsToString(Vector<JComponent> contents) {//���� Component���� ��ǥ�� ����� �ϳ��� String���� ����.
		String result = "";
		for(JComponent jc : contents) {result += makeDotsString(jc);}
		return result;
	}
	
	private String makeDotsString(JComponent object) {//���ǰ� ģ��
		String result = "";
		result += object.getX()+"/";
		result += object.getY()+"/";
		result += object.getWidth()+"/";
		result += object.getHeight()+"/";
		return result;
	}

	protected void setAllBounds(String getdotsData, Vector<JComponent> contents) {//��ǥ, ������ �ҷ�����.
		 String[] array = getdotsData.split("/");
		 int i=0;
		 for(JComponent jc : contents) {
			 jc.setBounds(Integer.parseInt(array[4*i]),Integer.parseInt(array[4*i+1]),Integer.parseInt(array[4*i+2]),Integer.parseInt(array[4*i+3]));
			 i++;
		}
	}
	
	
	protected String makeSaveIDString(Vector<VCLecture> allData) {//�轺Ŷ�̳� ����Ʈ�� ���� ��Ʈ�� ����� ��
		String reasult = "0000";
		for (VCLecture row : allData) {reasult+=row.getId();}
		return reasult;
	}
	
	protected Vector<VCLecture> makeVector(String gangJwaIDS) {//id�� ���¸�  ã�ƿɴϴ�. �轺Ŷ, ����Ʈ �ҷ�����
		Vector<VCLecture> reasult = new Vector<VCLecture>();
		for(int i=0; i<gangJwaIDS.length()/4-1;i++) {
			CGangJwaFind cGangJwaFind = new CGangJwaFind();
			VCLecture vCLecture = cGangJwaFind.getGangJwaInfo(gangJwaIDS.substring(0+4*(i+1), 4+4*(i+1)));
			if(vCLecture!=null) {reasult.add(vCLecture);}
		}
		return reasult;
	}
	
	@SuppressWarnings("resource")
	protected void save(String nowID, String info) {//�轺Ŷ, ����Ʈ, ��ǥ������ ��� ����.
		try {
			boolean imethim = false;
			File f = new File("student/GangJwalInfo");
			FileWriter fw = new FileWriter("student/Temp", false) ;
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {//Temp����
				String nl = sc.nextLine();
				Scanner sc2 = new Scanner(nl);
				String id=sc2.next();
				if(id.equals(nowID)) {
					imethim = true;
					fw.write(id+" "+info);
					fw.write("\r\n");
				}else {
					fw.write(nl);
					fw.write("\r\n");
				}
			}
			if(!imethim&&nowID!=null) {
				fw.write(nowID+" "+info);
				fw.write("\r\n");
			}
			fw.flush();
			f = new File("student/Temp");
			sc = new Scanner(f);
			fw = new FileWriter("student/GangJwalInfo", false) ;
			while(sc.hasNextLine()) {
				fw.write(sc.nextLine());
				fw.write("\r\n");
			}
			fw.flush();
		} catch (IOException e) {e.printStackTrace();}
	}
}
