
public class Player {
	
	private Piece.Color color;
	private String Name;
	private int piecesLeft;
	private boolean yourTurn;
	public Player(Piece.Color color, String name) {
		super();
		this.color = color;
		Name = name;
		this.piecesLeft = 12;
	}
	public Piece.Color getColor() {
		return color;
	}
	public String getName() {
		return Name;
	}
	public int getPiecesLeft() {
		return piecesLeft;
	}
	
	public void removePiece() {
		this.piecesLeft--;
	}
	
	public void addPiece() {
		this.piecesLeft++;
	}
	
	public void setTurn(boolean state) {
		this.yourTurn = state;
	}
	
	public boolean getTurn() {
		return this.yourTurn;
	}

	
	
}
