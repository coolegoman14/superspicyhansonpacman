import java.awt.Color;
import java.awt.Graphics2D;

public class Pacman {
	
	Direction direction=Direction.RIGHT;
	int startAngle=0;
	int arcAngle=360;
	Boolean isIncreasing=true;
	int x;
	int y;
	
	 enum Direction{
			RIGHT, LEFT, UP, DOWN;
		}
	 
	public Pacman() {
	}
	
	
	
	public  void draw(Graphics2D g){
		g.setColor(Color.black);
		g.drawArc(x,y,50,50,startAngle,arcAngle);
		g.setColor(Color.yellow);
		g.fillArc(x,y,50,50,startAngle,arcAngle);
		
	}
}
