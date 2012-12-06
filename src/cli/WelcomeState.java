/**
 * Welcome.java
 * 
 * Initial ConcreteState in state pattern. Welcomes the user.
 * 
 * @author peter
 */

package cli;

public class WelcomeState implements CLIState {

	private MainCLI _mainCLI;
	
	public WelcomeState(MainCLI mainCLI) {
		this._mainCLI = mainCLI;
	}
	
	public void printMsg() {
		System.out.println("Welcome to Mastermind!\n");
		getInput();
	}

	public void getInput() {
		_mainCLI.changeState();
	}


	public CLIState nextState() {
		return new GameModeState(_mainCLI);
	}

}
