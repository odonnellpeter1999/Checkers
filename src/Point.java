
public class Point {
	
	private int row;
	private int column;
	
	
	Point(int row,int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public String toString() {
		return row + "," + column;
	}

}
