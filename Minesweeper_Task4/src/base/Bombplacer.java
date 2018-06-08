package base;

import java.util.List;

import interfaces.Difficulty;
import loader.PluginLoader;

public class Bombplacer {
	
	private Bombplacer() {
		
	}
	
	public static int placebombs (Cell[][] gameField) {
		int width = gameField.length;
		int height = gameField[0].length;
		int numberOfCells = width * height;
		int numberOfBombs;
		List<Difficulty> list = PluginLoader.load(Difficulty.class);
		if (list.size() != 1)
			System.exit(-1);
		numberOfBombs = list.get(0).getNumberOfBombs(numberOfCells);
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
