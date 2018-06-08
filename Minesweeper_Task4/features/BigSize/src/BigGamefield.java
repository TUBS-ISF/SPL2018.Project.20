import base.Cell;
import interfaces.Gamefield;

public class BigGamefield implements Gamefield {

	@Override
	public Cell[][] getField() {
		return new Cell[30][16];
	}

}
