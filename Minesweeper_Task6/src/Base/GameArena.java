package Base;
import java.util.Observable;

public class GameArena extends Observable{
	
	private Cell[][] field;
	private WinChecker checker;
	private Solver solver;
	
	public GameArena() {
		int numberBombs;
		field = (new Size()).getGamefield();
		solver = new Solver();
		for (int i = 0; i < field.length; ++i)
			for (int j = 0; j < field[i].length; ++j)
				field[i][j] = new Cell();
		numberBombs = Bombplacer.placebombs(field);
		setCellNumbers();
		checker = new WinChecker(field.length, field[0].length, numberBombs);
		
	}
	
	private void setCellNumbers() {
		for (int x = 0; x < field.length; ++x) {
			for (int y = 0; y < field[x].length; ++y) {
				if (CellState.CELL_BOMB == field[x][y].getCellstate()) {
					increaseCellNumbersAround(x, y);
				}
			}
		}
	}
	
	private void increaseCellNumbersAround(int x, int y) {
		for (int i = (x - 1); i <= (x + 1); ++i) {
			for (int j = (y - 1); j <= (y + 1); ++j) {
				if ((i > (-1)) && (i < field.length) &&//Au?erhalb des Spielbereichs
					(j > (-1)) && (j < field[i].length) &&//Au?erhalb des Spielbereichs
					/*((i != x) || (j != y)) && *///Eigene Zelle nicht betrachten
					(CellState.CELL_BOMB != field[i][j].getCellstate())) {
					field[i][j].increaseBombsAround();
				}
			}
		}
	}
	
	public GAME_STATE uncoverCellAt(int x, int y) {
		if (x < 0 || x >= field.length ||
				y < 0 && y >= field[0].length) {
			return GAME_STATE.GAME_FORBIDDEN_PLAY;
		} else if (!field[x][y].isCovered()) {
			return GAME_STATE.GAME_FORBIDDEN_PLAY;
		} else if (CellState.CELL_BOMB == field[x][y].getCellstate()) {
			return GAME_STATE.GAME_LOST;
		}
		if (field[x][y].getBombsAround() == 0) {
			int uncoveredCells = 0;
			uncoveredCells = solver.solve(field, x, y);
			checker.increasedUncoveredCells(uncoveredCells);
		} else {
			field[x][y].setUncovered();
			checker.increasedUncoveredCells(1);
		}
		setChanged();
		notifyObservers(this);
		clearChanged();
		if (checker.isGameWon()) {
			return GAME_STATE.GAME_WON;
		} 
		return GAME_STATE.GAME_RUNNING;
	}
	
	@Override
	public String toString() {
		int columnNumber = 0;
		StringBuilder builder = new StringBuilder();
		builder.append("  ");
		builder.append('|');
		for (int i = 0; i < field.length; ++i) {
			if (i < 10)
				builder.append(columnNumber);
			else
				builder.append((char)('A' + (columnNumber-10)));
			builder.append('|');
			++columnNumber;
		}
		builder.append('x');
		builder.append('\n');
		builder.append('\n');
		for (int i = 0; i < field[0].length; ++i) {
			builder.append(i);
			if (i < 10)
				builder.append(' ');
			builder.append('|');
			for (int j = 0; j < field.length; ++j) {
				if (field[j][i].isCovered()) {
					builder.append('-');
				} else {
					builder.append(field[j][i].toString());
				}
				
				builder.append("|");
			}
			builder.append('\n');
		}
		builder.append("y");
		return builder.toString();
	}
}
