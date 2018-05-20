import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;

public class Pacman implements GameWideConstants{

	boolean wider = true;
	int x = 100;
	int speed = 5;
	int y = 50;
	int startAngle = 0;
	int arcAngle = 250;
	int radius = 10;
	String direction = "up";
	Rectangle clone;
	boolean firstTime = true;
	
	
	
	public Pacman() {
		clone= new Rectangle();
		clone.setFrameFromCenter(x+15, y+15, x, y);
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		//g.drawRect(x, y, blockSize, blockSize);
		g.setColor(Color.yellow);
		munch();
		System.out.println(" pacman x is " + x + " the y is " + y);
		g.fillArc(x, y, blockSize, blockSize, startAngle, arcAngle);
		//	g.fillArc(x, y, BlockSize, blockSize, 180-startAngle, arcAngle);

	}

	public void setX(int number) {
		x = number;
	}

	public void setY(int number) {
		y = number;
	}





	public void munch() {
		if(wider) {
			arcAngle+=10;
			startAngle += 10;
			//			System.out.println("angle got bigger " + arcAngle + " " + startAngle);
		}
		if(!wider) {
			arcAngle-= 10;
			startAngle-= 10;
			//			System.out.println("angle got smaller " + arcAngle);
		}
		if(arcAngle > 235) {
			wider = false;
		}
		if(arcAngle < 135 ) {
			wider = true;
		}
		//		System.out.println("munch");
	}



	public void stop() {
		direction = "";
	}



	public void move() {
		speed = 6;
		if(direction != "") {
			if(direction.equalsIgnoreCase("right")) {
				x+= speed;
			}
			if(direction.equalsIgnoreCase("up")) {
				y-= speed;
			}
			if(direction.equalsIgnoreCase("down")) {
				y+= speed;
			}
			if(direction.equalsIgnoreCase("left")) {
				x -= speed;
			}
		}
	}



}
