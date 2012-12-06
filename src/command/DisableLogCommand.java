/**
 * LogBooleanCommand.java
 * 
 * Disables the log.
 * 
 * author: peter
 */

package command;

import model.GCReceiver;
import model.GameCommand;

public class DisableLogCommand implements GameCommand {

	private GCReceiver _receiver;
	
	/**
	 * Constructor.
	 * @param _receiver The current game state.
	 */
	public DisableLogCommand(GCReceiver receiver) {
		this._receiver = receiver;
	}
	
	public void execute() {
		_receiver.disableLog();
	}
	
}