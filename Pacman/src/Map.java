import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map implements GameWideConstants{

	int SIZE_PANEL = 800;
	Pacman pac = new Pacman();
	Blinky blinky = new Blinky(pac);
//	Pinky pinky = new Pinky(pac);
	List<Ghost> ghosts = new ArrayList();
	
	List<Apple> appleList = new ArrayList();
	static List<Wall> wallList = new ArrayList();
	static List<Path> pathList = new ArrayList();
	List<Tile> tileList = new ArrayList();


	int[][] map= {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
			{1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
			{1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
			{1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
			{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
			{1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
			{1,2,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1},
			{1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,0,2,2,2,1,1,2,2,2,2,2,2,1},
			{1,1,1,1,1,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,1,1,1,1,1},
			{0,0,0,0,0,1,2,1,1,1,1,1,0,1,1,0,1,1,1,1,1,2,1,0,0,0,0,0},
			{0,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,0},
			{0,0,0,0,0,1,2,1,1,0,1,1,1,2,2,1,1,1,0,1,1,2,1,0,0,0,0,0},
			{1,1,1,1,1,1,2,1,1,0,1,6,0,0,0,0,6,1,0,1,1,2,1,1,1,1,1,1},
			{2,0,0,0,0,0,2,0,0,0,1,0,0,0,0,0,0,1,0,0,0,2,0,0,0,0,0,2},
			{1,1,1,1,1,1,2,1,1,0,1,6,0,0,0,0,6,1,0,1,1,2,1,1,1,1,1,1},
			{0,0,0,0,0,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,0,0,0,0,0},
			{0,0,0,0,0,1,2,1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,1,0,0,0,0,0},
			{0,0,0,0,0,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,0,0,0,0,0},
			{1,1,1,1,1,1,2,1,1,0,1,1,1,1,1,1,1,1,0,1,1,2,1,1,1,1,1,1},
			{1,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,1},
			{1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
			{1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,2,1,1,1,1,1,2,1,1,1,1,2,1},
			{1,2,2,2,1,1,2,2,2,2,2,2,2,0,2,2,2,2,2,2,2,2,1,1,2,2,2,1},
			{1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1},
			{1,1,1,2,1,1,2,1,1,2,1,1,1,1,1,1,1,1,2,1,1,2,1,1,2,1,1,1},
			{1,2,2,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,1,1,2,2,2,2,2,2,1},
			{1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
			{1,2,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,2,1},
			{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},

	};


	public void draw(Graphics g) {
		
		hitApple(pac);
		onTrack();
		for(Wall a : wallList) {
			a.draw((Graphics2D) g);
		}
		for(Path a: pathList) {
			a.draw(g);
		}
		for(Apple a: appleList) {
			a.draw(g);

		}
		blinky.draw((Graphics2D) g);
		//pinky.draw((Graphics2D) g);

		pac.draw(g);
	}

	
	
	public void onTrack() {
		Tile pacOn = findPac();
		if(pacOn == null) {
			return;
		}
		if(pac.direction.equalsIgnoreCase("right")) {
			pac.setY(pacOn.y);
		}
if(pac.direction.equalsIgnoreCase("left")) {
			pac.setY(pacOn.y);
		}
if(pac.direction.equalsIgnoreCase("up")) {
	pac.setX(pacOn.x);
}
if(pac.direction.equalsIgnoreCase("down")) {
	pac.setX(pacOn.x);
}
	}

	public List<String> canGo() {
		List<String> string = new ArrayList();
		Tile pacOn = findPac();
		
		for(Path a: pathList) {
			
			if(pacOn.boardY == a.boardY) {
			if(pacOn.boardX +1 == a.boardX) {
				string.add("right");
			}
			if(pacOn.boardX -1 == a.boardX) {
				string.add("left");
			}
		}
			if(pacOn.boardX == a.boardX) {
				if(pacOn.boardY+1 == a.boardY) {
					string.add("down");
				}
				if(pacOn.boardY-1 == a.boardY) {
					string.add("up");
				}
				
				
			}
		}
		return string;
	}


	public Boolean canMove(String direction){
		if (direction.equals("right")) {
			for (Wall r : wallList) {
				if (pac.clone.intersects(r.getX() - 7, r.getY(), r.getWidth(), r.getHeight())) {
					System.out.println("right not possible");
					return false;
				}
			}
		}
		if (direction.equals("left")) {
			for (Wall r : wallList) {
				if (pac.clone.intersects(r.getX() + 7, r.getY(), r.getWidth(), r.getHeight())) {
					return false;
				}
			}
		}
		if (direction.equals("up")) {
			for (Wall r : wallList) {
				if (pac.clone.intersects(r.getX(), r.getY() + 7, r.getWidth(), r.getHeight())) {
					return false;
				}
			}
		}
		if (direction.equals("down")) {
			for (Wall r : wallList) {
				if (pac.clone.intersects(r.getX(), r.getY() - 7, r.getWidth(), r.getHeight())) {
					System.out.println(r.toString());
					return false;
				}
			}
		}
		
		return true;
	}
	
	
public Tile findPac() {
	int pacXC = pac.x/squareSize;
	int pacYC = pac.y/squareSize;
	for(Tile a: tileList) {
		int[] coordinates = a.getTile();
		if(coordinates[0] == pacYC && coordinates[1] == pacXC) {
			System.out.println("found pac at " + pacYC + " " + pacXC + " " + a.toString());
			return a;
		}
	}
	
	return null;
}





	public boolean hitApple(Pacman pac) {

		for(int i = 0; i < appleList.size(); i++) {
			Apple a = appleList.get(i);
			if((pac.x + pac.radius == a.x) &&pac.y + pac.radius == a.y) {
				appleList.remove(i);
				return true;
			}
		}
		return false;
	}


	public void makeObjects() {
		
		boolean first = true;

		for(int i = 0; i < map.length; i++) {
			for(int w = 0; w < map[i].length; w ++) {
				int x = w*squareSize;
				int y = i*squareSize;
				if(map[i][w] == 1) {
					Wall a = new Wall(x, y);
					wallList.add(a);
					tileList.add(a);
				}
				if(map[i][w] !=1){
					
					if(first) {
						
						
						blinky.setSpot(x, y);
				//		pinky.setX(200);
					//	pinky.setY(200);
						
						first = false;
						
					}
					Path a = new Path(x, y);
					pathList.add(a);
					tileList.add(a);
					Apple apple = new Apple(x+10 ,y+10);
					appleList.add(apple);
					pac.setX(x);
					pac.setY(y);
					
				}
			}
		}

	}




}
