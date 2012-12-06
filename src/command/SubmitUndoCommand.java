/**
 * SubmitUndoCommand.java
 *
 * This class is the command to undo a state change. A state change is either 
 * a new guess or a new feedback.
 * 
 * @author Jeremy
 */

package command;

import model.GCReceiver;
import model.GameCommand;

public class SubmitUndoCommand implements GameCommand {
    
    private GCReceiver receiver;
    
    /**
     * Constructor for the undo command
     * 
     * @param receiver - The reference to the receiver
     */
    public SubmitUndoCommand(GCReceiver receiver) {
        this.receiver = receiver;
    }
    
    /**
     * This method interacts with the receiver to undo the last guess
     * or the last feedback and guess pair
     */
    @Override
    public void execute() {
    	//This little algorithm determines if we need to undo 1 or 2 states
    	//and saves the result in numToUndo for readability
        int numToUndo = Math.abs((receiver.getState().getNumTurns() % 2) - 2);
        int minimumStates = receiver.getState().getNumGamePlays() + 1 - numToUndo;
        if (minimumStates >= 1) {
        	receiver.undoStates(numToUndo);
        	receiver.sendToLog("Undo command issued: Last state change undone.");
        }
        else {}
    }

}
