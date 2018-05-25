package base;
//#ifdef Iterative
//@import java.util.LinkedList;
//@import java.util.Queue;
//#endif
public class Solver {
	//#ifdef Iterative
//@	static class CellWithPosition {
//@		private Cell c;
//@		private int x;
//@		private int y;
//@		
//@		public CellWithPosition (Cell c, int x, int y) {
//@			this.c = c;
//@			this.x = x;
//@			this.y = y;
//@		}
//@
//@		public Cell getC() {
//@			return c;
//@		}
//@		public int getX() {
//@			return x;
//@		}
//@		public int getY() {
//@			return y;
//@		}				
//@	}
	//#endif
	
	private Solver() {

	}
	
	private static int checkCellsAround(Cell[][] field, int x, int y
	//#ifdef Iterative
//@			, Queue<CellWithPosition> iterQueue
	//#elifdef Recursive
			, int numberOfUncoveredBombs			
	//#endif
			) {
		//#ifdef Iterative
//@		int numberOfUncoveredBombs = 0;
		//#endif
		for (int i = (x - 1); i <= (x + 1); ++i) {
			for (int j = (y - 1); j <= (y + 1); ++j) {
				if (i >= 0 && i < field.length &&
						j >= 0 && j < field[0].length &&
						field[i][j].isCovered()) {
					++numberOfUncoveredBombs;
					field[i][j].setUncovered();
					if (0 == field[i][j].getBombsAround()) {
						//#ifdef Iterative
//@						iterQueue.add(new CellWithPosition(field[i][j], i, j));
						//#elifdef Recursive			
						numberOfUncoveredBombs = checkCellsAround(field, i, j, numberOfUncoveredBombs);
						//#endif
					}
				}
			}
		}
		return numberOfUncoveredBombs;
	}
	
	//#ifdef Iterative
//@	public static int solveIterative(Cell[][] field, int x, int y) {	
//@		int numUncoveredCells = 0;
//@		Queue<CellWithPosition> iterQueue = new LinkedList<>();
//@		iterQueue.add(new CellWithPosition(field[x][y], x, y));
//@		while (!iterQueue.isEmpty()) {
//@			CellWithPosition cWithPos = iterQueue.poll();
//@			x = cWithPos.getX();
//@			y = cWithPos.getY();
//@			numUncoveredCells += checkCellsAround(field, x, y, iterQueue);
//@//			for (int i = (x - 1); i <= (x + 1); ++i) {
//@//				for (int j = (y - 1); j <= (y + 1); ++j) {
//@//					if (i >= 0 && i < field.length &&
//@//							j >= 0 && j < field[0].length &&
//@//							field[i][j].isCovered()) {
//@//						field[i][j].setUncovered();
//@//						if (0 == field[i][j].getBombsAround()) {
//@//							iterQueue.add(new CellWithPosition(field[i][j], i, j));
//@//						}
//@//					}
//@//				}
//@//			}
//@		}
//@		return numUncoveredCells;
//@	}
	//#endif
	//#ifdef Recursive
	public static int solveRecusive(Cell[][] field, int x, int y) {
		int numUncoveredCells = 0;
//		for (int i = (x - 1); i <= (x + 1); ++i) {
//			for (int j = (y - 1); j <= (y + 1); ++j) {
//				if (i >= 0 && i < field.length &&
//						j >= 0 && j < field[0].length &&
//						field[i][j].isCovered()) {
//					field[i][j].setUncovered();
//					if (0 == field[i][j].getBombsAround()) {
//						solveRecusive(field, i, j);
//					}
//				}
//			}
//		}
		numUncoveredCells = checkCellsAround(field, x, y, numUncoveredCells);
		return numUncoveredCells;
	}
	//#endif
}
