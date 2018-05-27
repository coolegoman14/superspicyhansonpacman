import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Blinky extends Ghost{
		
	
	int x=32;
	int y=32;
	int centerX=32;
	int centerY=32;
	
	Rectangle clone;
	Direction direction=Ghost.Direction.RIGHT;
	Direction futureDirection;
	List<Tile> openList= new ArrayList<Tile>();
	List<Tile> closedList= new ArrayList<Tile>();
	List<Tile> adjacentTiles= new ArrayList<Tile>();
	static List<Tile> finalPath= new ArrayList<Tile>();
	Tile startTile=Board.tileArray[y/Board.TILE_D][x/Board.TILE_D];
	Tile finalTile;
	Tile smallest;
	Tile current;

	public Blinky(Pacman pacman) {
		super(pacman);
		clone= new Rectangle();
	}
	
	public void draw(Graphics2D g){
		
		findPath();
		//move();
		
		/*clone.setFrameFromCenter(x+16, y+16, x, y);
		g.setColor(Color.DARK_GRAY);
		g.draw(clone);*/
		g.drawImage(Blinky, x-5, y-3, null);
		openList.clear();
		closedList.clear();
		
	}
	
	public void move() {
		moveTo(finalPath.get(1));
	}
	
	public void moveTo(Tile tile){
		while(centerX<tile.centerX){
			x+=speed;
		}
		while(centerX>tile.centerX){
			x-=speed;
		}
		while(centerY<tile.centerY){
			y+=speed;
		}
		while(centerY>tile.centerY){
			y-=speed;
		}
	}
	
	public void findPath(){
		Tile targetTile=Board.tileArray[Pacman.centerY/Board.TILE_D][Pacman.centerX/Board.TILE_D];
		openList.add(startTile);

		while(!openList.isEmpty()){
			current=findSmallestNode();
			openList.remove(current);
			closedList.add(current);
				if(closedList.contains(targetTile)){
					break;
				}
				findAdjacentTiles(current,adjacentTiles);
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
		finalTile=targetTile;
		while(finalTile!=startTile){
			finalTile= finalTile.parent;
			finalPath.add(finalTile);
		}
	}
	
	
	
	public Tile findSmallestNode(){
		 smallest=openList.get(0);
		for(int i=0;i<openList.size();i++){
			if(openList.get(i).Fscore<smallest.Fscore){
				smallest=openList.get(i);
			}
		}
		return smallest;
	}
}

