package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import valueObject.VELecture;

public class EGangJwaFind {
	String id;
	VELecture realdata;
	
	public VELecture getGangJwaInfo(String string) {// 입력된 강좌 ID에 따라 테이블 정보 리턴
		this.id = string;
		realdata = new VELecture();
		return Operate("root");
	}

	public VELecture Operate(String fileName) {
		VELecture data;
		try {
			File f = new File("data/" + fileName);
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				String id = sc.next();
				if (id.length()!=4) {//4자리수
					sc.next();
					Operate(sc.next());
				} else {
					data = new VELecture();
					data.readFromFile(id,1,sc);
					if (this.id.equals(id)) {realdata = data;}
				}
			}} catch (FileNotFoundException e) {e.printStackTrace();}
		return realdata;
	}
}
