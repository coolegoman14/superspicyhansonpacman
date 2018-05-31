import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Timer;
public abstract class Ghost {

	Image image;
	int speed=3;
	int centerX;
	int centerY;
	Image scared;
	Image eyes;
	Rectangle clone;
	boolean isScared = false; 
	int keySpot = 1;
	boolean eaten = false;
	Direction direction;
	int secondsOfScare = 10;
	List<Tile> openList= new ArrayList<Tile>();
	List<Tile> closedList= new ArrayList<Tile>();
	List<Tile> adjacentTiles= new ArrayList<Tile>();
	static List<Tile> finalPath= new ArrayList<Tile>();
	Tile smallest, current, startTile, targetTile;
	int interval = 0;
	

	public int[] tellCoordinate() {
		int[] num = new int[2];
		num[0] = centerY/Board.TILE_D;
		num[1] = centerX/Board.TILE_D;
		return num;
	}

	enum Direction{
		RIGHT,LEFT,UP,DOWN;
	}

	public void makeScared() {
		isScared = true;
		scareTimer(secondsOfScare);
	}



	public void draw(Graphics2D g){
		if(eaten){
			g.drawImage(eyes, centerX-10, centerY-6, null);
			fly();
			return;
		}
		//findPath(findTargetTile());
		findPath();
		move();
		clone.setLocation(centerX, centerY-15);
g.setColor(Color.WHITE);
g.draw(clone);

		/*clone.setFrame(centerX, centerY, 5, 5);
		clone.setFrameFromCenter(centerX, centerY, centerX, centerY);
		g.draw(clone);*/
		if(isScared) {
			g.drawImage(scared, centerX-10, centerY-6, null);

		}
		
		else {
			g.drawImage(image, centerX-10, centerY-6, null);
		}
		openList.clear();
		closedList.clear();

	}


	private void fly() {
		// TODO Auto-generated method stub
		int xTarget = 12 *Board.TILE_D;
		int yTarget = 12 * Board.TILE_D;
		if(centerX - xTarget >0) {
			centerX--;
		}
		else {
			centerX++;
		}
		if(centerY - yTarget > 0) {
			centerY --;
		}
		else {
			centerY ++;
		}
	}



