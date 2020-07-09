package valueObject;

import java.util.Scanner;

public class VEGangJwaInfo { // 값을 전달하는 애
	private String id;
	private String basketData;
	private String reasultData;
	private String dotsData;

	public String getID() {return id;}
	public void setID(String id) {this.id = id;}

	public String getBasketData() {return basketData;}
	public void setBasketData(String basketData) {this.basketData = basketData;}

	public String getReasultData() {return reasultData;}
	public void setReasultData(String reasultData) {this.reasultData = reasultData;}

	public String getdotsData() {return dotsData;}
	public void setdotsData(String dotsData) {this.dotsData = dotsData;}
	
	public void readFromFile(Scanner sc) {
		this.id = sc.next();
		this.basketData = sc.next();
		this.reasultData = sc.next();
		this.dotsData = sc.next();
	}
}
