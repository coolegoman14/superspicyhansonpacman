import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ghost implements GameWideConstants{
	
	int speed = 5;
	int x = 50;
	int y = 50;
	
	Image Blinky;
	Image Pinky;
	Image Clyde;
	Image Inky;
	Pacman pacman;
	Rectangle clone;
	
	public Ghost(Pacman pacman) {
		
		this.pacman=pacman;
		clone= new Rectangle(new Dimension(19,19));
		moveRect(x, y);
		try {
			
			String name = "img/Blinky.png";
			
			Blinky=ImageIO.read(this.getClass().getResource(name)).getScaledInstance(19, 19,Image.SCALE_SMOOTH);			
			
			name = "img/Pinky.png";
			
			Pinky=ImageIO.read(this.getClass().getResource(name)).getScaledInstance(19, 19,Image.SCALE_SMOOTH);
			
			name = "img/Clyde.png";
			
			Clyde=ImageIO.read(this.getClass().getResource(name)).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			
			name = "img/Inky.png";
			
			Inky=ImageIO.read(this.getClass().getResource(name)).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void moveRect(int x, int y) {
		System.out.println("move rect was called");
		clone.setLocation(x, y);
	}
	
	public Boolean canMove(int x,int y){
		for(Wall r: Map.wallList){
			int tryX = r.getX()-x;
			int tryY = r.getY()-y;
			//
			if(tryX > 0 && tryY > 0) {
			if(this.clone.intersects(tryX, tryY, r.getWidth(), r.getHeight())){
				System.out.println( "Stopped by wall at "  +tryX + " " +  tryY + " " + (tryX + r.getWidth()) + " " + (tryY + r.getHeight()));
				
				//System.out.println("can move from blinky was false " + r + " " + r.getTile()[0] + " " +r.getTile()[1]);
				return false;
		}
			}
	}
		
	return true;
}
	

}
