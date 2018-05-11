import java.awt.Graphics2D;
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
	
	public Ghost(Pacman pacman) {
		this.pacman=pacman;
		try {
			
			Blinky=ImageIO.read(new File("Blinky.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			Pinky=ImageIO.read(new File("Pinky.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			Clyde=ImageIO.read(new File("Clyde.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			Inky=ImageIO.read(new File("Inky.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void draw(Graphics2D g){
		
	}
	
	
}
