import java.awt.Graphics2D;


public class Pinky extends Ghost {
	
	
	int x=100;
	int y=250;
	int targetX;
	int targetY;
	
	public Pinky(Pacman pacman) {
		super(pacman);
	
	}
	
	public void draw(Graphics2D g){
		if(pacman.direction==Pacman.Direction.RIGHT){
			targetX=pacman.x+10;
		}
		if(pacman.direction==Pacman.Direction.LEFT){
			targetX=pacman.x-10;
		}
		if(pacman.direction==Pacman.Direction.UP){
			targetX=pacman.y+10;
		}
		if(pacman.direction==Pacman.Direction.DOWN){
			targetX=pacman.x-10;
		}
			if(x<targetX){
				x+=2;
			}
			else{
				x-=2;
			}
			if(y<targetY){
				y+=2;
			}
			else{
				y-=2;
			}
			g.drawImage(Pinky, x, y, null);
	}


}
