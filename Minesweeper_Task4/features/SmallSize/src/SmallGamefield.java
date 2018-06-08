import base.Cell;
import interfaces.Gamefield;

public class SmallGamefield implements Gamefield {

	@Override
	public Cell[][] getField() {
		return new Cell[9][9];
	}
}
