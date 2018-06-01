import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartScreen {

	private JFrame frame = new JFrame("PAC-MAN");
	JPanel panel;
	static Image logo;
	static Image background;
	boolean isIncreasing;
	Image startGameButton;
	int logos=0;
	int logoMax=20;
	int logoMin=-10;
	public StartScreen() {
		try {
			
			logo= ImageIO.read(new File("PacmanLogo.png")).getScaledInstance(1224, 653,Image.SCALE_SMOOTH);
			background= ImageIO.read(new File("Background.png"));
			startGameButton=ImageIO.read(new File("newGameButton.png"));
			//tile length and width is 8*3
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	/*public static void main(String[] args){
		new StartScreen().startGame();
	}
	*/
	private void startGame() {
		
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				displayLogo((Graphics2D) g);
			}
		};
		setUpGame();
}
	public void displayLogo(Graphics2D g){
		g.drawImage(background, 0, 0, null);
		logoAnimation();
		g.drawImage(logo, 350, logos, null);
		g.drawImage(startGameButton, 350, 500, null);
		panel.repaint();
	}
	
	public void logoAnimation(){
		if (logos == logoMax) {
			isIncreasing = false;
		} else if (logos == logoMin) {
			isIncreasing = true;
		}
		if (isIncreasing) {
			logos += 1;
		} else {
			logos -= 1;	
		}
	}
	private void setUpGame(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setPreferredSize(new Dimension(1500,800));
		frame.pack();
		frame.setVisible(true);
	}

}
