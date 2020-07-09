package control;

import entity.EGangJwaInfo;
import valueObject.VCGangJwaInfo;
import valueObject.VEGangJwaInfo;

public class CGangJwaInfo {
	
	private EGangJwaInfo eGangJwaInfo;
	
	public CGangJwaInfo() {
		eGangJwaInfo = new EGangJwaInfo();
	}

	public VCGangJwaInfo getGangJwaInfo(String id) {//�Էµ� Student ID�� ���� ���̺� ���� ����
		VEGangJwaInfo vEPersonalInfo =this.eGangJwaInfo.getGangJwaInfo(id);
		
		VCGangJwaInfo vCGangJwaInfo = new VCGangJwaInfo();
		vCGangJwaInfo.setID(vEPersonalInfo.getID());
		vCGangJwaInfo.setBasketData(vEPersonalInfo.getBasketData());
		vCGangJwaInfo.setReasultData(vEPersonalInfo.getReasultData());
		vCGangJwaInfo.setdotsData(vEPersonalInfo.getdotsData());
		
		return vCGangJwaInfo;
	}

}
