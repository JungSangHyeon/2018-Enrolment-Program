package control;

import entity.EGangJwaInfo;
import valueObject.VCGangJwaInfo;
import valueObject.VEGangJwaInfo;

public class CGangJwaInfo {
	
	private EGangJwaInfo eGangJwaInfo;
	
	public CGangJwaInfo() {
		eGangJwaInfo = new EGangJwaInfo();
	}

	public VCGangJwaInfo getGangJwaInfo(String id) {//입력된 Student ID에 따라 테이블 정보 리턴
		VEGangJwaInfo vEPersonalInfo =this.eGangJwaInfo.getGangJwaInfo(id);
		
		VCGangJwaInfo vCGangJwaInfo = new VCGangJwaInfo();
		vCGangJwaInfo.setID(vEPersonalInfo.getID());
		vCGangJwaInfo.setBasketData(vEPersonalInfo.getBasketData());
		vCGangJwaInfo.setReasultData(vEPersonalInfo.getReasultData());
		vCGangJwaInfo.setdotsData(vEPersonalInfo.getdotsData());
		
		return vCGangJwaInfo;
	}

}
