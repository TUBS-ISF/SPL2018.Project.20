//#if Console
package View;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import Controller.Controller;
import base.GameArena;

public class Console implements Observer {

	private Scanner scanner;
	private Controller controller;
	
	public Console(Controller c) {
		controller = c;
		scanner = new Scanner(System.in);
	}
	
	public void printStart(GameArena g) {
		System.out.println("Minesweeper Game!\nGood Luck!\n");
		System.out.println("Commands:");
		System.out.println("1. {uncover Xy}, X={A,B,..., }, y= {1...n}\n");
		System.out.println(g);
		printCommandLine();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		GameArena f = null;
		if (arg1 instanceof GameArena) {
			f = (GameArena) arg1;
		} else {
			return;
		}
		System.out.println(f);
		printCommandLine();
	}
	
	private void interpretInput(String str) {
		String strArray[] = str.split(" ");
		
		if (strArray.length == 2 && strArray[0].equals("uncover") && 
				(strArray[1].length() == 2 || strArray[1].length() == 3)) {
			int x = strArray[1].charAt(0) - 'A';
			int y = strArray[1].charAt(1) - '0';
			if (strArray[1].length() == 3) {
				y *= 10;
				y += (strArray[1].charAt(2) - '0');
			} 
			switch (controller.uncoverAt(x, y)) {
			case GAME_FORBIDDEN_PLAY:
				System.out.println("Incorrect play! Try again!\n");
				break;
			case GAME_LOST:
				System.out.println("Sorry, you lost :(! You hit a bomb! More Luck next Time!\n");
				System.exit(0);
				break;
			case GAME_WON:
				System.out.println("Congratiolations! You won :)!\n");
				System.exit(0);
				break;
			default:
				break;
			}
		} else {
			System.out.println("Input cannot interpreted. Try again :)!\n");
		}
	}
	
	public void scanInput() {
		interpretInput(scanner.nextLine());
	}
	
	public void printCommandLine() {
		System.out.print("Command > ");
	}
}
//#endif