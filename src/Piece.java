
public class Piece {
	
	enum Color {
		Black,White
	}
	
	
	
	private Color color;
	
	private int position_x;
	private int position_y;
	
	private boolean king;
	private boolean selected;

	public Piece(Color color, int position_x, int position_y) {
		super();
		this.color = color;
		this.position_x = position_x;
		this.position_y = position_y;
		this.king = false;
		this.selected =  false;
		
	}

	public Color getColor() {
		return color;
	}

	public int getPosition_x() {
		return position_x;
	}

	public void setPosition_x(int position_x) {
		this.position_x = position_x;
	}

	public int getPosition_y() {
		return position_y;
	}

	public void setPosition_y(int position_y) {
		this.position_y = position_y;
	}

	public boolean isKing() {
		return king;
	}

	public void setKing(boolean king) {
		this.king = king;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean getSelected() {
		return this.selected;
	}
	
	public Piece newPiece(Color color, int position_x, int position_y) {
		return new Piece(color,position_x,position_y);
	}
	

}
