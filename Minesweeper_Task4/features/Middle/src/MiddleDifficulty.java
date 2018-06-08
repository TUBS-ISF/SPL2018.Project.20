import interfaces.Difficulty;

public class MiddleDifficulty implements Difficulty {

	@Override
	public int getNumberOfBombs(int numberOfCells) {
		return (int)(numberOfCells * 0.15625);
	}
}
