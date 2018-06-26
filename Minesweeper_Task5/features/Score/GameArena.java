
public class GameArena {
	
	private int score;
	
	public void increaseScore() {
		++score;
	}
	
	public int getScore() {
		return score;
	}
	
	public GAME_STATE uncoverCellAt(int x, int y) {
		increaseScore();
		return original(x, y);
	}
	
	@Override
	public String toString() {
		String str = original();
		StringBuilder builder = new StringBuilder(str);
		builder.append("\nYour current Score:");
		builder.append(score);
		return builder.toString();
	}
}
