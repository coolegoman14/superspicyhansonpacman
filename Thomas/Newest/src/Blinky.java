
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Blinky extends Ghost{
	

	
	
	
	public Blinky() {
	
		
		
		try {
			this.image=ImageIO.read(new File("Blinky.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.centerX=Board.TILE_D*13+Board.TILE_D/2;
		this.centerY=Board.TILE_D*11+Board.TILE_D/2;
		//this.direction=Ghost.Direction.RIGHT;
		
	}
	

	public Tile findTargetTile(){
		if(isScared) {
			int[] now = tellCoordinate();
			if((now[0] == 1  || now[0] == 2) && (now[1] == 26 || now[1] == 25)) {
				keySpot = 2;
				
			}
			if((now[0] == 1  || now[0] == 2) && (now[1] == 21 || now[1] == 22)) {
				keySpot = 3;
				
			}
			if((now[0] == 5  || now[0] == 4) && (now[1] == 21|| now[1] == 22)) {
				keySpot = 4;
				
			}
			if((now[0] == 5  || now[0] == 4) && (now[1] == 26 || now[1] == 25)) {
				keySpot = 1;
				
			}
			
			
			
			
			if(keySpot == 1) {
				return Board.tileArray[1][26];
			}
			if(keySpot == 2){
				return Board.tileArray[1][21];
			}
			if(keySpot == 3) {
				return Board.tileArray[5][21];
			}
			if(keySpot == 4){
				return Board.tileArray[5][26];
			}
		
		}
		
		return Board.tileArray[Pacman.centerY/Board.TILE_D][Pacman.centerX/Board.TILE_D];	
	}
	
}

