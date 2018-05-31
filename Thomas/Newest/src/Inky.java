
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Inky extends Ghost {
	
	Blinky blinky;

	Direction direction=Ghost.Direction.RIGHT;
	
	public Inky(Blinky blinky) {	
		try {
			this.image=ImageIO.read(new File("Inky.png")).getScaledInstance(60, 60,Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
	}
		this.centerX=Board.TILE_D*10+Board.TILE_D/2;
		this.centerY=Board.TILE_D*13+Board.TILE_D/2;
		this.blinky=blinky;
		this.direction=Ghost.Direction.RIGHT;
		
	}

	
	private Tile findTile(int x,int y){
		for (int i =2 ;i>-1;i--){
			if(isInBounds((Pacman.centerY/Board.TILE_D)+i*y,(Pacman.centerX/Board.TILE_D)+ i* x)){
				if(!Board.tileArray[(Pacman.centerY/Board.TILE_D)+i*y][(Pacman.centerX/Board.TILE_D)+ i* x].isWall){
					return Board.tileArray[(Pacman.centerY/Board.TILE_D)+i*y][(Pacman.centerX/Board.TILE_D)+ i* x];
				}
			}
		}
		return null;	
	}
	@Override
	public Tile findTargetTile() {
		Tile targetTile = null;
		if(isScared) {
			int[] now = tellCoordinate();
			if((now[0] == 25  || now[0] == 26) && (now[1] == 26 || now[1] == 25)) {
				keySpot = 2;
				
			}
			if((now[0] == 29  || now[0] == 28) && (now[1] == 21 || now[1] == 20)) {
				keySpot = 3;
				
			}
			if((now[0] == 26  || now[0] == 25) && (now[1] == 17|| now[1] == 18)) {
				keySpot = 4;
				
			}
			if((now[0] == 23  || now[0] == 24) && (now[1] == 18 || now[1] == 17)) {
				keySpot = 1;
				
			}
			
		
			if(keySpot == 1) {
				return Board.tileArray[26][26];
			}
			if(keySpot == 2){
				return Board.tileArray[29][20];
			}
			if(keySpot == 3) {
				return Board.tileArray[26][18];
			}
			if(keySpot == 4){
				return Board.tileArray[23][20];
			}
		
		}
		
		if(Pacman.direction==Pacman.Direction.RIGHT){
			targetTile=findTile(1,0);		
		}
		else if (Pacman.direction==Pacman.Direction.LEFT){		
			targetTile=findTile(-1,0);	
		}
		else if(Pacman.direction==Pacman.Direction.UP){	
			targetTile=findTile(0,-1);
		}
		else	if(Pacman.direction == Pacman.Direction.DOWN) {
			targetTile = findTile(0, 1);
		}
		else {
			targetTile = findTile(0,0);
		}
		
		
		System.out.println("target tile " + targetTile.centerX);
		System.out.println("blinky value " + blinky.centerX);
		
			int deltaX=Math.abs(targetTile.centerX - blinky.centerX);
			int deltaY=Math.abs(targetTile.centerY - blinky.centerY);
			if(isInBounds((targetTile.centerY+deltaY)/Board.TILE_D,(targetTile.centerX+deltaX)/Board.TILE_D)){
				return Board.tileArray[(targetTile.centerY+deltaY)/Board.TILE_D][(targetTile.centerX+deltaX)/Board.TILE_D];
			}	
				return Board.tileArray[Pacman.centerY/Board.TILE_D][Pacman.centerX/Board.TILE_D];
	}
}
