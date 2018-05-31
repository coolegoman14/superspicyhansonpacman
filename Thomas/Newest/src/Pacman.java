import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pacman {
	
	static Direction direction=Pacman.Direction.RIGHT;
	Direction userDirection;
	Boolean isIncreasing=true;
	int startAngle=0;
	int arcAngle=360;
	int maxStartAngle=70;
	int minStartAngle=290;
	static int centerX=Board.TILE_D*14+Board.TILE_D/2;
	static int centerY=Board.TILE_D*17+Board.TILE_D/2;
	int speed=6;
	static int coins;
	Rectangle clone;
	
	 enum Direction{
			RIGHT, LEFT, UP, DOWN;
		}
	 
	public Pacman() {
		 clone= new Rectangle(new Dimension(50,50));
		 

	}
	
	public  void draw(Graphics2D g){
			spotAt();
			g.setColor(Color.yellow);
			g.drawArc(centerX-15,centerY-17,35,35,startAngle,arcAngle);
			g.fillArc(centerX-15,centerY-17,35,35,startAngle,arcAngle);
			clone.setLocation(centerX-20, centerY-20);
			g.setColor(Color.CYAN);
		
			g.draw(clone);
	}
	
	public void doMouthAnimation() {
		if (startAngle == maxStartAngle) {
			isIncreasing = false;
		} else if (startAngle == minStartAngle) {
			isIncreasing = true;
		}

		if (isIncreasing) {
			startAngle += 10;
			arcAngle -= 20;
		} else {
			startAngle -= 10;
			arcAngle += 20;
		}
	}

	public void move() {
		if (direction == Pacman.Direction.RIGHT) {
			if (canMove(direction)) {
				centerX +=speed;
			}
		}

		if (direction == Pacman.Direction.LEFT) {
			if (canMove(direction)) {
				centerX-=speed;
			}

		}

		if (direction == Pacman.Direction.UP) {
			if (canMove(direction)) {
				centerY-=speed;
			}
		}

		if (direction == Pacman.Direction.DOWN) {
			if (canMove(direction)) {
				centerY+=speed;
			}
		}
	}
	
	public void changeAngleAttributes(Direction direction){
		if (direction == Pacman.Direction.RIGHT) {
			startAngle=0;
		}

		if (direction == Pacman.Direction.LEFT) {
			startAngle=180;
		}

		if (direction == Pacman.Direction.UP) {
			startAngle=90;	
		}

		if (direction == Pacman.Direction.DOWN) {
			startAngle=270;	
		}
		arcAngle=360;
		maxStartAngle=startAngle+70;
		minStartAngle=startAngle;
	}

	
	public Boolean canMove(Pacman.Direction desiredDirection){
		eatFood();
		if(desiredDirection==Pacman.Direction.RIGHT ){
			if(direction!=Pacman.Direction.RIGHT){
				if(Board.tileArray[centerY/Board.TILE_D][(centerX/Board.TILE_D)+1].isWall){
					return false;
				}
			}
			else if(Board.tileArray[centerY/Board.TILE_D][centerX/Board.TILE_D].centerX==centerX && Board.tileArray[centerY/Board.TILE_D][(centerX/Board.TILE_D)+1].isWall){
				return false;
			}
		}
		else if(desiredDirection==Pacman.Direction.LEFT ){
			if(direction!=Pacman.Direction.LEFT){
				if(Board.tileArray[centerY/Board.TILE_D][(centerX/Board.TILE_D)-1].isWall){
					return false;
				}
			}
			else if(Board.tileArray[centerY/Board.TILE_D][centerX/Board.TILE_D].centerX==centerX && Board.tileArray[centerY/Board.TILE_D][(centerX/Board.TILE_D)-1].isWall){
				return false;
			}
		}
		else if(desiredDirection==Pacman.Direction.UP ){
			if(direction!=Pacman.Direction.UP){
				if(Board.tileArray[(centerY/Board.TILE_D)-1][centerX/Board.TILE_D].isWall){
					return false;
				}
			}
			else if(Board.tileArray[centerY/Board.TILE_D][centerX/Board.TILE_D].centerY==centerY && Board.tileArray[(centerY/Board.TILE_D)-1][centerX/Board.TILE_D].isWall){
				return false;
			}
		}
		else if(desiredDirection==Pacman.Direction.DOWN ){
			if(direction!=Pacman.Direction.DOWN){
				if(Board.tileArray[(centerY/Board.TILE_D)+1][centerX/Board.TILE_D].isWall){
					return false;
				}
			}
			else if(Board.tileArray[centerY/Board.TILE_D][centerX/Board.TILE_D].centerY==centerY && Board.tileArray[(centerY/Board.TILE_D)+1][centerX/Board.TILE_D].isWall){
				return false;
			}
		}
		
		return true;
	}
	
	
	public int[] spotAt() {
		int[] now = new int[2];
		System.out.println(" x on board " + centerX/Board.TILE_D + " y on board " + centerY/Board.TILE_D);
		now[0] = centerY/Board.TILE_D;
		now[1] = centerX/Board.TILE_D;
		return now;
	}

	private void eatFood() {
		if(Board.tileArray[(centerY)/Board.TILE_D][centerX/Board.TILE_D].isFood){
			Board.tileArray[(centerY)/Board.TILE_D][centerX/Board.TILE_D].isFood=false;
			coins+=10;
		}
		if(Board.tileArray[(centerY)/Board.TILE_D][centerX/Board.TILE_D].isGhostKiller){
			for(Ghost a: Board.ghosts) {
				a.makeScared();
			}
			Board.tileArray[(centerY)/Board.TILE_D][centerX/Board.TILE_D].isGhostKiller=false;
		}
	}

}
