
public aspect TimeBasedScore {
	
	private long timeInMS;
	
	pointcut TB_Score(Score s) : execution(void Score.increaseScore()) && target(s);
	
	after(Score s) : TB_Score(s) {
		if (0 != timeInMS) {
			if ((System.currentTimeMillis() - timeInMS) <= 15000) {
				++s.score;				
			}
		}
		timeInMS = System.currentTimeMillis();		
	}
}