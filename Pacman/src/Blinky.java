import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Blinky extends Ghost {
	int x=36;
	int y=36;
	

	public Blinky(Pacman pacman) {
		super(pacman);
		
		
	}
	
	public void draw(Graphics2D g){
		move();
		System.out.println("clone coordinates"  + clone.getX() + " " + clone.getY() + " " + clone.getMaxX() + " " + clone.getMaxY());
		super.moveRect(x, y);
		g.setColor(Color.ORANGE);
		g.draw(clone);
	
		g.drawImage(Blinky, x, y, null);
		
		

	}
	
	public void setSpot(int x, int y ) {
		super.moveRect(x, y);
		this.x = x;
		this.y = y;
		
	}
	
	
	

	
	
	
	public void move() {
		int yAway = pacman.y - this.y;
		int xAway = pacman.x - this.x;
	
		boolean madeMove = false;
		
		
		
		
		if(Math.abs(xAway) >= Math.abs(yAway)) {
			System.out.println("tryna go horizonally");
			if(xAway < 0 && canMove(-5, 0)) {
				System.out.println("tryna go left");
				x-= speed;
				madeMove = true;
				
			}
			if(xAway > 0 && canMove(5, 0)) {
				System.out.println("tryna go right");
				//System.out.println("attempted to go to right " + canMove(clone.x+5, clone.y) + " " + clone.x);
				x+= speed;
				madeMove = true;
			}
		}

		if(Math.abs(xAway) < Math.abs(yAway)) {
			System.out.println("tryna go vertically");
			if(yAway < 0 && canMove(0, -5)) {
				System.out.println("tryna go up");
				y-= speed;
				madeMove = true;
				
			}
			if(yAway > 0 && canMove(0, +5)) {
				System.out.println("tryna go down");
				y+= speed;
				madeMove = true;
			}

		}
		if(Math.abs(xAway) >= Math.abs(yAway) || madeMove == false) {
			System.out.println("tryna go horizonally");
			if(xAway < 0 && canMove(-5, 0)) {
				System.out.println("tryna go left");
				x-= speed;
				madeMove = true;
				
			}
			if(xAway > 0 && canMove(5, 0)) {
				System.out.println("tryna go right");
				//System.out.println("attempted to go to right " + canMove(clone.x+5, clone.y) + " " + clone.x);
				x+= speed;
				madeMove = true;
			}
		}
		if(Math.abs(xAway) < Math.abs(yAway) || madeMove == false) {
			System.out.println("tryna go vertically");
			if(yAway < 0 && canMove(0, -5)) {
				System.out.println("tryna go up");
				y-= speed;
				madeMove = true;
				
			}
			if(yAway > 0 && canMove(0, +5)) {
				System.out.println("tryna go down");
				y+= speed;
				madeMove = true;
			}

		}
		//if(!madeMove)
		//anyMove();
		
	}
	
	
	public void anyMove() {
		System.out.println("any move called");
		if(canMove(-5,0))
			x-= speed;
		
		if(canMove(5,0)) 
			x+= speed;
		
		
		if(canMove(0,-5))
			y-= speed;
		
		if(canMove(0,5))
			y+=speed;
		}
	
	

	
	
	public int[] targetTile() {
		int[] target = new int[2];
		target[0] = pacman.x/squareSize;
		target[1] = pacman.y/squareSize;
		return target;
	}
}