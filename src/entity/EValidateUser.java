package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EValidateUser {


	@SuppressWarnings("resource")
	public boolean validateUser(String id, String name) {//txt파일에 주어진 Student ID와 Name이 있는지 알려줌.
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
