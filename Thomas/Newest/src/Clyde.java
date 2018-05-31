
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Clyde extends Ghost {
	
	
	int x=45;
	int y=80;
	
	
	Direction direction=Ghost.Direction.RIGHT;

	public Clyde() {
		try {
			this.image=ImageIO.read(new File("Clyde.png")).getScaledInstance(50, 50,Image.SCALE_SMOOTH);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.centerX=Board.TILE_D*14+Board.TILE_D/2;
		this.centerY=Board.TILE_D*13+Board.TILE_D/2;
		this.direction=Ghost.Direction.RIGHT;
		
	}
	

	
	
	public Tile findTargetTile() {
		
		
		if(isScared) {
			int[] now = tellCoordinate();
			if((now[0] == 29  || now[0] == 28) && (now[1] == 10 || now[1] == 11)) {
				keySpot = 2;
				System.out.println("to main got false");
			}
			if((now[0] == 25  || now[0] == 26) && (now[1] == 1 || now[1] == 2)) {
				keySpot = 3;
				System.out.println("to main got false");
			}
			if((now[0] == 23  || now[0] == 24) && (now[1] == 6|| now[1] == 5)) {
				keySpot = 4;
				System.out.println("to main got false");
			}
			if((now[0] == 27  || now[0] == 26) && (now[1] == 13 || now[1] == 12)) {
				keySpot = 1;
				System.out.println("to main got false");
			}
			
			
			
			
			if(keySpot == 1) {
				return Board.tileArray[29][10];
			}
			if(keySpot == 2){
				return Board.tileArray[26][1];
			}
			if(keySpot == 3) {
				return Board.tileArray[23][6];
			}
			if(keySpot == 4){
				return Board.tileArray[26][12];
			}
		
		}
		
		
		int deltaX=Math.abs(Pacman.centerX - centerX);
		int deltaY=Math.abs(Pacman.centerY - centerY);
		double distance= Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));
		if(distance>34*8){
			return Board.tileArray[Pacman.centerY/Board.TILE_D][Pacman.centerX/Board.TILE_D];	
		}
		return Board.tileArray[29][1];	
	}
}
