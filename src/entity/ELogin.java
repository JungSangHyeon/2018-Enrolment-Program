package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VESignUpInfo;

public class ELogin {

	public VESignUpInfo validateUser(String ID, String pW) {//ID�� PW�� �ش��ϴ� nowID�� VO����Ƽ� �������ش�
		try {
			VESignUpInfo vESignUpInfo = new VESignUpInfo();
			File f = new File("student/login");
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				vESignUpInfo.readFromFile(sc); // ���θ� �𸣱⶧���� �ڽ��� ���� ��������. 
				if(vESignUpInfo.getID().equals(ID)&&vESignUpInfo.getPW().equals(pW)) {
					vESignUpInfo.setLoginSuccess(true);
					return vESignUpInfo;
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		return null;
	}

}
