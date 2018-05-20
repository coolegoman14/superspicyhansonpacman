import java.awt.Color;
import java.awt.Graphics;

public class Path  extends Tile implements GameWideConstants{
	
	
	
	public Path(int x, int y) {
		
			this.x = x;
			this.y = y;
			boardX = x/squareSize;
			boardY = y/squareSize;
		

	}

	
	
	
	public void draw(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(x, y, 19, 19);
	}
	
	public String toString() {
		return "Path";
	}
}
