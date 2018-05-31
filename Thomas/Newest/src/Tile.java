
public class Tile {

	
	boolean isWall;
	boolean isPortal;
	boolean isFood;
	boolean isGhostKiller;
	int centerX;
	int centerY;
	double Hscore;//heuristic
	double Gscore;//movement cost from starting tile
	double Fscore;//combination of F and G
	Tile parent;
	Direction direction=null;
	
	enum Direction{
		RIGHT,LEFT,UP,DOWN;
	}
	
	public Tile(int centerX, int centerY) {
		
		this.centerX=centerX+Board.TILE_D/2;
		this.centerY=centerY+Board.TILE_D/2;
	}
	
	public void calculateFHG(){
		
			int x= Math.abs(centerX-Pacman.centerX);
			int y= Math.abs(centerY-Pacman.centerY);
			double distance= Math.sqrt((x^2)+(y^2));
			Hscore=distance;
			Gscore=parent.Gscore+1;
			Fscore=Hscore+Gscore;
		
	}
}
