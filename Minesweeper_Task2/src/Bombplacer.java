import properties.PropertyManager;

/**
 * TODO description
 */
public class Bombplacer {
	private Bombplacer() {
		
	}
	
	public static void placebombs (GamearenaCell[][] gameField) {
		int width = gameField.length;
		int height = gameField[0].length;
		int numberOfCells = width * height;
		int numberOfBombs = 0;
		if (PropertyManager.getProperty("Easy")) {
			numberOfBombs = numberOfCells / 9;
		} else if (PropertyManager.getProperty("Middle")) {
			numberOfBombs = (int)(numberOfCells * 0.15625);
		} else if (PropertyManager.getProperty("Hard")) {
			numberOfBombs = (int)(numberOfCells * 0.20625);
		}
		--width;
		--height;
		int x, y;
		for (int i = (numberOfBombs); i > 0; --i ) {
			x = (int) (Math.round(Math.random() * width));
			y = (int) (Math.round(Math.random() * height));
			if (CellState.CELL_BOMB == gameField[x][y].getCellstate()) {
				++i;
			} else {
				gameField[x][y].setBomb();
			}
		}
	}
}