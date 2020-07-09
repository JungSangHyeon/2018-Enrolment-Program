package resisterView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JFrame;

import valueObject.VCLecture;

@SuppressWarnings("serial")
public class TimeTable extends JFrame {//���� �巯����

	Vector<VCLecture> data;
    Vector<Color> randomColor;
    TimeStuff timeStuff;
    
    int frameWidth = 500;
    int frameHeight = 800;
    int blackStartX = 30;
	int blackStartY = 30;
	int blackWidth = frameWidth - blackStartX*2;
	int blackHeight = frameHeight - blackStartY*2;
	char[] days = { '��', 'ȭ', '��', '��','��' };
	int dayLineHeight = 100;
	int dayFontSIze = 40;
	int normalFontSIze = 12;
	int timeTableStartY = blackStartY + dayLineHeight;
	int gap = blackHeight - timeTableStartY;
	int leastTime = 540;//9��. 9*60
	int bestTime = 1200;//20��. 20*60
	int milionGap = gap*10000 / (bestTime - leastTime);//�ϵ� �ȽἭ double�� �ִٴ� ���� ��Ծ����. ������ �� �Ŀ� �ʾ����󱸿�.
	int timeSymbol =9;
	int rectWidth = blackWidth / days.length;// �ð�ǥ�׸��� ���α���.
	int rectStartX = 0;
	int colorControl =0;
	
	public TimeTable(String title) {
		super(title);
		timeStuff = new TimeStuff();
		data = new Vector<VCLecture>();//�̻� ����.
		randomColor = new Vector<Color>();
		randomColor.add(new Color(240, 134, 118));
		randomColor.add(new Color(251, 170, 104));
		randomColor.add(new Color(236, 195, 105));
		randomColor.add(new Color(167, 201, 114));
		randomColor.add(new Color(120, 202, 136));
		randomColor.add(new Color(125, 209, 193));
		randomColor.add(new Color(122, 165, 233));
		randomColor.add(new Color(159, 134, 225));
		randomColor.add(new Color(211, 151, 237));
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("�����븶ũ.png"));
		this.setSize(frameWidth, frameHeight);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);//refresh�񽺹���
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//��
		g.setColor(new Color(	185, 185, 185));
		g.drawRect(blackStartX, blackStartY, blackWidth, blackHeight);// �⺻ �׸�
		g.drawLine(blackStartX, dayLineHeight-1, blackStartX + blackWidth, dayLineHeight-1);// ���� ����ĭ ��
		
		//������, ����
		g.setFont(new Font(null, Font.BOLD, dayFontSIze));
		for (int i = 0; i < days.length; i++) {
			g.drawLine(blackStartX + blackWidth / days.length * i, blackStartY, blackStartX + blackWidth / days.length * i,blackStartY + blackHeight);// ������
			g.setColor(new Color(	141, 141, 141));
			g.drawString(""+days[i], blackStartX + blackWidth / days.length * i+23, blackStartY+50);//����
			g.setColor(new Color(	185, 185, 185));
		}
		
		//������, �ð�
		timeSymbol=9;
		g.setFont(new Font(null, Font.PLAIN, normalFontSIze));
		for(int q= 0; q<(bestTime-leastTime)/60;q++) {
			g.drawLine(blackStartX, timeTableStartY+(milionGap/100*60*q)/100+25, blackStartX+blackWidth, timeTableStartY+(milionGap/100*60*q)/100+25);//������
			g.drawString(String.valueOf(timeSymbol), blackStartX-15, timeTableStartY+(milionGap/100*60*(q-1))/100+30);//�ð�
			timeSymbol++;//�ð�����
		}
		
		//�׸�
		colorControl =0;
		for (VCLecture nowData : data) {
			Vector<String> timeThing = this.timeStuff.Time(nowData.getTime());
			for (int i = 0; i < timeThing.get(0).length(); i++) {// ���� ������ŭ ����. //�����ݵ����
				char day = timeThing.get(0).charAt(i);//�����ϳ�
				for (int v = 0; v < days.length; v++) {if (days[v]==day) {rectStartX = blackStartX + rectWidth * (v);}}//���Ϸ�startx ����.
				int startTime = this.timeStuff.intMakeTime(timeThing.get(timeThing.size() - 2));//���ٷ� ����� ������
				int startY = timeTableStartY + (milionGap/100*startTime)/100;
				int endTime = this.timeStuff.intMakeTime(timeThing.get(timeThing.size() - 1));
				int endY = timeTableStartY + (milionGap/100 * endTime)/100;
				int rectHeight = endY - startY;
				g.setColor(randomColor.get(colorControl));//�׸� �׸���
				g.fillRect(rectStartX+1, startY+ timeTableStartY-656, rectWidth-1, rectHeight);
				
				//����
				g.setColor(Color.WHITE);
				String name = nowData.getName();//��-���� ��¥¥����. ����.
				String btyTime =  this.timeStuff.timeBeautifier(nowData.getTime());
				int writeNum = 5 + (7 - days.length);
				if(name.length()>writeNum) {
					String temp = "";
					for(int w=0; w<writeNum; w++) {temp+=name.charAt(w);}
					g.drawString(temp, rectStartX+3, startY+ timeTableStartY-641);
					temp = "";
					for(int w=0; w<name.length()-writeNum; w++) {temp+=name.charAt(writeNum+w);}//�����̸� �ǰ���.
					g.drawString(temp, rectStartX+3, startY+ timeTableStartY-628);
					g.drawString(btyTime, rectStartX+3, startY+ timeTableStartY-612);
				}else {
					g.drawString(name, rectStartX+3, startY+ timeTableStartY-641);
					g.drawString(btyTime, rectStartX+3, startY+ timeTableStartY-627);
				}
			}
			colorControl++;
			if(colorControl>randomColor.size()) {colorControl=0;}
		}
	}

	public void refreshData(Vector<VCLecture> data) {this.data = data;}
}
