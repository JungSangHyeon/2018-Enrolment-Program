package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VEGangJwaInfo;

public class EGangJwaInfo {

	public VEGangJwaInfo getGangJwaInfo(String id) {//입력된 Student ID에 따라 테이블 정보 리턴
		VEGangJwaInfo vEGangJwaInfo = new VEGangJwaInfo();
		try {
			Scanner sc = new Scanner(new File("student/GangjwalInfo"));
			while(sc.hasNext()) {
				vEGangJwaInfo.readFromFile(sc);
				if(vEGangJwaInfo.getID().equals(id)) {return vEGangJwaInfo;}
			}
			sc.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		return null;
	}
}
