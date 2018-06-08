import base.Cell;
import interfaces.Gamefield;

public class MiddleGamefield implements Gamefield {

	@Override
	public Cell[][] getField() {
		return new Cell[16][16];
	}

}
