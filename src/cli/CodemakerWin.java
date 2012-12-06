/**
 * CodemakerWin.java
 * 
 * Prints a codemaker win message.
 * 
 * @author Jeremy
 */

package cli;

public class CodemakerWin implements CLIState {

	private MainCLI main;
	
	public CodemakerWin(MainCLI main) {
		this.main = main;
	}
	
    public void printMsg() {
        System.out.println("Codemaker wins!");
        getInput();
    }

    public void getInput() {
    	main.changeState();
    }

    public CLIState nextState() {
        return new GameOverState(main);
    }

}
