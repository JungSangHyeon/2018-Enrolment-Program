package control;

import entity.ESignUpInfo;
import valueObject.VCSignUpInfo;
import valueObject.VESignUpInfo;

public class CSignUpInfo {
	
	private ESignUpInfo ePersonalInfo;
	
	public CSignUpInfo() {
		 ePersonalInfo = new ESignUpInfo();
	}

	public VCSignUpInfo getSignUpInfo(String nowID) {//nowID의 ID와 PW를 VO에담아서 전달해준다
		try {
		//get data from entity
		VESignUpInfo vESignUpInfo =this.ePersonalInfo.getSignUpInfo(nowID);
		
		//create value object and set data from entity value object
		VCSignUpInfo vCPersonalInfo = new VCSignUpInfo();
		vCPersonalInfo.setID(vESignUpInfo.getID());
		vCPersonalInfo.setPW(vESignUpInfo.getPW());
		return vCPersonalInfo;
		}catch(Exception e) {return null;}//null밭은경우 그대로 null전달. null의 뜻 = 준ID에 해당하는게 null임!)
	}
}
