package control;

import java.util.Vector;

import entity.ELecture;
import valueObject.VCLecture;
import valueObject.VELecture;

public class CLecture {
	
	private ELecture eLecture;
	private Vector<VCLecture> vCLectures;
	
	public CLecture() {
      this.eLecture = new ELecture();
	}

	public Vector<VCLecture> getData(String filename) {
		Vector<VELecture> vELectures = this.eLecture.getData(filename);
		this.vCLectures = new Vector<VCLecture>();
		
		for(VELecture ve : vELectures) {
			VCLecture vCLecture = new VCLecture();
			vCLecture.setId(ve.getId());
			vCLecture.setName(ve.getName());
			vCLecture.setProfessorName(ve.getProfessorName());
			vCLecture.setCredit(ve.getCredit());
			vCLecture.setTime(ve.getTime());
			this.vCLectures.add(vCLecture);
		}
		return this.vCLectures;
	}
}
