package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VEPersonalInfo;

public class ESIDPersonalInfo {

	public VEPersonalInfo getPersonalInfo(String id) {//�Էµ� Student ID�� ���� ���������� ����
		VEPersonalInfo vEPersonalInfo = new VEPersonalInfo();
		
		try {
			File f = new File("student/PersonalInfo");
			Scanner sc = new Scanner(f);
			while(sc.hasNext()) {
				vEPersonalInfo.readFromFile(sc); // ���θ� �𸣱⶧���� �ڽ��� ���� ��������. 
				if(vEPersonalInfo.getId().equals(id)) {
					return vEPersonalInfo;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		return null;
	}

}
