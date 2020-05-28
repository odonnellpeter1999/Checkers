import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Checkers");
		Action a = new Action();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.getContentPane().addMouseListener(a);
		Game game = new Game();
		GUI g = new GUI(game);
		a.setGUI(g);
		f.add(g);
		f.setSize(495, 479);
		f.setVisible(true);
		g.repaint();
		
	}

}
