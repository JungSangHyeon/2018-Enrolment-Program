package valueObject;

public class VCSignUpInfo {
	
 private String nowID;
 private String id;
 private String pw;
 private boolean loginSuccess = false;
 
 public String getnowID() {return nowID;}
 public void setnowID(String nowID) {this.nowID = nowID;}
 
public String getID() {return id;}
public void setID(String id) {this.id = id;}

public String getPW() {return pw;}
public void setPW(String pw) {this.pw = pw;}
 
public boolean getLoginSuccess() {return loginSuccess;}
public void setLoginSuccess(boolean loginSuccess) {this.loginSuccess = loginSuccess;}
}
