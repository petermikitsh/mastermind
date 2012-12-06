/**
 * EndGameState.java
 * 
 * Ends this instance of Mastermind via system exit.
 * 
 * @author peter
 */

package cli;

public class EndGameState implements CLIState {

	public EndGameState() {
	}
	
	public void printMsg() {
		getInput();
	}

	public void getInput() {
		System.exit(0);
	}

	public CLIState nextState() {
		return null;
	}

}
