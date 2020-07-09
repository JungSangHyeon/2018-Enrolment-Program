package myAmazingThings;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class SuperField extends JPanel {
	private static final long serialVersionUID = 1L;

	int focusedHolderSize = 18;
	int errorSize = 12;
	int textAreaTextSize =20;
	int barWidth = 100;
	int barHeight = 2;
	int interval = 6;
	int status = 1;
	int time = 20;
	int cnt = 0;
	int errorNum = 1;
	
	Color normalChange = new Color(66,133,244);
	Color errorChange = new Color(213,0,0);
	Color basicColor = new Color(224,224,224);
	Color changeColor= normalChange;
	
	String placeHolder, errorMessage;
	
	public void error(int num) {
		if(num==1) {changeColor = normalChange;}
		else {changeColor = errorChange;}
		if(num==2) {status=3;}
		errorNum = num;
		repaint();
	}
	
	public SuperField(String placeHolderValue,String errorMessageValue, int barWidthValue) {
		this.errorMessage = errorMessageValue;
		this.placeHolder = placeHolderValue;
		this.barWidth = barWidthValue;
		this.setSize(barWidth, barHeight + textAreaTextSize + focusedHolderSize*2 + interval +errorSize);
		this.setLayout(null);
	}
	
	public void Animation(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(basicColor);
		g.fillRect(0, focusedHolderSize + interval * 2 + textAreaTextSize, barWidth, barHeight);// 얘는 무조건 그림. bar.
		g.setFont(new Font(null, Font.BOLD, focusedHolderSize));
		if (status == 1) {// 내용없을때 포커스 얻음
			g.setColor(changingColor(basicColor, changeColor));
			upText(g);
			g.setColor(changeColor);
			showBar(g);
		} else if (status == 2) {// 내용 없을때 포커스 잃음
			g.setColor(changingColor(changeColor, basicColor));
			downText(g);
			hideBar(g);
		} else if (status == 3) {// 내용 있을때 포커스 잃음
			g.setColor(changingColor(changeColor, basicColor));
			drawPlaceHolder(g);
			hideBar(g);
		} else if (status == 4) {// 내용 있을때 포커스 얻음
			g.setColor(changingColor(basicColor, changeColor));
			drawPlaceHolder(g);
			g.setColor(changeColor);
			showBar(g);
		} else if (status == 5) {// 리셋
			g.setColor(basicColor);
			g.drawString(placeHolder, 0, focusedHolderSize * 2);
			g.fillRect(0, focusedHolderSize + interval * 2 + textAreaTextSize, barWidth, barHeight);
		}
		if (errorNum != 1) {
			g.setColor(changeColor);
			g.setFont(new Font(null, Font.BOLD, errorSize));
			g.fillRect(0, focusedHolderSize + interval * 2 + textAreaTextSize, barWidth, barHeight);
			if (errorNum == 2) {// 뭐 안써서 일어난 에러
			g.drawString(errorMessage, 0, focusedHolderSize + interval * 2 + textAreaTextSize * 2);
			}
		}
	}
	
	public int changevalue(int input) {return input*100/time*cnt/100; }//100을 곱하고 나눠주는 이유는 수가 작아서 0 나와서이다.  그리고 해주면 더 정확해짐.//0~본래 값. cnt가 커짐에 따라.
	public void upText(Graphics g) {g.drawString(placeHolder, 0, focusedHolderSize*2-changevalue(focusedHolderSize)); }
	public void downText(Graphics g) {g.drawString(placeHolder, 0, focusedHolderSize+changevalue(focusedHolderSize));}
	public void showBar(Graphics g) {g.fillRect(barWidth/2-barWidth/time*cnt, focusedHolderSize + interval*2  + textAreaTextSize, barWidth/time*cnt*2, barHeight);}		
	public void hideBar(Graphics g) {g.fillRect(0, focusedHolderSize + interval*2  + textAreaTextSize, barWidth, barHeight);}
	public void drawPlaceHolder(Graphics g) {g.drawString(placeHolder, 0, focusedHolderSize);}
	public Color changingColor(Color start, Color end) {//changing은 cnt에따라 변하는 색임. start에서 서서히 end로 바꿈.
		return new Color(start.getRed() + changevalue(end.getRed() - start.getRed()),
				start.getGreen() + changevalue(end.getGreen() - start.getGreen()),
				start.getBlue() + changevalue(end.getBlue() - start.getBlue()));
	}

	public String makePassword(JPasswordField jPasswordField) {//char[]로 리턴이 되서 필요함.
 		String reasult = "";
 		for(char cha : jPasswordField.getPassword()) {reasult+=cha;}
 		return reasult;
 	}
}
