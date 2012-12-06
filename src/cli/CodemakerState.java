/**
 * CodemakerState.java
 * 
 * Gets the Codemaker type.
 * 
 * @author peter
 */

package cli;

import java.util.Scanner;


public class CodemakerState implements CLIState {

	private MainCLI _mainCLI;
	private String _codemaker;
	
	public CodemakerState(MainCLI mainCLI) {
		this._mainCLI = mainCLI;
	}
	
	public void printMsg() {
		System.out.print("Who is the codemaker? Computer (c) or Human (h)\n? ");
		getInput();
	}

	public void getInput() {
		Scanner input = new Scanner(System.in);
		String in = input.next().trim();
		while(!in.equals("c") && !in.equals("h")) {
			System.out.print("? ");
			in = input.next().trim();
		}
		_codemaker = in;
		processString();
		_mainCLI.changeState();
	}
	
	private void processString() {
		_mainCLI.updateCodemaker(_codemaker);
	}

	public CLIState nextState() {
		if (_mainCLI.getCodemakerType().equals("h")) {
			return new AnswerState(_mainCLI);
		}
		else {
			_mainCLI.createComputerAnswer();
			return new GuessState(_mainCLI);
		}
	}

}
