package control;

import entity.ESIDPersonalInfo;
import valueObject.VCPersonalInfo;
import valueObject.VEPersonalInfo;

public class CSIDPersonalInfo {
	
	private ESIDPersonalInfo ePersonalInfo;
	
	public CSIDPersonalInfo() {
		 ePersonalInfo = new ESIDPersonalInfo();
	}

	public VCPersonalInfo getPersonalInfo(String id) {//입력된 Student ID에 따라 개인정보를 리턴
		//get data from entity
		VEPersonalInfo vEPersonalInfo =this.ePersonalInfo.getPersonalInfo(id);
		
		//create value object and set data from entity value object
		VCPersonalInfo vCPersonalInfo = new VCPersonalInfo();
		vCPersonalInfo.setId(vEPersonalInfo.getId());
		vCPersonalInfo.setName(vEPersonalInfo.getName());
		
		return vCPersonalInfo;
	}

}