	public void scareTimer(int d) {
		
		Timer timer = new Timer(1000,null);
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick();
				
				if(interval == d) {
					isScared = false;
					timer.stop();
				}
			}
		});
		timer.start();




	}

	protected void tick() {
interval++;
	}

	public Ghost() {
		clone= new Rectangle(new Dimension(50,50));
		clone.setLocation(centerX -25, centerY -25);
		try {
			this.scared=ImageIO.read(new File("scared.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			this.eyes = ImageIO.read(new File("ghostEyes.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public abstract Tile findTargetTile();

	public boolean canMove(Tile.Direction desiredDirection,Tile tile){
		if(desiredDirection==Tile.Direction.RIGHT ){
			if(tile.direction==Tile.Direction.LEFT){
				return false;		
			}
			if(Board.tileArray[tile.centerY/Board.TILE_D][(tile.centerX/Board.TILE_D)+1].isWall){
				return false;
			}
			/*else if(Board.tileArray[tile.centerY/Board.TILE_D][tile.centerX/Board.TILE_D].centerX==centerX && Board.tileArray[tile.centerY/Board.TILE_D][(centerX/Board.TILE_D)+1].isWall){
				System.out.println("here");
				return false;
			}*/
		}
		if(desiredDirection==Tile.Direction.LEFT ){
			if(tile.direction==Tile.Direction.RIGHT){
				return false;	
			}
			if(Board.tileArray[tile.centerY/Board.TILE_D][(tile.centerX/Board.TILE_D)-1].isWall){
				return false;
			}
			/*else if(Board.tileArray[tile.centerY/Board.TILE_D][tile.centerX/Board.TILE_D].centerX==centerX && Board.tileArray[tile.centerY/Board.TILE_D][(tile.centerX/Board.TILE_D)-1].isWall){
				return false;
			}*/
		}
		if(desiredDirection==Tile.Direction.UP ){
			if(tile.direction==Tile.Direction.DOWN){
				return false;	
			}
			if(Board.tileArray[(tile.centerY/Board.TILE_D)-1][tile.centerX/Board.TILE_D].isWall){
				return false;
			}
			/*else if(Board.tileArray[tile.centerY/Board.TILE_D][tile.centerX/Board.TILE_D].centerY==centerY && Board.tileArray[(tile.centerY/Board.TILE_D)-1][tile.centerX/Board.TILE_D].isWall){
				return false;
			}*/
		}
		if(desiredDirection==Tile.Direction.DOWN ){
			if(tile.direction==Tile.Direction.UP){
				return false;

			}
			if(Board.tileArray[(tile.centerY/Board.TILE_D)+1][tile.centerX/Board.TILE_D].isWall){
				return false;
			}
			/*else if(Board.tileArray[tile.centerY/Board.TILE_D][tile.centerX/Board.TILE_D].centerY==centerY && Board.tileArray[(tile.centerY/Board.TILE_D)+1][tile.centerX/Board.TILE_D].isWall){
				return false;
			}*/
		}

		return true;
	}

	public void findAdjacentTiles(Tile current){
		adjacentTiles.clear();
		if(canMove(Tile.Direction.RIGHT,current)){
			Board.tileArray[current.centerY/Board.TILE_D][(current.centerX/Board.TILE_D)+1].direction=Tile.Direction.RIGHT;
			adjacentTiles.add(Board.tileArray[current.centerY/Board.TILE_D][(current.centerX/Board.TILE_D)+1]);
		}
		if(canMove(Tile.Direction.LEFT,current)){
			Board.tileArray[current.centerY/Board.TILE_D][(current.centerX/Board.TILE_D)-1].direction=Tile.Direction.LEFT;
			adjacentTiles.add(Board.tileArray[current.centerY/Board.TILE_D][(current.centerX/Board.TILE_D)-1]);
		}
		if(canMove(Tile.Direction.UP,current)){
			Board.tileArray[(current.centerY/Board.TILE_D)-1][current.centerX/Board.TILE_D].direction=Tile.Direction.UP;
			adjacentTiles.add(Board.tileArray[(current.centerY/Board.TILE_D)-1][current.centerX/Board.TILE_D]);
		}
		if(canMove(Tile.Direction.DOWN,current)){
			Board.		tileArray[(current.centerY/Board.TILE_D)+1][current.centerX/Board.TILE_D].direction=Tile.Direction.DOWN;
			adjacentTiles.add(Board.tileArray[(current.centerY/Board.TILE_D)+1][current.centerX/Board.TILE_D]);
		}
	}
	public void findPath(){
		startTile=Board.tileArray[centerY/Board.TILE_D][centerX/Board.TILE_D];
		targetTile= findTargetTile();
		openList.add(startTile);

		while(!openList.isEmpty()){
			current=findSmallestNode();
			openList.remove(current);
			closedList.add(current);
			if(closedList.contains(targetTile)){
				break;
			}
			findAdjacentTiles(current);
			for(Tile tile: adjacentTiles){
				if(closedList.contains(tile)){
					continue;
				}
				if(!openList.contains(tile)){
					tile.parent=current;
					tile.calculateFHG();
					openList.add(tile);
				}
				else{

				}	
			}
		}
		while(targetTile!=startTile){
			if(targetTile.parent!=null){
				targetTile= targetTile.parent;
			}
			else{
				break;
			}
			finalPath.add(targetTile);
		}
		for(Tile tile:openList){
			tile.direction=null;
		}
		for(Tile tile:closedList){
			tile.direction=null;
		}
	}

	public Tile findSmallestNode(){
		Tile smallest=openList.get(0);
		for(int i=0;i<openList.size();i++){
			if(openList.get(i).Fscore<=smallest.Fscore){
				smallest=openList.get(i);
			}
		}
		return smallest;
	}

	public void move() {
		if(finalPath.size()>1){
			moveTo(finalPath.get(finalPath.size()-2));
		}
	}

	public void moveTo(Tile tile){
		if(centerX<tile.centerX){
			centerX+=speed;
		}
		if(centerX>tile.centerX){
			centerX-=speed;
		}
		if(centerY<tile.centerY){
			centerY+=speed;
		}
		if(centerY>tile.centerY){
			centerY-=speed;
		}
	}

	public boolean isInBounds(int y, int x){
		if(x<0 || x>27){
			return false;
		}
		else if(y<0 || y>30){
			return false;
		}
		return true;
	}
	
	
	public int[] spotAt() {
		int[] now = new int[2];
		now[0] = centerY/Board.TILE_D;
		now[1] = centerX/ Board.TILE_D;
		return now;
	}
	
	
	public void deathAnimation(){
		eaten = true;
		

	}
}
