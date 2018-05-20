import java.awt.Color;
import java.awt.Graphics;

public class Apple {
	 
	int x;
	int y;
	int r = 10;
	int yCentral ;
	int xCentral ;
	boolean eaten = false;
	
	public Apple(int x, int y) {
		this.x = x; 
		this.y = y;
		yCentral = y-(r/2);
				xCentral = x-(r/2);
	}
	
	
	public void draw(Graphics g) {
		g.setColor(Color.pink);
		  g.fillOval(xCentral,yCentral,r,r);
	}
	
	
	public boolean hitPac(Pacman pac) {
		if(pac.x == x || pac.x + pac.radius == x && pac.y == y || pac.y + pac.radius == y) {
			eaten = true;
			return true;
		}
		
		return false;
	}

}
