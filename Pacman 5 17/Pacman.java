import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pacman {
	
	Direction direction;
	Direction userDirection;
	Boolean isIncreasing=true;
	int startAngle=0;
	int arcAngle=360;
	int maxStartAngle=70;
	int minStartAngle=290;
	static int x=36;
	static int y=36;
	int tilex;
	int tiley;
	int speed=6;
	int coins;
	Rectangle clone;
	
	 enum Direction{
			RIGHT, LEFT, UP, DOWN;
		}
	 
	public Pacman() {
		 clone= new Rectangle();
		 clone.setFrameFromCenter(x+16, y+16, x, y);
		
	}
	
	public  void draw(Graphics2D g){
			g.setColor(Color.DARK_GRAY);
			clone.setFrameFromCenter(x+16, y+16, x, y);
			//g.draw(clone);
			g.drawArc(x,y,36,36,startAngle,arcAngle);
			g.setColor(Color.yellow);
			g.fillArc(x,y,36,36,startAngle,arcAngle);
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
			if (canMove(Pacman.Direction.RIGHT)) {
				x += speed;
			}
		}

		if (direction == Pacman.Direction.LEFT) {
			if (canMove(Pacman.Direction.LEFT)) {
				x -= speed;
			}

		}

		if (direction == Pacman.Direction.UP) {
			if (canMove(Pacman.Direction.UP)) {
				y -= speed;
			}
		}

		if (direction == Pacman.Direction.DOWN) {
			if (canMove(Pacman.Direction.DOWN)) {
				y += speed;
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

	public Boolean canMove(Pacman.Direction direction) {

		eatFood();
		if (direction == Pacman.Direction.RIGHT) {
			for (Rectangle r : Board.wallList) {
				if (clone.intersects(r.getX() - 7, r.getY(), r.getWidth(), r.getHeight())) {
					return false;
				}
			}
		}
		if (direction == Pacman.Direction.LEFT) {
			for (Rectangle r : Board.wallList) {
				if (clone.intersects(r.getX() + 7, r.getY(), r.getWidth(), r.getHeight())) {
					return false;
				}
			}
		}
		if (direction == Pacman.Direction.UP) {
			for (Rectangle r : Board.wallList) {
				if (clone.intersects(r.getX(), r.getY() + 7, r.getWidth(), r.getHeight())) {
					return false;
				}
			}
		}
		if (direction == Pacman.Direction.DOWN) {
			for (Rectangle r : Board.wallList) {
				if (clone.intersects(r.getX(), r.getY() - 7, r.getWidth(), r.getHeight())) {
					return false;
				}
			}
		}

		return true;
	}

	private void eatFood() {
		for (int i = 0; i < Board.foodList.size(); i++) {
			if (clone.intersects(Board.foodList.get(i))) {
				coins++;
				Board.foodList.remove(i);
				break;
			}
		}
	}

}
