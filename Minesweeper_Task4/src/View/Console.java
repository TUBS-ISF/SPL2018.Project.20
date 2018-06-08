package View;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import Control.Controller;
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
		System.out.println("1. {uncover x,y}, x={0,1,...,9, A,...}, y= {0,1,...,y}\n");
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
		if (0 == strArray.length) {
			printError();
		}
		if (strArray[0].equals("uncover")) {
			if (strArray.length != 2) {
				printError();
			} else {
				String[] positionArray = strArray[1].split(",");
				if (positionArray.length != 2) {
					printError();
				} else {
					try {
						int x = 0;
					if (positionArray[0].charAt(0) < 'A')
						x = Integer.parseInt(positionArray[0], 10);
					else
						x = positionArray[0].charAt(0) - 'A' + 10;
					//int x = Integer.parseInt(positionArray[0], 10);
					int y = Integer.parseInt(positionArray[1], 10);
					switch (controller.uncoverAt(x, y)) {
					case GAME_FORBIDDEN_PLAY:
						printError();
						break;
					case GAME_LOST:
						System.out.println("Sorry, you lost :(! You hit a bomb!\n");
						System.exit(0);
						break;
					case GAME_WON:
						System.out.println("Congratiolations! You won :)!\n");
						System.exit(0);
						break;
					default:
						break;
					}
					} catch (NumberFormatException e) {
						printError();
					}
				}
			}
		} /*else if(...) {

		}*/
		else {

		}
	}

	public void scanInput() {
		interpretInput(scanner.nextLine());
	}

	public void printCommandLine() {
		System.out.print("Command > ");
	}

	public void printError() {
		System.out.println("Invalid play! Try again!");
		printCommandLine();
	}
}
//#endif