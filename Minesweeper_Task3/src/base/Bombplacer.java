package base;

public class Bombplacer {
	private Bombplacer() {
		
	}
	
	public static int placebombs (Cell[][] gameField) {
		int width = gameField.length;
		int height = gameField[0].length;
		int numberOfCells = width * height;
		int numberOfBombs;
		//#ifdef Easy
			numberOfBombs = numberOfCells / 9;
		//#elifdef Middle
//@		numberOfBombs = (int)(numberOfCells * 0.15625);
		//#elifdef Hard
//@		numberOfBombs = (int)(numberOfCells * 0.20625);
		//#endif
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
		return numberOfBombs;
	}
}
