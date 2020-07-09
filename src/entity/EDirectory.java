package entity;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VEDirectory;

public class EDirectory {
	
	private Vector<VEDirectory> vEDirectories;//������ ������ �ִ°��� VEDirectory�ƴѰ���? 
	public EDirectory() {
       this.vEDirectories = new Vector<VEDirectory>();
	}

	public Vector<VEDirectory> getData(String filename) {
		try {
			this.vEDirectories.clear();
			File f = new File("data/"+filename);
			Scanner sc = new Scanner(f);
			while(sc.hasNext()) {
				VEDirectory vEDirectory = new VEDirectory();
				vEDirectory.readFromFile(sc); // ���θ� �𸣱⶧���� �ڽ��� ���� ��������. 
				this.vEDirectories.add(vEDirectory);
			}
			sc.close();
			return this.vEDirectories;
		} catch (FileNotFoundException e) {e.printStackTrace();}
		return null;
	}
}
