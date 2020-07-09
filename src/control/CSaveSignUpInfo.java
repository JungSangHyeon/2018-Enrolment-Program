package control;

import entity.ESaveSignUpInfo;
import valueObject.VCSignUpInfo;
import valueObject.VESignUpInfo;

public class CSaveSignUpInfo {
	
	public void saveSignUpInfo (VCSignUpInfo vCSignUpInfo) {//login.txt에 입력받은 데이터를 쓴다
		VESignUpInfo vESignUpInfo = new VESignUpInfo();
		vESignUpInfo.setnowID(vCSignUpInfo.getnowID());
		vESignUpInfo.setID(vCSignUpInfo.getID());
		vESignUpInfo.setPW(vCSignUpInfo.getPW());
		
		ESaveSignUpInfo eSaveSignUpInfo = new ESaveSignUpInfo();
		eSaveSignUpInfo.saveSignUpInfo(vESignUpInfo);
	}

}
