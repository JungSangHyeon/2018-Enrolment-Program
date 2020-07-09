package control;

import entity.ELogin;
import valueObject.VCSignUpInfo;
import valueObject.VESignUpInfo;

public class CLogin {

	
	public VCSignUpInfo validateUser(String ID, String PW) {//ID�� PW�� �ش��ϴ� nowID�� VO����Ƽ� �������ش�
		ELogin eLogin = new ELogin();
		try {
		//get data from entity
		VESignUpInfo vESignUpInfo =eLogin.validateUser(ID,PW);
		
		//create value object and set data from entity value object
		VCSignUpInfo vCPersonalInfo = new VCSignUpInfo();
		vCPersonalInfo.setnowID(vESignUpInfo.getnowID());
		vCPersonalInfo.setLoginSuccess(vESignUpInfo.getLoginSuccess());
		return vCPersonalInfo;
    	}catch(Exception e) {return null;}
	}
}
