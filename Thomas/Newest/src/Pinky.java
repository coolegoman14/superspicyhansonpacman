
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Pinky extends Ghost {
	
	
	Direction direction=Ghost.Direction.RIGHT;
	
	public Pinky() {
		try {
			this.image=ImageIO.read(new File("Pinky.png")).getScaledInstance(40, 40,Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.centerX=Board.TILE_D*13+Board.TILE_D/2;
		this.centerY=Board.TILE_D*14+Board.TILE_D/2;
		//this.direction=Ghost.Direction.RIGHT;
		
	}
	

	
	
	private Tile findTile(int x,int y){
		for (int i =4 ;i>-1;i--){
			if(isInBounds((Pacman.centerY/Board.TILE_D)+i*y,(Pacman.centerX/Board.TILE_D)+ i* x)){
				if(!Board.tileArray[(Pacman.centerY/Board.TILE_D)+i*y][(Pacman.centerX/Board.TILE_D)+ i* x].isWall){
					return Board.tileArray[(Pacman.centerY/Board.TILE_D)+i*y][(Pacman.centerX/Board.TILE_D)+ i* x];
				}
			}
		}
		return null;	
	}
	
	public Tile findTargetTile(){
		
		if(isScared) {
			int[] now = tellCoordinate();
			if((now[0] == 1  || now[0] == 2) && (now[1] == 1 || now[1] == 2)) {
				keySpot = 2;
			
			}
			if((now[0] == 4  || now[0] == 5) && (now[1] == 1 || now[1] == 2)) {
				keySpot = 3;
			
			}
			if((now[0] == 5  || now[0] == 4) && (now[1] == 6|| now[1] == 5)) {
				keySpot = 4;
			
			}
			if((now[0] == 2  || now[0] == 1) && (now[1] == 6 || now[1] == 5)) {
				keySpot = 1;
			
			}
			
			
			
			
			if(keySpot == 1) {
				return Board.tileArray[1][1];
			}
			if(keySpot == 2){
				return Board.tileArray[5][1];
			}
			if(keySpot == 3) {
				return Board.tileArray[5][6];
			}
			if(keySpot == 4){
				return Board.tileArray[1][6];
			}
		
		}
		
		
		if(Pacman.direction==Pacman.Direction.RIGHT){
			return this.findTile(1,0);		
		}
		if(Pacman.direction==Pacman.Direction.LEFT){		
			return this.findTile(-1,0);	
		}
		if(Pacman.direction==Pacman.Direction.UP){	
			return this.findTile(0,-1);
		}
				return this.findTile(0,1);
	}
}
