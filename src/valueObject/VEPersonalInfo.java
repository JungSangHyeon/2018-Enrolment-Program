package valueObject;

import java.util.Scanner;

public class VEPersonalInfo { // 값을 전달하는 애
 private String id;
 private String name;
 
public String getId() {return id;}
public void setId(String id) {this.id = id;}

public String getName() {return name;}
public void setName(String name) {this.name = name;}


public void readFromFile(Scanner sc) {
	this.id = sc.next();
	this.name = sc.next();
}
 
}
