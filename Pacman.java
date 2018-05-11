import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Pacman {
	
	Direction direction;
	int startAngle=0;
	int arcAngle=360;
	Boolean isIncreasing=true;
	int x=36;
	int y=36;
	Rectangle clone;
	
	 enum Direction{
			RIGHT, LEFT, UP, DOWN;
		}
	 
	public Pacman() {
		 clone= new Rectangle();
		 clone.setFrameFromCenter(x+15, y+15, x, y);
		
		
	}
	
	public  void draw(Graphics2D g){
			g.setColor(Color.DARK_GRAY);
			clone.setFrameFromCenter(x+13, y+15, x, y);
			g.draw(clone);
			
			g.drawArc(x,y,36,36,startAngle,arcAngle);
			g.setColor(Color.yellow);
			g.fillArc(x,y,36,36,startAngle,arcAngle);
	}

}
