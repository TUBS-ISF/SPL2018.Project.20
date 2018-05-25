package base;


enum CellState{
	 CELL_BOMB,
	 CELL_BOMB_AROUND,
	 CELL_FREE
 }
public class Cell {
	private boolean isCovered;
	private int numBombsAround;
	private CellState cellstate;

	public Cell() {
		isCovered = true;
		numBombsAround = 0;
		cellstate = CellState.CELL_FREE;
	}
	
	public Cell(int numBombsAround) {
		this.numBombsAround = numBombsAround;
	}
	
	public void setUncovered() {
		isCovered = false;
	}
	
	public boolean isCovered() {
		return isCovered;
	}
	
	public int getBombsAround() { 
		return numBombsAround;
	}
	
	public void increaseBombsAround() {
		if (cellstate != CellState.CELL_BOMB) {
			cellstate = CellState.CELL_BOMB_AROUND;
			++numBombsAround;
		}
	}
	
	public void setBombsAround(int numBombsAround) {
		if (numBombsAround > 0) {
			cellstate = CellState.CELL_BOMB_AROUND;
		} else {
			cellstate = CellState.CELL_FREE;
		}
		this.numBombsAround = numBombsAround;
	}
	
	public CellState getCellstate() {
		return cellstate;
	}

	public void setBomb() {
		this.cellstate = CellState.CELL_BOMB;
	}
	
	@Override
	public String toString() {
		if (CellState.CELL_BOMB == cellstate) {
			return "X";
		}
		return String.valueOf(numBombsAround);
	}
}
