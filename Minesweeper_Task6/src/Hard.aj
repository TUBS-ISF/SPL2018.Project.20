
public aspect Hard {
	
	pointcut HardDifficulty(int numberOfCells) : 
		execution(int Base.Difficulty.getNumberOfBombs(int)) && args(numberOfCells);
	
	int around(int numberOfCells) : HardDifficulty(numberOfCells) {
		return (int)(numberOfCells * 0.20625);
	}
}

