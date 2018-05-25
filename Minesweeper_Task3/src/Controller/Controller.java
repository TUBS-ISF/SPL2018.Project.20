package Controller;

import View.Console;
import base.GAME_STATE;
import base.GameArena;

public class Controller {
	private Console c;
	private GameArena g;
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
	}
	
	public Controller() {
		c = new Console(this);
		g = new GameArena();
		g.addObserver(c);
	}
	
	public void start() {
		c.printStart(g);
		do {		
			c.scanInput();
		} while (true);
	}
	
	public GAME_STATE uncoverAt(int x, int y) {
		return g.uncoverCellAt(x, y);
	}
}
