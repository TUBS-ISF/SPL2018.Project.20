import interfaces.Difficulty;

public class EasyDifficulty implements Difficulty {

	@Override
	public int getNumberOfBombs(int numberOfCells) {
		return numberOfCells / 9;
	}

}
