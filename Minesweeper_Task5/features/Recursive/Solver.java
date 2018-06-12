import java.util.LinkedList;
import java.util.Queue;

public class Solver{
	
	private static int checkCellsAround(Cell[][] field, int x, int y, int numberOfUncoveredBombs) {
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

	public int solve(Cell[][] field, int x, int y) {
		int numUncoveredCells = 0;
		numUncoveredCells = checkCellsAround(field, x, y, numUncoveredCells);
		return numUncoveredCells;
	}


}
