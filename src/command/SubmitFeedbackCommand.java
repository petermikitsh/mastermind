/**
 * SubmitFeedbackCommand.java
 * 
 * Adds a feedback to the game state.
 * 
 * @author peter
 */

package command;

import model.Feedback;
import model.GCReceiver;
import model.GameCommand;
import model.GameState;

public class SubmitFeedbackCommand implements GameCommand {

	private GCReceiver _receiver;
	private Feedback _feedback;
	private int _gameRows;
	
	/**
	 * Constructor.
	 * @param currGameState The current game state.
	 * @param feedback The feedback to be added.
	 */
	public SubmitFeedbackCommand(GCReceiver receiver, Feedback feedback, int gameRows) {
		this._receiver = receiver;
		this._feedback = feedback;
		this._gameRows = gameRows;
	}
	
	/**
	 * @see GameCommand#execute()
	 */
	public void execute() {
		
		GameState newState = new GameState(_receiver.getState());
		newState.addFeedback(_feedback);
		newState.setStatus("Recieved Codemaker's feedback. Waiting for Codebreaker's next guess.");
		


		// Prevent adding feedback beyond the gameRow limit.
		if (newState.getFeedback().size() <= _gameRows) {
			_receiver.addState(newState);
			_receiver.sendToLog("Codemaker provided feedback: " + _feedback);
		}
		
		//System.out.println("Whut " + _receiver.getState().getLastFeedback());
		WinCommand winCheck = new WinCommand(_receiver);
		if (winCheck.win(_gameRows)) {
			winCheck.execute();
		}
		
		
				
	}

}