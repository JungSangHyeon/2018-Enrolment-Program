package control;

import entity.EValidateUser;

public class CValidateUser {

	private EValidateUser eValidateUser;
	
	public boolean validateUser(String iD, String nAME) {//txt���Ͽ� �־��� Student ID�� Name�� �ִ��� �˷���.
		this.eValidateUser = new EValidateUser();
		return this.eValidateUser.validateUser(iD,nAME);
	}

}
