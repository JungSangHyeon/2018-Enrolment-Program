package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VESignUpInfo;

public class ESignUpInfo {

	public VESignUpInfo getSignUpInfo(String nowID) {//nowID�� ID�� PW�� VO����Ƽ� �������ش�
		try {
			VESignUpInfo vESignUpInfo = new VESignUpInfo();
			File f = new File("student/login");
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				vESignUpInfo.readFromFile(sc); // ���θ� �𸣱⶧���� �ڽ��� ���� ��������. 
				if(vESignUpInfo.getnowID().equals(nowID)) {return vESignUpInfo;}
			}
			sc.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		return null;
	}
}
