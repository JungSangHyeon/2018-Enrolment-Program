package resisterView;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JOptionPane;

import valueObject.VCLecture;

public class TimeStuff {//�ð� ������ ��Ƴ���. TablePan�� Time Table�� ����.
	
	public boolean sameTime(VCLecture row, Vector<VCLecture> vCLectures,Component parent) {//�ð��� ��ġ���� �Ǻ�
		for(VCLecture nowrow : vCLectures) {
			Vector<String> rowV = Time(row.getTime());
			int rowst = Integer.parseInt(rowV.get(rowV.size()-2));//���۽ð�
			int rowet = Integer.parseInt(rowV.get(rowV.size()-1));//���ð�
			
			Vector<String> nowrowV = Time(nowrow.getTime());
			int nowrowst = Integer.parseInt(nowrowV.get(nowrowV.size()-2));
			int nowrowet = Integer.parseInt(nowrowV.get(nowrowV.size()-1));
			
			if(!(nowrowet<rowst||rowet<nowrowst)) {
				for(int i=0; i<nowrowV.get(0).length();i++) {
					for(int v=0; v<rowV.get(0).length();v++) {
						if(nowrowV.get(0).charAt(i)==rowV.get(0).charAt(v)){
							JOptionPane.showMessageDialog(parent, row.getName()+"("+timeBeautifier(row.getTime())+")"+"�� "+nowrow.getName()+"("+timeBeautifier(nowrow.getTime())+") �� �ð��� ��Ĩ�ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
							return true;
						}
					}}}}
		return false;
	}
	
	public String timeBeautifier(String uglytime) {//������ �ð��� �̻ڰ� ������ش�. ���� = ����n�� + 0000-0000
		StringBuffer beautifulTime = new StringBuffer(uglytime.replace("-", "~"));
		beautifulTime.insert(beautifulTime.length()-2, ":");
		beautifulTime.insert(beautifulTime.length()-8, ":");
		beautifulTime.insert(beautifulTime.length()-11, "/");
		 String[] array = beautifulTime.toString().split("/");
		return array[1];
	}
	
	public Vector<String> Time(String time) {// ����n�� 0000-0000 //������ �޶� �� �������� �ٸ� �ð��� ����. //�ð��� ������.
		Vector<String> reasult = new Vector<String>();// {���ϸ, ���۽ð�, ���ð�}
		
		String temp = "";
		for (int i = 0; i < time.length() - 9; i++) {temp += time.charAt(i);}
		reasult.addElement(temp);

		temp = "";
		for (int i = 0; i < 4; i++) {temp += time.charAt(time.length() - 9 + i);}
		reasult.addElement(temp);

		temp = "";
		for (int i = 0; i < 4; i++) {temp += time.charAt(time.length() - 4 + i);}
		reasult.addElement(temp);
		
	    return reasult;
	}
	
	public int intMakeTime(String time) {//��¥ �ð����� �������.
		String hourString="";
		hourString += time.charAt(0);
		hourString +=time.charAt(1);
		
		String minString="";
		minString += time.charAt(2);
		minString +=time.charAt(3);
		
		int realtime = Integer.parseInt(hourString)*60 + Integer.parseInt(minString);
		return realtime;
	}
}
