/**
 * Codebreaker.java
 * 
 * Gets the codebreaker input from the user and sends
 * a valid request to the MainCLI.
 * 
 * @author peter
 */

package cli;

import java.util.ArrayList;
import java.util.Scanner;

public class CodebreakerState implements CLIState {

	private MainCLI _mainCLI;
	private ArrayList<String> _codebreakerInfo;
	
	public CodebreakerState(MainCLI mainCLI) {
		this._mainCLI = mainCLI;
		_codebreakerInfo = new ArrayList<String>();
	}
	
	public void printMsg() {
		System.out.print("Who is the codebreaker? Computer (c) or Human (h)\n? ");
		getInput();
	}

	public void getInput() {
		Scanner input = new Scanner(System.in);
		String in = input.next().trim();
		boolean validInput = false;
		while (!validInput) {
			while(!in.equals("c") && !in.equals("h")) {
				System.out.print("? ");
				in = input.next().trim();
			}
			validInput = true;
			_codebreakerInfo.add(in);
			if (in.equals("c")) { getAlgorithmType(); }
			processString();
		}
		_mainCLI.changeState();
	}
	
	private void getAlgorithmType() {
		System.out.print("Codebreaker is computer, select algorithm type:" +
				"\n(1) random\n(2) dumb\n(3) smart\n? ");
		Scanner input = new Scanner(System.in);
		String in = input.next().trim();
		while(!in.equals("1") && !in.equals("2") && !in.equals("3")) {
			System.out.print("? ");
			in = input.next().trim();
		}
		_codebreakerInfo.add(in.toString());
		
	}
	
	private void processString() {
		_mainCLI.updateCodebreaker(_codebreakerInfo);
	}

	public CLIState nextState() {
		return new CodemakerState(_mainCLI);
	}

}
