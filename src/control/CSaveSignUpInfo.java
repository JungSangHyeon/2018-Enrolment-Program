package control;

import entity.ESaveSignUpInfo;
import valueObject.VCSignUpInfo;
import valueObject.VESignUpInfo;

public class CSaveSignUpInfo {
	
	public void saveSignUpInfo (VCSignUpInfo vCSignUpInfo) {//login.txt�� �Է¹��� �����͸� ����
		VESignUpInfo vESignUpInfo = new VESignUpInfo();
		vESignUpInfo.setnowID(vCSignUpInfo.getnowID());
		vESignUpInfo.setID(vCSignUpInfo.getID());
		vESignUpInfo.setPW(vCSignUpInfo.getPW());
		
		ESaveSignUpInfo eSaveSignUpInfo = new ESaveSignUpInfo();
		eSaveSignUpInfo.saveSignUpInfo(vESignUpInfo);
	}

}
