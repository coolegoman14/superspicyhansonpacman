import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Blinky extends Ghost{
		
	
	int x=36;
	int y=36;
	Rectangle clone;

	public Blinky(Pacman pacman) {
		super(pacman);
		clone= new Rectangle();
		
	}
	
	public void draw(Graphics2D g){
		
		if(x!=pacman.x){
			if(x<pacman.x && canMove(x+2,y)){
				x+=3;
			}
			else if(canMove(x-2,y)){
				x-=3;
			}
		}
		if(y!=pacman.y){
			if(y<pacman.y && canMove(x,y+2)){
				y+=3;
			}
			else if(canMove(x,y-2)){
				y-=3;
			}
		}
	
		clone.setFrameFromCenter(x+13, y+13, x, y);
		g.draw(clone);
		g.drawImage(Blinky, x, y, null);
	}
	public Boolean canMove(int x,int y){
		
			for(Rectangle r: Board.wallList){
				if(clone.intersects(r.getX()-9, r.getY()-9, r.getWidth()-9, r.getHeight()-9)){
					return false;
			}
		}
		return true;
	}
}
