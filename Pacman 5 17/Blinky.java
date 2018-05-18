import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Blinky extends Ghost{
		
	
	int x=36;
	int y=36;
	final int speed=3;
	Rectangle clone;
	Direction direction=Ghost.Direction.RIGHT;
	Direction futureDirection;

	public Blinky(Pacman pacman) {
		super(pacman);
		clone= new Rectangle();
	}
	
	public void draw(Graphics2D g){
		
		
		move();
		clone.setFrameFromCenter(x+16, y+16, x, y);
		g.setColor(Color.DARK_GRAY);
		//g.draw(clone);
		g.drawImage(Blinky, x-5, y-3, null);
	}
	
	public void move() {
		if(x!=pacman.x){
			if(x<pacman.x && canMove(Ghost.Direction.RIGHT,clone)){
				x+=speed;
			}
			else if(x>pacman.x && canMove(Ghost.Direction.LEFT,clone)){
				x-=speed;
			}
		}
		if(y!=pacman.y){
			if(y<pacman.y && canMove(Ghost.Direction.DOWN,clone)){
				y+=speed;
			}
			else if(y>pacman.y && canMove(Ghost.Direction.UP,clone)){
				y-=speed;
			}
		}
	}
	
	}

