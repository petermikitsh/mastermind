/**
 * EnableLogCommand.java
 *
 * This class is the command to enable the log and open the log file
 * for writing. 
 * 
 * @author Jeremy
 */

package command;

import model.GCReceiver;
import model.GameCommand;

public class EnableLogCommand implements GameCommand {

	private GCReceiver receiver;
	private String filename;
	
	/**
	 * Constructor for the enableLogCommand
	 * 
	 * @param receiver - The reference to the receiver
	 * @param filename - The name of the file to be the log
	 */
	public EnableLogCommand(GCReceiver receiver, String filename) {
		this.receiver = receiver;
		this.filename = filename;
	}
	
	/**
	 * This function tells the receiver to enable the log and open the
	 * log file for writing.
	 */
	@Override
	public void execute() {
		receiver.enableLog(filename);
	}
}
