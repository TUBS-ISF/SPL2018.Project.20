
public class GameArena {
	
	private double 	scoreIncreaseFactor;
	private long 	timeInMS;
	
	public GameArena() {
		scoreIncreaseFactor = 1.0;
	}
	
	public void increaseScore() {	
		if (0 != timeInMS) {
			if ((System.currentTimeMillis() - timeInMS) <= 15000) {
				scoreIncreaseFactor += 0.125;				
			} else if (scoreIncreaseFactor >= 1.0000001){
				scoreIncreaseFactor -= 0.125;
			}
		}
		original();
		timeInMS = System.currentTimeMillis();
		score = (int)(score * scoreIncreaseFactor);	
	}
	
	public int getScore() {
		return score;
	}
}
