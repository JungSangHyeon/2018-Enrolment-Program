package resisterView;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JOptionPane;

import valueObject.VCLecture;

public class TimeStuff {//시간 관련을 모아놓음. TablePan과 Time Table이 쓴다.
	
	public boolean sameTime(VCLecture row, Vector<VCLecture> vCLectures,Component parent) {//시간이 곂치는지 판별
		for(VCLecture nowrow : vCLectures) {
			Vector<String> rowV = Time(row.getTime());
			int rowst = Integer.parseInt(rowV.get(rowV.size()-2));//시작시간
			int rowet = Integer.parseInt(rowV.get(rowV.size()-1));//끝시간
			
			Vector<String> nowrowV = Time(nowrow.getTime());
			int nowrowst = Integer.parseInt(nowrowV.get(nowrowV.size()-2));
			int nowrowet = Integer.parseInt(nowrowV.get(nowrowV.size()-1));
			
			if(!(nowrowet<rowst||rowet<nowrowst)) {
				for(int i=0; i<nowrowV.get(0).length();i++) {
					for(int v=0; v<rowV.get(0).length();v++) {
						if(nowrowV.get(0).charAt(i)==rowV.get(0).charAt(v)){
							JOptionPane.showMessageDialog(parent, row.getName()+"("+timeBeautifier(row.getTime())+")"+"은 "+nowrow.getName()+"("+timeBeautifier(nowrow.getTime())+") 과 시간이 겹칩니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
							return true;
						}
					}}}}
		return false;
	}
	
	public String timeBeautifier(String uglytime) {//못생긴 시간을 이쁘게 만들어준다. 형식 = 요일n개 + 0000-0000
		StringBuffer beautifulTime = new StringBuffer(uglytime.replace("-", "~"));
		beautifulTime.insert(beautifulTime.length()-2, ":");
		beautifulTime.insert(beautifulTime.length()-8, ":");
		beautifulTime.insert(beautifulTime.length()-11, "/");
		 String[] array = beautifulTime.toString().split("/");
		return array[1];
	}
	
	public Vector<String> Time(String time) {// 요일n개 0000-0000 //요일은 달라도 한 수업에서 다른 시간은 읎다. //시간을 나눠줌.
		Vector<String> reasult = new Vector<String>();// {요일몇개, 시작시간, 끝시간}
		
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
	
	public int intMakeTime(String time) {//진짜 시간으로 만들어줌.
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
