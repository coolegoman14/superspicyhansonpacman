import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;



public class Board {
	
	
	static List<Rectangle> wallList= new ArrayList<Rectangle>();
	static List<Rectangle> foodList= new ArrayList<Rectangle>();
	final int RECT_WIDTH=36;
	final int RECT_HEIGHT=36;
	Boolean first=true;

	
	static int[][] board= {  //28--horizontal x 31--vertical 
			/*
0=empty not null
1=wall
2=food
3=ghost killer food
4=ghost
5=ghost spawn point
6=only ghosts can go in here 
7=portal 
8=pacman spawn portal 
9=pacman
*/
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
			{ 1, 3, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 0, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1 },
			{ 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 1, 2, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 1, 1, 1, 7, 7, 1, 1, 1, 0, 1, 1, 2, 1, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 6, 0, 0, 0, 0, 6, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
			{ 7, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 7 },
			{ 1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 6, 0, 0, 0, 0, 6, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 1, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 0, 0, 0, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1 },
			{ 1, 3, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 0, 8, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 3, 1 },
			{ 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1 },
			{ 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 },
			{ 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },

	};

	public Board() {
		
	}
	
	public void drawBoard(Graphics2D g){
//row=y col=x
		for(int row=0;row<board.length;row++){
			for(int col=0;col<board[row].length;col++){
				if(board[row][col]==1){
						if(first){
							Rectangle wall= new Rectangle(36,36);
							wall.setLocation(col*37, row*37);
							wallList.add(wall);
						}
				}
				
				else{
					g.setColor(Color.DARK_GRAY);	
					g.drawRect(col*37, row*37, 36, 36);
					g.fillRect(col*37, row*37, 36, 36);
					
					if(first){
						Rectangle food = new Rectangle(5,5);
						food.setLocation(10+col*37, 10+row*37);
						foodList.add(food);
					}
					
				}
				/*if(board[row][col]==8){
					Pacman.x=(col+1)*36+col;
					Pacman.y=(row+1)*36+row;
				}*/
			}
		}
		drawWalls(g);
		drawFood(g);
		first=false;
	}
	
	private void drawWalls(Graphics2D g){
		g.setColor(Color.CYAN.darker());
		for(Rectangle wall: wallList){
			g.draw(wall);
		}
	}
	private void drawFood(Graphics2D g){
		g.setColor(Color.PINK);
		for(Rectangle food: foodList){
			g.draw(food);
		}
	}
	
}
