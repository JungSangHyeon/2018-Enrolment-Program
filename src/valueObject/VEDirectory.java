package valueObject;

import java.util.Scanner;

public class VEDirectory {
	private String id;
	private String text;
	private String fileName;

	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
	public String getText() {return text;}
	public void setText(String text) {this.text = text;}
	
	public String getFileName() {return fileName;}
	public void setFileName(String fileName) {this.fileName = fileName;}

	public void readFromFile(Scanner sc) {
		this.id = sc.next();
		this.text = sc.next();
		this.fileName = sc.next();
	}

}
