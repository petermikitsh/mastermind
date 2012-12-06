/**
 * GameOverState.java
 * 
 * Decides what to do next.
 * 
 * @author Jeremy and Peter
 */

package cli;

import java.util.Scanner;

public class GameOverState implements CLIState {

	private MainCLI _mainCLI;
	private String _nextMove;
	
	public GameOverState(MainCLI main) {
		this._mainCLI = main;
	}
	
    public void printMsg() {
        System.out.println("What next? (new, restart, exit)");
        getInput();
    }

    public void getInput() {
    	Scanner input = new Scanner(System.in);
		String in = input.next();
		boolean validInput = false;
		while (!validInput) {
			while(!in.equals("new") && !in.equals("restart") &&
					!in.equals("exit") ) {
				System.out.println("? ");
				in = input.next();
			}
			validInput = true;
			_nextMove = in;
			processString();
		}
		_mainCLI.changeState();
    }
    
    private void processString() {
    	_mainCLI.updateGameMode(_nextMove);
    }

    public CLIState nextState() {
    	if (_nextMove.equals("new")) {
    		_mainCLI.newGame("new");
    		return new WelcomeState(_mainCLI);
    	}
    	else if (_nextMove.equals("restart")) {
    		_mainCLI.setNumGuesses(0);
    		_mainCLI.newGame("restart");
    		return new GuessState(_mainCLI);
    	}
    	else {
    		return new EndGameState();
    	}
    }

}
