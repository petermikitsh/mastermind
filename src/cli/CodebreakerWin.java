/**
 * CodebreakerWin.java
 * 
 * Prints a codebreaker win message.
 * 
 * @author Jeremy
 */

package cli;

public class CodebreakerWin implements CLIState {

	private MainCLI main;
	
	public CodebreakerWin(MainCLI main) {
		this.main = main;
	}
	
    public void printMsg() {
        System.out.println("Codebreaker wins in " + main.getNumGuesses() + " guesses!");
        getInput();
    }

    public void getInput() {
    	main.changeState();
    }

    public CLIState nextState() {
        return new GameOverState(main);
    }

}
