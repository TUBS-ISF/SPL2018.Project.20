import Base.GAME_STATE;

public aspect Score {
	
	declare precedence : *, Score, TimeBasedScore, WeightedScore;
	
	public int score;
	
	public void increaseScore() {
		++score;
	}
	
	public int getScore() {
		return score;
	}
	
	pointcut UncoverCell() : execution(GAME_STATE Base.GameArena.uncoverCellAt(int, int));
	pointcut GameArenaToString() : execution(String Base.GameArena.toString());
	
	before() : UncoverCell() { 
		increaseScore();
	}
	
	String around() : GameArenaToString() {
		String str = proceed();
		StringBuilder builder = new StringBuilder(str);
		builder.append("\nYour current Score:");
		builder.append(score);
		return builder.toString();
	}
}