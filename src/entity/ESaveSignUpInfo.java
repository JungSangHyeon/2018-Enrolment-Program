package entity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import valueObject.VESignUpInfo;

public class ESaveSignUpInfo {

	@SuppressWarnings("resource")
	public void saveSignUpInfo(VESignUpInfo vESignUpInfo) {//login.txt�� �Է¹��� �����͸� ����
		try {
			File f = new File("student/login");
			FileWriter fw = new FileWriter(f, true);
			fw.write(vESignUpInfo.getnowID() + " " +vESignUpInfo.getID() + " " + vESignUpInfo.getPW() + "\r\n");
			fw.flush();
		} catch (IOException e) {e.printStackTrace();}
	}

}
