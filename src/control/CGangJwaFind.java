package control;

import entity.EGangJwaFind;
import valueObject.VCLecture;
import valueObject.VELecture;

public class CGangJwaFind {
	
	private EGangJwaFind eGangJwaFind;
	
	public CGangJwaFind() {
		eGangJwaFind = new EGangJwaFind();
	}

	public VCLecture getGangJwaInfo(String string) {//�Էµ� ���� ID�� ���� ���̺� ���� ����
		VELecture vELecture =this.eGangJwaFind.getGangJwaInfo(string);
		if(vELecture!=null) {
		VCLecture vCLecture = new VCLecture();
		vCLecture.setId(vELecture.getId());
		vCLecture.setName(vELecture.getName());
		vCLecture.setProfessorName(vELecture.getProfessorName());
		vCLecture.setCredit(vELecture.getCredit());
		vCLecture.setTime(vELecture.getTime());
		return vCLecture;
		}else {
		return null;
		}
	}

}
