
public class Move {
	
	private Point New;
	private Point old;
	
	Move(Point New,Point old) {
		this.New = New;
		this.old = old;
	}

	public Point getNew() {
		return New;
	}

	public Point getOld() {
		return old;
	}
	
	public String toString() {
		return old.getRow() + "," + old.getColumn() + "->" + New.getRow() + "," + New.getColumn();
	}
}
