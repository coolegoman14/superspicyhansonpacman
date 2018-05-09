import java.awt.Graphics2D;

public class Blinky extends Ghost{
		
	
	int x=50;
	int y=50;

	public Blinky(Pacman pacman) {
		super(pacman);
		
	}
	
	public void draw(Graphics2D g){
		if(x!=pacman.x){
			if(x<pacman.x){
				x+=2;
			}
			else{
				x-=2;
			}
		}
		if(y!=pacman.y){
			if(y<pacman.y){
				y+=2;
			}
			else{
				y-=2;
			}
		}
		g.drawImage(Blinky, x, y, null);
	}
	

}
