/**
 * SubmitGuessCommand.java
 * 
 * Adds a guess to the game state.
 * 
 * @author peter
 */

package command;

import model.Code;
import model.GCReceiver;
import model.GameCommand;
import model.GameState;

public class SubmitGuessCommand implements GameCommand {

	private GCReceiver _receiver;
	private Code _guess;
	private int _gameRows;
	
	/**
	 * Constructor.
	 * @param receiver The receiver to be modified.
	 * @param guess The guess to be added.
	 */
	public SubmitGuessCommand(GCReceiver receiver, Code guess, int gameRows) {
		this._receiver = receiver;
		this._guess = guess;
		this._gameRows = gameRows;
	}
	
	/**
	 * @see GameCommand#execute()
	 */
	public void execute() {
		GameState newState = new GameState(_receiver.getState());
		newState.setStatus("Codebreaker provided guess. Waiting for feedback...");
		newState.addGuess(_guess);
		
		// Prevent adding guesses beyond the gameRow limit.
		if (newState.getGuesses().size() > _gameRows) {
			return;
		}
		
		_receiver.addState(newState);
		_receiver.sendToLog("Codebreaker guessed: " + _guess);
	}
	
}
