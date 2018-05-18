import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Ghost {
	
	Image Blinky;
	Image Pinky;
	Image Clyde;
	Image Inky;
	Pacman pacman;
	
	enum Direction{
		RIGHT,LEFT,UP,DOWN;
	}
	public Ghost(Pacman pacman) {
		this.pacman=pacman;
		try {
			
			Blinky=ImageIO.read(new File("Blinky.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			Pinky=ImageIO.read(new File("Pinky.png")).getScaledInstance(40, 40,Image.SCALE_SMOOTH);
			Clyde=ImageIO.read(new File("Clyde.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			Inky=ImageIO.read(new File("Inky.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public Boolean canMove(Ghost.Direction direction,Rectangle clone) {

		if (direction == Ghost.Direction.RIGHT) {
			for (Rectangle r : Board.wallList) {
				if (clone.intersects(r.getX() - 7, r.getY(), r.getWidth(), r.getHeight())) {
					return false;
				}
			}
		}
		if (direction == Ghost.Direction.LEFT) {
			for (Rectangle r : Board.wallList) {
				if (clone.intersects(r.getX() + 7, r.getY(), r.getWidth(), r.getHeight())) {
					return false;
				}
			}
		}
		if (direction == Ghost.Direction.UP) {
			for (Rectangle r : Board.wallList) {
				if (clone.intersects(r.getX(), r.getY() + 7, r.getWidth(), r.getHeight())) {
					return false;
				}
			}
		}
		if (direction == Ghost.Direction.DOWN) {
			for (Rectangle r : Board.wallList) {
				if (clone.intersects(r.getX(), r.getY() - 7, r.getWidth(), r.getHeight())) {
					return false;
				}
			}
		}

		return true;
	}

	
}
