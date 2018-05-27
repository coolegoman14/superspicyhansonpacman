import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public abstract class Ghost {
	
	Image Blinky;
	Image Pinky;
	Image Clyde;
	Image Inky;
	Pacman pacman;
	int speed=6;
	int centerX;
	int centerY;
	Direction direction;
	
	enum Direction{
		RIGHT,LEFT,UP,DOWN;
	}
	public Ghost(Pacman pacman) {
		this.pacman=pacman;
		try {
			
			Blinky=ImageIO.read(new File("Blinky.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			Pinky=ImageIO.read(new File("Pinky.png")).getScaledInstance(40, 40,Image.SCALE_SMOOTH);
			Clyde=ImageIO.read(new File("Clyde.png")).getScaledInstance(40, 40,Image.SCALE_SMOOTH);
			Inky=ImageIO.read(new File("Inky.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
public Boolean canMove(Ghost.Direction desiredDirection,Tile tile){
		
		if(desiredDirection==Ghost.Direction.RIGHT ){
			if(direction!=Ghost.Direction.RIGHT){
				if(Board.tileArray[tile.centerY/Board.TILE_D][(tile.centerX/Board.TILE_D)+1].isWall){
					return false;
				}
			}
			else if(Board.tileArray[tile.centerY/Board.TILE_D][tile.centerX/Board.TILE_D].centerX==centerX && Board.tileArray[tile.centerY/Board.TILE_D][(centerX/Board.TILE_D)+1].isWall){
				return false;
			}
		}
		if(desiredDirection==Ghost.Direction.LEFT ){
			if(direction!=Ghost.Direction.LEFT){
				if(Board.tileArray[tile.centerY/Board.TILE_D][(tile.centerX/Board.TILE_D)-1].isWall){
					return false;
				}
			}
			else if(Board.tileArray[tile.centerY/Board.TILE_D][tile.centerX/Board.TILE_D].centerX==centerX && Board.tileArray[tile.centerY/Board.TILE_D][(tile.centerX/Board.TILE_D)-1].isWall){
				return false;
			}
		}
		if(desiredDirection==Ghost.Direction.UP ){
			if(direction!=Ghost.Direction.UP){
				if(Board.tileArray[(tile.centerY/Board.TILE_D)-1][tile.centerX/Board.TILE_D].isWall){
					return false;
				}
			}
			else if(Board.tileArray[tile.centerY/Board.TILE_D][tile.centerX/Board.TILE_D].centerY==centerY && Board.tileArray[(tile.centerY/Board.TILE_D)-1][tile.centerX/Board.TILE_D].isWall){
				return false;
			}
		}
		if(desiredDirection==Ghost.Direction.DOWN ){
			if(direction!=Ghost.Direction.DOWN){
				if(Board.tileArray[(tile.centerY/Board.TILE_D)+1][tile.centerX/Board.TILE_D].isWall){
					return false;
				}
			}
			else if(Board.tileArray[tile.centerY/Board.TILE_D][tile.centerX/Board.TILE_D].centerY==centerY && Board.tileArray[(tile.centerY/Board.TILE_D)+1][tile.centerX/Board.TILE_D].isWall){
				return false;
			}
		}
		
		return true;
	}


public void findAdjacentTiles(Tile current, List<Tile> adjacentTiles){
	adjacentTiles.clear();
	if(canMove(Ghost.Direction.RIGHT,current)){
		adjacentTiles.add(Board.tileArray[current.y/Board.TILE_D][(current.x/Board.TILE_D)+1]);
	}
	if(canMove(Ghost.Direction.LEFT,current)){
		adjacentTiles.add(Board.tileArray[current.y/Board.TILE_D][(current.x/Board.TILE_D)-1]);
	}
	if(canMove(Ghost.Direction.UP,current)){
		adjacentTiles.add(Board.tileArray[(current.y/Board.TILE_D)-1][current.x/Board.TILE_D]);
	}
	if(canMove(Ghost.Direction.DOWN,current)){
		adjacentTiles.add(Board.tileArray[(current.y/Board.TILE_D)+1][current.x/Board.TILE_D]);
	}
	
}

}
