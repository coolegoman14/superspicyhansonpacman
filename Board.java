import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;



public class Board {
	static Tile[][] tileArray= new Tile[31][28];
	static Image board;
	static{
		try {
			board= ImageIO.read(new File("PacmanBoard.png")).getScaledInstance((224)*3, (248)*3,Image.SCALE_SMOOTH);
			//tile length and width is 8*3
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	static final int TILE_D=24;

	Boolean first=true;
	
	
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
		
			makeTileArray();
		
	}
	
	public void drawBoard(Graphics2D g){	

	g.drawImage(board, 0, 0, null);
		for(int i=0;i<tileArray.length;i++){
			for(int x=0;x<tileArray[i].length;x++){
				/*if(tileArray[i][x].isWall){
					g.setColor(Color.BLUE);
					g.drawRect(tileArray[i][x].x, tileArray[i][x].y, TILE_D, TILE_D);
				}
				else{
				g.setColor(Color.MAGENTA);
				g.drawRect(tileArray[i][x].x, tileArray[i][x].y, TILE_D, TILE_D);
				}*/
				if(tileArray[i][x].isFood){
					g.setColor(Color.WHITE);
					g.drawOval(tileArray[i][x].centerX-1, tileArray[i][x].centerY-1, 5, 5);
					g.fillOval(tileArray[i][x].centerX-1, tileArray[i][x].centerY-1, 5, 5);
					
				}
				if(tileArray[i][x].isGhostKiller){
					g.setColor(Color.WHITE);
					g.drawOval(tileArray[i][x].centerX-1, tileArray[i][x].centerY-1, 10, 10);
					g.fillOval(tileArray[i][x].centerX-1, tileArray[i][x].centerY-1, 10, 10);
				}
			}
		}	
	
		for(Tile tile: Blinky.finalPath){
			g.setColor(Color.ORANGE);
			g.fillRect(tile.centerX, tile.centerY, Board.TILE_D, Board.TILE_D);
		}
		Blinky.finalPath.clear();
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
			size += 10;
			
		} else {
			size -= 15;
		
		}
		
		
		
	}
}
