import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pinky extends Ghost {

	public Pinky(Pacman pacman) {
		super(pacman);
		// TODO Auto-generated constructor stub
	}
	
	int x=36;
	int y=36;


	
	public void draw(Graphics2D g){
		move();
		g.drawImage(Pinky, x, y, null);

	}
	

	
	
	public int[] target () {
		
		
		
		int[] target = new int[2];
		
		String direction = pacman.direction;
		
		int fourSpaces=  4*squareSize;
		
		while(target[0] == 0) {
		
		if(direction.equals("right") && canMove(pacman.x + fourSpaces, pacman.y)) {
			
			
			target[0] = (pacman.x + fourSpaces)/squareSize;
			target[1] = (pacman.y)/squareSize;
			
		}

		if(direction.equals("left") && canMove(pacman.x - fourSpaces, pacman.y)) {
			target[0] = (pacman.x - fourSpaces)/squareSize;
			target[1] = (pacman.y)/squareSize;
		}

		if(direction.equals("up") && canMove(pacman.x , pacman.y - fourSpaces)) {
			target[0] = (pacman.x)/squareSize;
			target[1] = (pacman.y - fourSpaces)/squareSize;
		}

		if(direction.equals("down")  && canMove(pacman.x , pacman.y + fourSpaces)) {
			target[0] = (pacman.x)/squareSize;
			target[1] = (pacman.y + fourSpaces)/squareSize;
		}
		}
	//	System.out.println("target for p is " + target[0] + " " + target[1]);
		return target;
	}
	
	
	public void move() {
		int yAway = target()[1] - this.y;
		int xAway = target()[0] - this.x;
boolean madeMove = false;
		
		if(Math.abs(xAway) >= Math.abs(yAway)  ) {
			if(xAway < 0 && canMove(this.x, this.y)) {
				x-= speed;
				madeMove = true;
				
			}
			if(xAway > 0 && canMove(this.x, this.y)) {
				x+= speed;
				madeMove = true;
			}
		}

		if(Math.abs(xAway) < Math.abs(yAway) || madeMove == false) {
			
			if(yAway < 0 && canMove(this.x, this.y)) {
				y-= speed;
				madeMove = true;
				
			}
			if(yAway > 0 && canMove(this.x , this.y)) {
				y+= speed;
				madeMove = true;
			}

		}
	}
	


	public void setX(int x ) {
		this.x = x;
	}
	
	
	public void setY(int y ) {
		this.y = y ;
	}
	
	

}
