
public aspect WeightedScore {
	
	private double 	scoreIncreaseFactor;
	private long 	timeInMS; 
	
	pointcut WS_ScoreExtension(Score score) :
		execution(void Score.increaseScore()) && target(score);
	
	after() : execution(Base.GameArena.new()){
		scoreIncreaseFactor = 1.0;
	}
	
	after(Score s) : WS_ScoreExtension(s) {
		if (0 != timeInMS) {
			if ((System.currentTimeMillis() - timeInMS) <= 15000) {
				scoreIncreaseFactor += 0.125;				
			} else if (scoreIncreaseFactor >= 1.0000001){
				scoreIncreaseFactor -= 0.125;
			}
		}
		timeInMS = System.currentTimeMillis();
		s.score = (int)(s.score * scoreIncreaseFactor);			
	}
}