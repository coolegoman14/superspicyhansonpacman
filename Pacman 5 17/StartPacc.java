import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class StartPacc {
	
	private JFrame frame = new JFrame("PAC-MAN");
	private JPanel panel;
	Timer timer = new Timer(50,null);
	Board board= new Board();
	Pacman pacman= new Pacman();
	Blinky blinky= new Blinky(pacman);
	Pinky pinky= new Pinky(pacman);
	
	

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
				board.drawBoard((Graphics2D) g);
				
				pacman.draw((Graphics2D) g);
				blinky.draw((Graphics2D) g);
				pinky.draw((Graphics2D) g);
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
		
		if (pacman.direction!=pacman.userDirection && pacman.canMove(pacman.userDirection)) {
			pacman.direction=pacman.userDirection;
			pacman.changeAngleAttributes(pacman.direction);

		}
		pacman.move();
		pacman.doMouthAnimation();
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
				pacman.userDirection=Pacman.Direction.LEFT;
				if(pacman.canMove(Pacman.Direction.LEFT)){
					pacman.direction=Pacman.Direction.LEFT;
					pacman.changeAngleAttributes(Pacman.Direction.LEFT);
					
				}
			}
		});
		panel.getActionMap().put("right",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pacman.userDirection=Pacman.Direction.RIGHT;
				if(pacman.canMove(Pacman.Direction.RIGHT)){
					pacman.direction=Pacman.Direction.RIGHT;
					pacman.changeAngleAttributes(Pacman.Direction.RIGHT);
					
				}
				
			}
		});
		panel.getActionMap().put("up",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pacman.userDirection=Pacman.Direction.UP;
				if(pacman.canMove(Pacman.Direction.UP)){
					pacman.direction=Pacman.Direction.UP;
					pacman.changeAngleAttributes(Pacman.Direction.UP);
					
				}
				
			}
		});
		panel.getActionMap().put("down",new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pacman.userDirection=Pacman.Direction.DOWN;
				if(pacman.canMove(Pacman.Direction.DOWN)){
					pacman.direction=Pacman.Direction.DOWN;
					pacman.changeAngleAttributes(Pacman.Direction.DOWN);
				}
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
		panel.setBackground(Color.black);
		this.setUpKeyMappings();
		JButton button = new JButton("hi");
		button.setSize(50, 50);
		//button.setLocation(1400, 200);
		panel.add(new JButton("hiii"));
	}

}
