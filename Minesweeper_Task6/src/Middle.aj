
public aspect Middle {
	
	pointcut MiddleDifficulty(int numberOfCells) : 
		execution(int Base.Difficulty.getNumberOfBombs(int)) && args(numberOfCells);
	
	int around(int numberOfCells) : MiddleDifficulty(numberOfCells) {
		return (int)(numberOfCells * 0.15625);
	}
}

