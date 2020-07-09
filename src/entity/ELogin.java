package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VESignUpInfo;

public class ELogin {

	public VESignUpInfo validateUser(String ID, String pW) {//ID와 PW에 해당하는 nowID를 VO에담아서 전달해준다
		try {
			VESignUpInfo vESignUpInfo = new VESignUpInfo();
			File f = new File("student/login");
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				vESignUpInfo.readFromFile(sc); // 내부를 모르기때문에 자식이 직접 읽으라함. 
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
