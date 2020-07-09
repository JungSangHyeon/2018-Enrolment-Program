package valueObject;

import java.util.Scanner;

public class VELecture {
	private int id;
	private String name;
	private String professorName;
	private int credit;
	private String time;
	
	public int getId() { return id;	}
	public void setId(int id) {	this.id = id; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getProfessorName() { return professorName; }
	public void setProfessorName(String professorName) { this.professorName = professorName; }

	public int getCredit() {return credit;}
	public void setCredit(int credit) {this.credit = credit;}
	
	public String getTime() {return time;}
	public void setTime(String time) {this.time = time;}
	
	public void readFromFile(String id, int doit, Scanner sc) {
		if(doit==0) {this.id = sc.nextInt();}
		else {this.id = Integer.parseInt(id);}
		this.name = sc.next();
		this.professorName = sc.next();
		this.credit = sc.nextInt();
		this.time = sc.next();
	}
}
