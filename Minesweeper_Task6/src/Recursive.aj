
import Base.Cell;

public aspect Recursive {

	pointcut RecursiveSolver(Cell[][] field, int x, int y) :
		execution(int Base.Solver.solve(Cell[][], int, int)) && args(field, x, y);
	
	int around(Cell[][] field, int x, int y) : RecursiveSolver(field, x, y) {
		int numUncoveredCells = 0;
		numUncoveredCells = checkCellsAround(field, x, y, numUncoveredCells);
		return numUncoveredCells;
	}
	
	private int checkCellsAround(Cell[][] field, int x, int y, int numberOfUncoveredBombs) {
		for (int i = (x - 1); i <= (x + 1); ++i) {
			for (int j = (y - 1); j <= (y + 1); ++j) {
				if (i >= 0 && i < field.length &&
						j >= 0 && j < field[0].length &&
						field[i][j].isCovered()) {
					++numberOfUncoveredBombs;
					field[i][j].setUncovered();
					if (0 == field[i][j].getBombsAround()) {		
						numberOfUncoveredBombs = checkCellsAround(field, i, j, numberOfUncoveredBombs);
					}
				}
			}
		}
		return numberOfUncoveredBombs;
	}
	
	
}
