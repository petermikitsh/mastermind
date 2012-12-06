/**
 * GuessState.java
 * 
 * Gets user's guesses and processes them.
 * 
 * @author Jeremy
 */

package cli;

import java.util.Scanner;
import java.util.Timer;

public class GuessState implements CLIState {
	
	private MainCLI _mainCLI;
	private boolean timeout;
	
	public GuessState(MainCLI main) {
		this._mainCLI = main;
	}
	
    public void printMsg() {
    	if(_mainCLI.getCodebreakerType().equals("h")){
	        System.out.print("\nGuess #" + Integer.valueOf((_mainCLI.getNumGuesses()+1)) + ": ");
	        this.timeout = false;
	        getInput();
    	} else {
    		computerGuess();
    	}
    }
    
    public void computerGuess() {
    	_mainCLI.createComputerGuess();
        String guess = _mainCLI.getGuess();
        guess = guess.replace("{", "");
        guess = guess.replace(",", "");
        guess = guess.replace("}", "");
        guess = guess.replace("BLACK", "bk");
        guess = guess.replace("WHITE", "wh");
        guess = guess.replace("RED", "rd");
        guess = guess.replace("BLUE", "bl");
        guess = guess.replace("PURPLE", "pu");
        guess = guess.replace("GREEN", "gr");
        guess = guess.replace("YELLOW", "ye");
        System.out.println("\nGuess #" + Integer.valueOf((_mainCLI.getNumGuesses()+1)) +": " + guess);
    	_mainCLI.addFeedback();
    	_mainCLI.incrementNumGuesses();
    	_mainCLI.changeState();
    	
    }

    public void getInput() {
        Timer timer = new Timer();
        timer.schedule(new HumanTimer(_mainCLI, this), 30000);
        Scanner in = new Scanner(System.in);
        String guess = in.nextLine();
        if (timeout) {
        	_mainCLI.changeState();
        	return;
        }
    	while(!_mainCLI.parseCode(guess)) {
    	    System.out.println("Illegal Guess. Please try again.");
    	    guess = in.nextLine();
    	}
    	timer.cancel();
    	_mainCLI.addGuess(guess);
    	_mainCLI.addFeedback();
    	_mainCLI.incrementNumGuesses();
    	_mainCLI.changeState();
    }

    public CLIState nextState() {
    	if (timeout) {
    		return new GuessState(_mainCLI);
    	} else {
        return new FeedbackState(_mainCLI);
        }
    }
    
    public void timeout() {
    	this.timeout = true;
    }

}
