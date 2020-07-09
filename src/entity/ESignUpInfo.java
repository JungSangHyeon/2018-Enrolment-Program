package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VESignUpInfo;

public class ESignUpInfo {

	public VESignUpInfo getSignUpInfo(String nowID) {//nowID의 ID와 PW를 VO에담아서 전달해준다
		try {
			VESignUpInfo vESignUpInfo = new VESignUpInfo();
			File f = new File("student/login");
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				vESignUpInfo.readFromFile(sc); // 내부를 모르기때문에 자식이 직접 읽으라함. 
				if(vESignUpInfo.getnowID().equals(nowID)) {return vESignUpInfo;}
			}
			sc.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		return null;
	}
}
