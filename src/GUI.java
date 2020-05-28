import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JPanel {

	Game game;
	private Image king;

	GUI(Game game) {
		this.king = new ImageIcon("C:\\Users\\Owner\\Desktop\\Software\\King.png").getImage();
		this.game = game;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 1000, 1000);

		g.setColor(Color.WHITE);
		g.fillRect(80, 40, 400, 400);
		g.setColor(Color.BLACK);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j += 2) {

				if (i % 2 == 0) {
					g.fillRect(80 + (i * 50), 40 + (j * 50), 50, 50);
				} else {
					g.fillRect(80 + ((i) * 50), 40 + ((j + 1) * 50), 50, 50);
				}

			}
		}

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (game.getPiece(i, j) != null && (game.getPiece(i, j).getColor() == Piece.Color.Black)) {
					g.setColor(Color.BLUE);
					g.fillOval(92 + (i * 50), 52 + (j * 50), 26, 26);
				} else if (game.getPiece(i, j) != null && (game.getPiece(i, j).getColor() == Piece.Color.White)) {
					g.setColor(Color.RED);
					g.fillOval(92 + ((i) * 50), 52 + ((j) * 50), 26, 26);
				}

				if (game.getPiece(i, j) != null && game.getPiece(i, j).isKing() == true) {
					g.drawImage(king, 97 + (i * 50), 59 + (j * 50), this);

				}

				if (game.getPiece(i, j) != null && game.getPiece(i, j).getSelected()) {
					g.setColor(Color.GREEN);
					g.fillOval(99 + (i * 50), 59 + (j * 50), 13, 13);
				}
				

			}
		}
		g.setColor(Color.BLACK);
		Font font1 = new Font("San seif", Font.BOLD, 20);
		g.setFont(font1);
		g.drawString("Turn:", 10, 25);
		Font font2 = new Font("San seif", Font.BOLD, 14);
		g.setFont(font2);
		g.drawString("Pieces", 10, 70);
		g.drawString("Left:",20,90);
		
		g.drawString("Blue: " + game.getplayer(1).getPiecesLeft(), 10, 130);
		g.drawString("Red:  " + game.getplayer(2).getPiecesLeft(), 10, 160);
		if (game.whosTurn().getColor() == Piece.Color.Black) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.RED);
		}

		g.fillOval(65, 9, 20, 20);
	}
}