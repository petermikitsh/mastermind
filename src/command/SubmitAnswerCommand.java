/**
 * SubmitAnswerCommand.java
 * 
 * Adds an answer to the game state.
 * 
 * @author peter
 */

package command;

import model.Code;
import model.GCReceiver;
import model.GameCommand;
import model.GameState;

public class SubmitAnswerCommand implements GameCommand {

	private GCReceiver _receiver;
	private Code _answerCode;
	
	/**
	 * Constructor.
	 * @param _receiver The current game state.
	 * @param feedback The feedback to be added.
	 */
	public SubmitAnswerCommand(GCReceiver _receiver, Code _answerCode) {
		this._receiver = _receiver;
		this._answerCode = _answerCode;
	}
	
	/**
	 * @see GameCommand#execute()
	 */
	public void execute() {
		GameState newState = new GameState(_receiver.getState());
		newState.setAnswer(_answerCode);
		newState.setStatus("Codebreaker submitted code. Waiting for CodeBreaker's guess.");
		_receiver.addState(newState);
		_receiver.sendToLog("Codemaker created answer code.");
	}
	
}