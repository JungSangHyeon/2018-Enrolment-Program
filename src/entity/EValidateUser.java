package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EValidateUser {


	@SuppressWarnings("resource")
	public boolean validateUser(String id, String name) {//txt���Ͽ� �־��� Student ID�� Name�� �ִ��� �˷���.
		boolean reasult = false;
		try {
			File f = new File("student/PersonalInfo");
			Scanner sc = new Scanner(f);
			while(sc.hasNext()) {
				if(id.equals(sc.next())&&name.equals(sc.next())) {reasult = true;}
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		
		return reasult;
	}

}
