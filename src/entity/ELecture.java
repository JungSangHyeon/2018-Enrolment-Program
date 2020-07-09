package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VELecture;

public class ELecture {

	private Vector<VELecture> vELectures;

	public Vector<VELecture> getData(String fileName) {
		try {
			this.vELectures = new Vector<VELecture>();
			File f = new File("data/" + fileName);
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				VELecture vELecture = new VELecture();
				vELecture.readFromFile("",0,sc);
				vELectures.add(vELecture);
			}
			sc.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		return vELectures;
	}
}
