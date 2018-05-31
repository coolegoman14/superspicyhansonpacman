import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;



public class Board {
	static Tile[][] tileArray= new Tile[31][28];
	static Image board;
	static Image logo;
	static{
		try {
			board= ImageIO.read(new File("PacmanBoard.png")).getScaledInstance((224)*3, (248)*3,Image.SCALE_SMOOTH);
			logo= ImageIO.read(new File("PacmanLogo.png")).getScaledInstance(1224, 653,Image.SCALE_SMOOTH);
			//tile length and width is 8*3
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	static final int TILE_D=24;
	Boolean isIncreasinglogo;
	Boolean first=true;
	int size=13;
	int maxSize=18;
	int minSize=8;
	int logos=75;
	int logoMax=100;
	int logoMin=50;
	boolean isIncreasing;
	static List<Ghost> ghosts = new ArrayList();
	Blinky blinky;
	Pinky pinky;
	Clyde clyde;
	Inky inky;
	
	static int[][] boardArray= {  //28--horizontal x 31--vertical 
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
			{ 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1 },
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

	public Board(Blinky blinky, Pinky pinky, Clyde clyde, Inky inky) {
		this.blinky=blinky;
		this.pinky=pinky;
		this.clyde=clyde;
this.inky = inky;
		ghosts.add(blinky);
		ghosts.add(clyde);
		ghosts.add(pinky);
		ghosts.add(inky);
			makeTileArray();
		
	}
	
	public void drawBoard(Graphics2D g){	
	logoAnimation();
	g.drawImage(board, 0, 0, null);
	//g.drawImage(logo, 600, logos, null);
		for(int i=0;i<tileArray.length;i++){
			for(int x=0;x<tileArray[i].length;x++){
				
				if(tileArray[i][x].isFood){
					g.setColor(Color.WHITE);
					g.drawOval(tileArray[i][x].centerX-1, tileArray[i][x].centerY-1, 5, 5);
					g.fillOval(tileArray[i][x].centerX-1, tileArray[i][x].centerY-1, 5, 5);
					
				}
				if(tileArray[i][x].isGhostKiller){
					g.setColor(Color.WHITE);
					g.drawOval(tileArray[i][x].centerX-4, tileArray[i][x].centerY-2, size, size);
					g.fillOval(tileArray[i][x].centerX-4, tileArray[i][x].centerY-2, size, size);
				}
				
			}
		}	
		foodAnimation();
		
		g.setFont(new Font("Arial",100,100));
		g.drawString("SCORE: "+Pacman.coins, 800, 100);
		
	}
	
	
	public void makeTileArray(){
		
		
		
		for(int i=0;i<boardArray.length;i++){
			for(int x=0;x<boardArray[i].length;x++){
				Tile tile = new Tile(TILE_D*x,TILE_D*i);
				if(boardArray[i][x]==1){
					tile.isWall=true;
				}
				if(boardArray[i][x]==2){
					tile.isFood=true;
				}
				if(boardArray[i][x]==3){
					tile.isGhostKiller=true;
				}
				if(boardArray[i][x]==7){
					tile.isPortal=true;
				}
				tileArray[i][x]=tile;
			}
		}	
	}
	
	public void foodAnimation(){
		
		if (size == maxSize) {
			isIncreasing = false;
		} else if (size == minSize) {
			isIncreasing = true;
		}
		if (isIncreasing) {
			size += 1;
			
		} else {
			size -= 1;	
		}
	}
public void logoAnimation(){
		
		if (logos == logoMax) {
			isIncreasinglogo = false;
		} else if (logos == logoMin) {
			isIncreasinglogo = true;
		}
		if (isIncreasing && logos<logoMax && logos>logoMax/2) {
			logos += 10;
		} else {
			logos -= 10;	
		}
	}
public void drawGhostPath(Graphics2D g){
	
	for(Tile tile: blinky.finalPath){
		g.setColor(Color.red);
		g.fillRect(tile.centerX, tile.centerY, Board.TILE_D-5, Board.TILE_D-5);
	}
	
	for(Tile tile: pinky.finalPath){
		g.setColor(Color.pink);
		g.fillRect(tile.centerX-5, tile.centerY, Board.TILE_D-5, Board.TILE_D-5);
	}
	clyde.finalPath.clear();
	blinky.finalPath.clear();
	pinky.finalPath.clear();
	inky.finalPath.clear();
	
 }


}
