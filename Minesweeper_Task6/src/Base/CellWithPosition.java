package Base;

public class CellWithPosition {
	private Cell c;
	private int x;
	private int y;
	
	public CellWithPosition (Cell c, int x, int y) {
		this.c = c;
		this.x = x;
		this.y = y;
	}

	public Cell getC() {
		return c;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}	
}
