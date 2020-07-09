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
		g.fillRect(0, focusedHolderSize + interval * 2 + textAreaTextSize, barWidth, barHeight);// ��� ������ �׸�. bar.
		g.setFont(new Font(null, Font.BOLD, focusedHolderSize));
		if (status == 1) {// ��������� ��Ŀ�� ����
			g.setColor(changingColor(basicColor, changeColor));
			upText(g);
			g.setColor(changeColor);
			showBar(g);
		} else if (status == 2) {// ���� ������ ��Ŀ�� ����
			g.setColor(changingColor(changeColor, basicColor));
			downText(g);
			hideBar(g);
		} else if (status == 3) {// ���� ������ ��Ŀ�� ����
			g.setColor(changingColor(changeColor, basicColor));
			drawPlaceHolder(g);
			hideBar(g);
		} else if (status == 4) {// ���� ������ ��Ŀ�� ����
			g.setColor(changingColor(basicColor, changeColor));
			drawPlaceHolder(g);
			g.setColor(changeColor);
			showBar(g);
		} else if (status == 5) {// ����
			g.setColor(basicColor);
			g.drawString(placeHolder, 0, focusedHolderSize * 2);
			g.fillRect(0, focusedHolderSize + interval * 2 + textAreaTextSize, barWidth, barHeight);
		}
		if (errorNum != 1) {
			g.setColor(changeColor);
			g.setFont(new Font(null, Font.BOLD, errorSize));
			g.fillRect(0, focusedHolderSize + interval * 2 + textAreaTextSize, barWidth, barHeight);
			if (errorNum == 2) {// �� �ȽἭ �Ͼ ����
			g.drawString(errorMessage, 0, focusedHolderSize + interval * 2 + textAreaTextSize * 2);
			}
		}
	}
	
	public int changevalue(int input) {return input*100/time*cnt/100; }//100�� ���ϰ� �����ִ� ������ ���� �۾Ƽ� 0 ���ͼ��̴�.  �׸��� ���ָ� �� ��Ȯ����.//0~���� ��. cnt�� Ŀ���� ����.
	public void upText(Graphics g) {g.drawString(placeHolder, 0, focusedHolderSize*2-changevalue(focusedHolderSize)); }
	public void downText(Graphics g) {g.drawString(placeHolder, 0, focusedHolderSize+changevalue(focusedHolderSize));}
	public void showBar(Graphics g) {g.fillRect(barWidth/2-barWidth/time*cnt, focusedHolderSize + interval*2  + textAreaTextSize, barWidth/time*cnt*2, barHeight);}		
	public void hideBar(Graphics g) {g.fillRect(0, focusedHolderSize + interval*2  + textAreaTextSize, barWidth, barHeight);}
	public void drawPlaceHolder(Graphics g) {g.drawString(placeHolder, 0, focusedHolderSize);}
	public Color changingColor(Color start, Color end) {//changing�� cnt������ ���ϴ� ����. start���� ������ end�� �ٲ�.
		return new Color(start.getRed() + changevalue(end.getRed() - start.getRed()),
				start.getGreen() + changevalue(end.getGreen() - start.getGreen()),
				start.getBlue() + changevalue(end.getBlue() - start.getBlue()));
	}

	public String makePassword(JPasswordField jPasswordField) {//char[]�� ������ �Ǽ� �ʿ���.
 		String reasult = "";
 		for(char cha : jPasswordField.getPassword()) {reasult+=cha;}
 		return reasult;
 	}
}
