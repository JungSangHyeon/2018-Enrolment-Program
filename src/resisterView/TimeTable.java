package resisterView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JFrame;

import valueObject.VCLecture;

@SuppressWarnings("serial")
public class TimeTable extends JFrame {//여긴 드러워요

	Vector<VCLecture> data;
    Vector<Color> randomColor;
    TimeStuff timeStuff;
    
    int frameWidth = 500;
    int frameHeight = 800;
    int blackStartX = 30;
	int blackStartY = 30;
	int blackWidth = frameWidth - blackStartX*2;
	int blackHeight = frameHeight - blackStartY*2;
	char[] days = { '월', '화', '수', '목','금' };
	int dayLineHeight = 100;
	int dayFontSIze = 40;
	int normalFontSIze = 12;
	int timeTableStartY = blackStartY + dayLineHeight;
	int gap = blackHeight - timeTableStartY;
	int leastTime = 540;//9시. 9*60
	int bestTime = 1200;//20시. 20*60
	int milionGap = gap*10000 / (bestTime - leastTime);//하도 안써서 double이 있다는 것을 까먹었어요. 생각해 낸 후엔 늦었더라구요.
	int timeSymbol =9;
	int rectWidth = blackWidth / days.length;// 시간표네모의 가로길이.
	int rectStartX = 0;
	int colorControl =0;
	
	public TimeTable(String title) {
		super(title);
		timeStuff = new TimeStuff();
		data = new Vector<VCLecture>();//이쁜 색들.
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
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("명지대마크.png"));
		this.setSize(frameWidth, frameHeight);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);//refresh비스무리
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//줄
		g.setColor(new Color(	185, 185, 185));
		g.drawRect(blackStartX, blackStartY, blackWidth, blackHeight);// 기본 네모
		g.drawLine(blackStartX, dayLineHeight-1, blackStartX + blackWidth, dayLineHeight-1);// 요일 쓰는칸 줄
		
		//세로줄, 요일
		g.setFont(new Font(null, Font.BOLD, dayFontSIze));
		for (int i = 0; i < days.length; i++) {
			g.drawLine(blackStartX + blackWidth / days.length * i, blackStartY, blackStartX + blackWidth / days.length * i,blackStartY + blackHeight);// 세로줄
			g.setColor(new Color(	141, 141, 141));
			g.drawString(""+days[i], blackStartX + blackWidth / days.length * i+23, blackStartY+50);//요일
			g.setColor(new Color(	185, 185, 185));
		}
		
		//가로줄, 시간
		timeSymbol=9;
		g.setFont(new Font(null, Font.PLAIN, normalFontSIze));
		for(int q= 0; q<(bestTime-leastTime)/60;q++) {
			g.drawLine(blackStartX, timeTableStartY+(milionGap/100*60*q)/100+25, blackStartX+blackWidth, timeTableStartY+(milionGap/100*60*q)/100+25);//가로줄
			g.drawString(String.valueOf(timeSymbol), blackStartX-15, timeTableStartY+(milionGap/100*60*(q-1))/100+30);//시간
			timeSymbol++;//시간증가
		}
		
		//네모
		colorControl =0;
		for (VCLecture nowData : data) {
			Vector<String> timeThing = this.timeStuff.Time(nowData.getTime());
			for (int i = 0; i < timeThing.get(0).length(); i++) {// 요일 개수만큼 돈다. //월수금덩어리줌
				char day = timeThing.get(0).charAt(i);//요일하나
				for (int v = 0; v < days.length; v++) {if (days[v]==day) {rectStartX = blackStartX + rectWidth * (v);}}//요일로startx 정함.
				int startTime = this.timeStuff.intMakeTime(timeThing.get(timeThing.size() - 2));//한줄로 만들어 버릴까
				int startY = timeTableStartY + (milionGap/100*startTime)/100;
				int endTime = this.timeStuff.intMakeTime(timeThing.get(timeThing.size() - 1));
				int endY = timeTableStartY + (milionGap/100 * endTime)/100;
				int rectHeight = endY - startY;
				g.setColor(randomColor.get(colorControl));//네모 그리기
				g.fillRect(rectStartX+1, startY+ timeTableStartY-656, rectWidth-1, rectHeight);
				
				//내용
				g.setColor(Color.WHITE);
				String name = nowData.getName();//드-러운 글짜짜르기. 으엑.
				String btyTime =  this.timeStuff.timeBeautifier(nowData.getTime());
				int writeNum = 5 + (7 - days.length);
				if(name.length()>writeNum) {
					String temp = "";
					for(int w=0; w<writeNum; w++) {temp+=name.charAt(w);}
					g.drawString(temp, rectStartX+3, startY+ timeTableStartY-641);
					temp = "";
					for(int w=0; w<name.length()-writeNum; w++) {temp+=name.charAt(writeNum+w);}//두줄이면 되겠지.
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
