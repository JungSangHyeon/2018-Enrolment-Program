package myAmazingThings;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MovingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public int f_width, f_height; 
	int border = 10;
	int grabGap = 20;
	int minimum_w, minimum_h; 
	Point mouseClickedLocation = new Point(0, 0);
	boolean up, down, right, left;
	protected boolean DoNotResize = false;
	
	public MovingPanel(int mini_w, int mini_h, String borderTitle) {
		this.minimum_w  = mini_w;
		this.minimum_h  = mini_h;
		
		MouseMotionHandler mouseMotionHandler = new MouseMotionHandler();
		ComponentHandler componentHandler = new ComponentHandler();
		MouseHandler mouseHandler = new MouseHandler();
		
		this.addMouseMotionListener(mouseMotionHandler);
		this.addComponentListener(componentHandler);
		this.addMouseListener(mouseHandler);
		
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//없으면 터진다.(레이아웃)
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), borderTitle, TitledBorder.LEFT, TitledBorder.TOP));
	}
	 
	class MouseMotionHandler implements MouseMotionListener{
		public void mouseDragged(MouseEvent e) {
				locationBoolean(mouseClickedLocation.x, mouseClickedLocation.y);//왼쪽이랑 위쪽은 프레임처럼 안되는 구만. 삭제삭제
				if(!DoNotResize) {
				if (down) {f_height = f_height + e.getY() - mouseClickedLocation.y; mouseClickedLocation.y = e.getY();}
				if (right) {f_width = f_width + e.getX() - mouseClickedLocation.x; mouseClickedLocation.x = e.getX();}
				}
				if(f_width<minimum_w) {f_width=minimum_w;}
				if(f_height<minimum_h) {f_height=minimum_h;}
				setSize(f_width, f_height);//프레임을 기준으로 하게는 못하냥 --> 요고 밖의 좌표를 가져와야 한다.
				if (border <= mouseClickedLocation.x && mouseClickedLocation.x <= f_width - border && border <= mouseClickedLocation.y && mouseClickedLocation.y <= border + grabGap) {
					setLocation(e.getLocationOnScreen().x - mouseClickedLocation.x, e.getLocationOnScreen().y - mouseClickedLocation.y - 20);
			    }
		}

		public void mouseMoved(MouseEvent e) {
			    if(!DoNotResize) {
				locationBoolean(e.getX(),e.getY());
				if ((up&&right)||(down&&left)) {setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));}
				else if ((up&&left)||(down&&right)) {setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));}
				else if (up||down) {setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));}
				else if (left||right) {setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));}
				else if (border <= mouseClickedLocation.x && mouseClickedLocation.x <= f_width - border && border <= mouseClickedLocation.y && mouseClickedLocation.y <= border + grabGap) {
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
				else {setCursor(new Cursor(Cursor.DEFAULT_CURSOR));}
			}
		}		
	}

	public void locationBoolean(int x, int y) {//왼,위 삭제
		 down = 0 <= x && x <= f_width && f_height - border <= y && y<= f_height;
		 right = f_width - border <= x && x <= f_width && 0 <= y && y <= f_height;
	}
	
	class MouseHandler implements MouseListener{
		public void mousePressed(MouseEvent e) {
			mouseClickedLocation.x = e.getX();
			mouseClickedLocation.y = e.getY();
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseReleased(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
	}

	class ComponentHandler implements ComponentListener{
		public void componentResized(ComponentEvent e) {reSized();}
		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
	}
	
	public void reSized() {
		f_width = this.getWidth();
		f_height = this.getHeight();
	}
}
