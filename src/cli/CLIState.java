/**
 * CLIState.java
 * 
 * Defines method signatures for ConcreteStates in a given context.
 * 
 * @author: peter
 */

package cli;

public interface CLIState {

	/**
	 * Message output when state is initially entered.
	 */
	public void printMsg();
	
	/**
	 * Processes input from the command line.
	 */
	public void getInput();
	
	/**
	 * Returns the next state of the automata. 
	 */
	public CLIState nextState();
	
}
