import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class StartPacc {
	
	private JFrame frame = new JFrame("PAC-MAN");
	private JPanel panel;
	Timer timer = new Timer(50,null);
	int speed=6;
	Board board= new Board();
	Pacman pacman= new Pacman();
	//Ghost ghost= new Ghost(pacman);

	public StartPacc() {
		
	}
	public static void main(String[] args){
		new StartPacc().startGame();
	}
	
	private void startGame() {
		
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				//board.drawBoard((Graphics2D) g);
				pacman.draw((Graphics2D) g);
				ghost.drawGhosts((Graphics2D) g);
			}
		};
		setUpGame();
		setUpKeyMappings();
		
		
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tick();
			}
		});
		timer.start();
	}
	
	protected void tick() {
		
		if(pacman.direction==Pacman.Direction.RIGHT){
			//board.board[pacman.y][pacman.x]=0;
			pacman.x+=speed;
			//board.board[pacman.y][pacman.x]=2;
			if(pacman.startAngle==70){
				pacman.isIncreasing=false;
			}
			else if(pacman.startAngle==0){
				pacman.isIncreasing=true;
			}
		}
		
		if(pacman.direction==Pacman.Direction.LEFT){
			//board.board[pacman.y][pacman.x]=0;
			pacman.x-=speed;
			//board.board[pacman.y][pacman.x]=2;
			if(pacman.startAngle==250){
				pacman.isIncreasing=false;
			}
			else if(pacman.startAngle==180){
				pacman.isIncreasing=true;
			}
			
		}
		if(pacman.direction==Pacman.Direction.UP){
			//board.board[pacman.y][pacman.x]=0;
			pacman.y-=speed;
			//board.board[pacman.y][pacman.x]=2;
			if(pacman.startAngle==160){
				pacman.isIncreasing=false;
			}
			else if(pacman.startAngle==90){
				pacman.isIncreasing=true;
			}
		}
		
		if(pacman.direction==Pacman.Direction.DOWN){
			//board.board[pacman.y][pacman.x]=0;
			pacman.y+=speed;
			//board.board[pacman.y][pacman.x]=2;
			
			if(pacman.startAngle==340){
				pacman.isIncreasing=false;
			}
			else if(pacman.startAngle==270){
				pacman.isIncreasing=true;
			}
		}
		
		if(pacman.isIncreasing){
			pacman.startAngle+=10;
			pacman.arcAngle-=20;
			}
			else{
				pacman.startAngle-=10;
				pacman.arcAngle+=20;
			}
		panel.repaint();
	}
	
	private void setUpKeyMappings() {

		panel.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		panel.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
		panel.getInputMap().put(KeyStroke.getKeyStroke("UP"),"up");
		panel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"down");
		
		panel.getActionMap().put("left",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pacman.direction=Pacman.Direction.LEFT;
				pacman.arcAngle=360;
				pacman.startAngle=180;
			}
		});
		panel.getActionMap().put("right",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pacman.direction=Pacman.Direction.RIGHT;
				pacman.arcAngle=360;
				pacman.startAngle=0;
				
			}
		});
		panel.getActionMap().put("up",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pacman.direction=Pacman.Direction.UP;
				pacman.arcAngle=360;
				pacman.startAngle=90;
				
			}
		});
		panel.getActionMap().put("down",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pacman.direction=Pacman.Direction.DOWN;
				pacman.arcAngle=360;
				pacman.startAngle=270;
			}
		});
		panel.requestFocusInWindow();
	}
	
	private void setUpGame(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setPreferredSize(new Dimension(1500,800));
		frame.pack();
		frame.setVisible(true);
		panel.setBackground(Color.LIGHT_GRAY);
		this.setUpKeyMappings();
	}

}
