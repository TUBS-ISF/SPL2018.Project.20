

import java.util.LinkedList;
import java.util.Queue;

import Base.Cell;
import Base.CellWithPosition;

public aspect Iterative {
	
	pointcut IterativeSolver(Cell[][] field, int x, int y) :
		execution(int Base.Solver.solve(Cell[][], int, int)) && args(field, x, y);
	
	int around(Cell[][] field, int x, int y) : IterativeSolver(field, x, y) {
		int numUncoveredCells = 0;
		Queue<CellWithPosition> iterQueue = new LinkedList<CellWithPosition>();
		iterQueue.add(new CellWithPosition(field[x][y], x, y));
		while (!iterQueue.isEmpty()) {
			CellWithPosition cWithPos = iterQueue.poll();
			x = cWithPos.getX();
			y = cWithPos.getY();
			numUncoveredCells += checkCellsAround(field, x, y, iterQueue);
		}
		return numUncoveredCells;
	}
	
	/*static class CellWithPosition {
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
	}*/
	
	private int checkCellsAround(Cell[][] field, int x, int y, Queue<CellWithPosition> iterQueue) {
		int numberOfUncoveredBombs = 0;
		for (int i = (x - 1); i <= (x + 1); ++i) {
			for (int j = (y - 1); j <= (y + 1); ++j) {
				if (i >= 0 && i < field.length &&
						j >= 0 && j < field[0].length &&
						field[i][j].isCovered()) {
					++numberOfUncoveredBombs;
					field[i][j].setUncovered();
					if (0 == field[i][j].getBombsAround()) {
						iterQueue.add(new CellWithPosition(field[i][j], i, j));
					}
				}
			}
		}
		return numberOfUncoveredBombs;
	}
}
