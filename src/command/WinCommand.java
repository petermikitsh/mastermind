/**
 * WinCommand.java
 * 
 * Executes a win on the game state.
 * 
 * @author Peter
 */

package command;

import model.FeedbackPegs;
import model.GCReceiver;
import model.GameCommand;
import model.GameState;

public class WinCommand implements GameCommand {

	private GCReceiver _receiver;
	private GameState _currState;
	
	public WinCommand(GCReceiver receiver) {
		this._receiver = receiver;
		this._currState = _receiver.getState();
	}

	public void execute() {
		//System.out.println("I won!");
		if (codeBreakerWin()) {
			_receiver.updateStatus("Codebreaker has won!");
		}
		else {
			_receiver.updateStatus("Codemaker has won!");
		}
	}
	
	/**
	 * Determines if the codebreaker won.
	 * @return boolean representing the code breaker's win or loss.
	 */
	private boolean codeBreakerWin() {
		
		FeedbackPegs[] feedback = _currState.getLastFeedback().getFeedback();
		for (int i = 0; i < feedback.length; i++) {
			if (!feedback[i].equals(FeedbackPegs.BLACK)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Determines if the codemaker won.
	 * @return boolean representing the codemaker's win or loss.
	 */
	private boolean codeMakerWin(int gameRows) {
		if(_currState.getNumTurns() == gameRows * 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * Determines if a win occurred.
	 * @param gameRows number of rows in the game.
	 * @return Boolean representing a win.
	 */
	public boolean win(int gameRows) {
		return codeBreakerWin() || codeMakerWin(gameRows);
	}

}
