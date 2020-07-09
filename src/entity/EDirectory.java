package entity;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import valueObject.VEDirectory;

public class EDirectory {
	
	private Vector<VEDirectory> vEDirectories;//정보를 전달해 주는것이 VEDirectory아닌가요? 
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
				vEDirectory.readFromFile(sc); // 내부를 모르기때문에 자식이 직접 읽으라함. 
				this.vEDirectories.add(vEDirectory);
			}
			sc.close();
			return this.vEDirectories;
		} catch (FileNotFoundException e) {e.printStackTrace();}
		return null;
	}
}
