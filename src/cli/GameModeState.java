/**
 * GameMode.java
 * 
 * Collects user input of game mode and passes it to the controller.
 * 
 * @author peter
 */

package cli;

import java.util.Scanner;

public class GameModeState implements CLIState {

	private MainCLI _mainCLI;
	private String _gameState;
	
	public GameModeState(MainCLI mainCLI) {
		this._mainCLI = mainCLI;
	}
	
	/** This state's welcome message */
	public void printMsg() {
		System.out.print("Select mode of play, Novice (n), Expert (e)\n? ");
		getInput();
	}
	
	/** Gets valid input from the user */
	public void getInput() {
		Scanner input = new Scanner(System.in);
		String in = input.nextLine();
		boolean validInput = false;
		while (!validInput) {
			while(!in.equals("n") && !in.equals("e")) {
				System.out.print("? ");
				in = input.next().trim();
			}
			validInput = true;
			_gameState = in;
			processString();
		}
		_mainCLI.changeState();
	}
	
	/** Does something with the user's valid input */
	private void processString() {
		if (_gameState.equals("n")) {
			_mainCLI.updateGameMode("Novice");
		}
		else {
			_mainCLI.updateGameMode("Expert");
		}
	}

	/** Tells the context to get it's next state */
	public CLIState nextState() {
		return new CodebreakerState(_mainCLI);
	}

}
