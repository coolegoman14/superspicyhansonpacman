import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Main extends JPanel{

	int SIZE_PANEL = 800;
	Map map = new Map();
	int mapStart = 0;
Timer t;

	public static void main(String[] args) {

		JFrame frame = new JFrame("This doesn't work!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main main = new Main();
		main.setUp();
		frame.add(main);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	
	
	
	public void setUp() {
		map.makeObjects();
	}



	public Main() {
		this.setPreferredSize(new Dimension(this.SIZE_PANEL,SIZE_PANEL));
		t= new Timer(500, null);
		
		// You can make your own ActionListeners so that you can later remove them if you want.
		ActionListener projMover =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				tick();
				
				
//				
//				List<String> moves = map.canGo();
//				if(moves.size() > 0) {
//				for(String a: moves) {
//					if(a.equalsIgnoreCase(map.pac.direction)) {
//						System.out.println("yup");
//						map.pac.move();
//					}
//				}
//				}
//				
//				if(map.canGo(map.pac.direction)) {
//					
//				}
				
				repaint();
			}

			private void tick() {
				// TODO Auto-generated method stub
				
				
				
					if(map.canMove(map.pac.direction)){
						
				map.pac.move();
				
			}
			}
		};
	
		
		
		
		t.addActionListener(projMover);// this adds the ProjectMover to the list so that every time the 
		t.start();
		
		
		
		
		this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"moveRight"); 
		getActionMap().put("moveRight",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				map.pac.direction = "right";
				repaint();
			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("UP"),"moveUp"); 
		getActionMap().put("moveUp",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				map.pac.direction = "up";
				repaint();
			}
		});

		this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"moveDown"); 
		getActionMap().put("moveDown",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				map.pac.direction = "down";
				repaint();
			}
		});


		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"moveLeft"); 
		getActionMap().put("moveLeft",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				map.pac.direction = "left";
				repaint();
			}
		});



		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				System.out.println("You just entered!! "+arg0);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				System.out.println("You just exited!! "+arg0);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				int y =arg0.getY();
				int x = arg0.getX();
				System.out.println("You just clicked: "+ x + " " + y + " " + arg0);
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.out.println("mouse released " +arg0);
				int y = arg0.getY();
				int x = arg0.getX();
				System.out.println("got this far");
			}

		});
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		map.draw(g);
	
	}

}