import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Action extends MouseAdapter {

	int clicks = 0;
	int old_x = 0;
	int old_y = 0;
	int new_x = 0;
	int new_y = 0;
	GUI g;

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		if (g.game.whosTurn() == g.game.getplayer(2)) {
			if (clicks % 2 == 0) {
				old_x = e.getX() - 80;
				old_y = e.getY() - 40;
			if(g.game.getPiece(old_x / 50, old_y / 50) != null) {
 				if (g.game.getplayer(2).getColor() == g.game.getPiece(old_x / 50, old_y / 50).getColor()) {
					g.game.getPiece(old_x / 50, old_y / 50).setSelected(true);
					g.repaint();
					clicks++;
 				}
				}
			
			} else {
				
				new_x = e.getX() - 80;
				new_y = e.getY() - 40;
				
				if (old_x / 50 != new_x / 50 && old_y / 50 != new_y / 50) {
					g.game.movePiece(new_x / 50, new_y / 50, old_x / 50, old_y / 50);
					g.game.getPiece(new_x / 50, new_y / 50).setSelected(false);
					g.repaint();
					clicks--;

				} else {
					g.game.getPiece(old_x / 50, old_y / 50).setSelected(false);
					g.repaint();
					clicks--;
				}
				

			}
	} else {
			
			Game dupGame = new Game();
			dupGame.setPieces(g.game.getPieces());
			Move newMove = dupGame.getComp().bestMoveforBoard(dupGame);
			g.game.movePiece(newMove.getNew().getRow(),newMove.getNew().getColumn(),newMove.getOld().getRow(),newMove.getOld().getColumn());
			g.repaint();
			
			
		}

	}

	public void setGUI(GUI gui) {
		this.g = gui;
	}

}
