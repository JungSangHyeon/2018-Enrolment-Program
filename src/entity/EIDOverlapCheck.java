package entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EIDOverlapCheck {


	@SuppressWarnings("resource")
	public boolean idOverlapCheck(String signUpID) {//txt���Ͽ� �־��� ID�� �ִ��� �˷���.
		boolean reasult = false;
		try {
			File f = new File("student/login");
			Scanner sc = new Scanner(f);
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				Scanner sc2 = new Scanner(line);
				String id = sc2.next();
				if(signUpID.equals(id)) {
					reasult = true;
				}
			}
		} catch (FileNotFoundException e) {e.printStackTrace();}
		return reasult;
	}

}
