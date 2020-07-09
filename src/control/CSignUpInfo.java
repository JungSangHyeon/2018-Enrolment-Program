package control;

import entity.ESignUpInfo;
import valueObject.VCSignUpInfo;
import valueObject.VESignUpInfo;

public class CSignUpInfo {
	
	private ESignUpInfo ePersonalInfo;
	
	public CSignUpInfo() {
		 ePersonalInfo = new ESignUpInfo();
	}

	public VCSignUpInfo getSignUpInfo(String nowID) {//nowID�� ID�� PW�� VO����Ƽ� �������ش�
		try {
		//get data from entity
		VESignUpInfo vESignUpInfo =this.ePersonalInfo.getSignUpInfo(nowID);
		
		//create value object and set data from entity value object
		VCSignUpInfo vCPersonalInfo = new VCSignUpInfo();
		vCPersonalInfo.setID(vESignUpInfo.getID());
		vCPersonalInfo.setPW(vESignUpInfo.getPW());
		return vCPersonalInfo;
		}catch(Exception e) {return null;}//null������� �״�� null����. null�� �� = ��ID�� �ش��ϴ°� null��!)
	}
}
