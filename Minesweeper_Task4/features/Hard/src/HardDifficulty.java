import interfaces.Difficulty;

public class HardDifficulty implements Difficulty {

	@Override
	public int getNumberOfBombs(int numberOfCells) {
		return (int)(numberOfCells * 0.20625);
	}

}
