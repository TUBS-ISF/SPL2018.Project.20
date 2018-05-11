import properties.PropertyManager;

/**
 * TODO description
 */
public class Gamearena {
	private GamearenaCell[][] field;
	
	public static void main(String[] args) {
		System.out.println(new Gamearena());
	}
	
	public Gamearena() {
		if (PropertyManager.getProperty("SmallSize")) {
			field = new GamearenaCell[9][9];
		} else if (PropertyManager.getProperty("MiddleSize")) {
			field = new GamearenaCell[16][16];
		} else if (PropertyManager.getProperty("BigSize")) {
			field = new GamearenaCell[30][16];
		}
		
		for (int i = 0; i < field.length; ++i)
			for (int j = 0; j < field[i].length; ++j)
				field[i][j] = new GamearenaCell();

		Bombplacer.placebombs(field);
		setCellNumbers();
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
				if ((i > (-1)) && (i < field.length) &&//Außerhalb des Spielbereichs
					(j > (-1)) && (j < field[i].length) &&//Außerhalb des Spielbereichs
					/*((i != x) || (j != y)) && *///Eigene Zelle nicht betrachten
					(CellState.CELL_BOMB != field[i][j].getCellstate())) {
					field[i][j].increaseBombsAround();
				}
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int numberOfCells = field.length * field[0].length;
		builder.append("Minesweeper\n");
		builder.append("Größe:" + field.length + "x" + field[0].length);
		builder.append("\nAnzahl Bomben:");
		if (PropertyManager.getProperty("Easy")) {
			builder.append(numberOfCells / 9);
		} else if (PropertyManager.getProperty("Middle")) {
			builder.append((int)(numberOfCells * 0.15625));
		} else if (PropertyManager.getProperty("Hard")) {
			builder.append((int)(numberOfCells * 0.20625));
		}
		builder.append("\n(X) Bombe | (n) Anzahl der Bomben um das Feld\n");
		builder.append("Gamearena:\n");
		for (int i = 0; i < field[0].length; ++i) {
			for (int j = 0; j < field.length; ++j) {
				builder.append(' ');
				builder.append(field[j][i].toString());
				builder.append(" |");
			}
			builder.append('\n');
		}
		return builder.toString();
	}
}