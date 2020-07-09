package control;

import entity.EValidateUser;

public class CValidateUser {

	private EValidateUser eValidateUser;
	
	public boolean validateUser(String iD, String nAME) {//txt파일에 주어진 Student ID와 Name이 있는지 알려줌.
		this.eValidateUser = new EValidateUser();
		return this.eValidateUser.validateUser(iD,nAME);
	}

}
