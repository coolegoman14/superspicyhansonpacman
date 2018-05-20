import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wall extends Tile{


	Rectangle rect;
	
	public Wall (int x, int y) {
		rect = new Rectangle(new Dimension(squareSize, squareSize));
		rect.setLocation(x, y);
		
	}
	
	
	
	public void draw(Graphics2D g) {
		g.setColor(Color.CYAN.darker());
		//g.fillRect(x*37, i*37, 36, 36);
		g.draw(rect);
	}
	
	public String toString() {
		return x+ " " + y;
	}


	public int getX() {
		return rect.x;
	}

	public int getY() {
		return rect.y;
	}
	
	public double getWidth() {
		return rect.getWidth();
	}
	
	public double getHeight() {
		return rect.getHeight();
	}
	
}
