
public class GameArena {
	
	private long timeInMS;
	
	public void increaseScore() {
		original();
		if (0 != timeInMS) {
			if ((System.currentTimeMillis() - timeInMS) <= 15000) {
				++score;				
			}
		}
		timeInMS = System.currentTimeMillis();
	}
	
	public int getScore() {
		return score;
	}
}
