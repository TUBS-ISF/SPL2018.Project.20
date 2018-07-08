package Base;

public class WinChecker {
	private int numberOfUncoveredCellsToWin;
	private int uncoveredCells;
	public WinChecker (int x, int y, int numberOfBombs) {
		numberOfUncoveredCellsToWin = (x * y) - numberOfBombs;
		uncoveredCells = 0;
	}
	
	public void increasedUncoveredCells(int newUncoveredCells) {
		uncoveredCells += newUncoveredCells;
	}
	
	public boolean isGameWon() {
		return (numberOfUncoveredCellsToWin == uncoveredCells);
	}
}
