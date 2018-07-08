

public aspect Easy {
	
	pointcut EasyDifficulty(int numberOfCells) : 
		execution(int Base.Difficulty.getNumberOfBombs(int)) && args(numberOfCells);
	
	int around(int numberOfCells) : EasyDifficulty(numberOfCells) {
		return (int)(numberOfCells / 9);
	}
}