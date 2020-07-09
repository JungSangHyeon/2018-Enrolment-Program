package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VEPersonalInfo;

public class ESIDPersonalInfo {

	public VEPersonalInfo getPersonalInfo(String id) {//입력된 Student ID에 따라 개인정보를 리턴
		VEPersonalInfo vEPersonalInfo = new VEPersonalInfo();
		
		try {
			File f = new File("student/PersonalInfo");
			Scanner sc = new Scanner(f);
			while(sc.hasNext()) {
				vEPersonalInfo.readFromFile(sc); // 내부를 모르기때문에 자식이 직접 읽으라함. 
				if(vEPersonalInfo.getId().equals(id)) {
					return vEPersonalInfo;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		return null;
	}

}
