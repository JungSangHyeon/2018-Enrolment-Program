package control;

import entity.EIDOverlapCheck;

public class CIDOverlapCheck {

	private EIDOverlapCheck eSignUpUser;
	
	public boolean idOverlapCheck(String signUpID) {//txt파일에 주어진 ID가 있는지 알려줌.
		this.eSignUpUser = new EIDOverlapCheck();
		return this.eSignUpUser.idOverlapCheck(signUpID);
	}

}
