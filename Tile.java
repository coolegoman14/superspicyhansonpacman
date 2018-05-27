
public class Tile {

	
	boolean isWall;
	boolean isFood;
	boolean isGhostKiller;
	int x;
	int y;
	int centerX;
	int centerY;
	double Hscore;//heuristic
	double Gscore;//movement cost from starting tile
	double Fscore;//combination of F and G
	Tile parent;
	
	public Tile(int x, int y) {
		this.x=x;
		this.y=y;
		this.centerX=x+Board.TILE_D/2;
		this.centerY=y+Board.TILE_D/2;
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
