package control;

import entity.EIDOverlapCheck;

public class CIDOverlapCheck {

	private EIDOverlapCheck eSignUpUser;
	
	public boolean idOverlapCheck(String signUpID) {//txt���Ͽ� �־��� ID�� �ִ��� �˷���.
		this.eSignUpUser = new EIDOverlapCheck();
		return this.eSignUpUser.idOverlapCheck(signUpID);
	}

}
