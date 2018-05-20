import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Tile implements GameWideConstants{
	
	int y;
	int x;
	int boardX;
	int boardY;
	Rectangle rect;
	
	
	
	public int[] getTile() {
		rect = new Rectangle(new Dimension(squareSize,squareSize));
		int[] returnThis = new int[2];
		returnThis[0] = boardY;
		returnThis[1] = boardX;
		return returnThis;
	}
	
	
}
