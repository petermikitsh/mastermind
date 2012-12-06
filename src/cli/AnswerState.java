/**
 * AnswerState.java
 * 
 * Gets the answer code from the user.
 * 
 * @author peter
 */

package cli;

import java.util.Scanner;

public class AnswerState implements CLIState {

	private MainCLI _mainCLI;
	
	public AnswerState(MainCLI mainCLI) {
		this._mainCLI = mainCLI;
	}
	
	public void printMsg() {
		System.out.println("Codemaker is human, enter the code:\n? ");
		getInput();
	}

	public void getInput() {
		
	    Scanner in = new Scanner(System.in);
        String answer = in.nextLine();
        while(!_mainCLI.parseCode(answer)) {
            System.out.println("Illegal Answer. Please try again.");
            answer = in.nextLine();
        }
        _mainCLI.addAnswer(answer);
        _mainCLI.changeState();
	}

	public CLIState nextState() {
		// TODO Auto-generated method stub
		return new GuessState(_mainCLI);
	}

}
