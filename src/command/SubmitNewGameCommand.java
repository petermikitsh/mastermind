/**
 * SubmitNewGameCommand.java
 * 
 * This class is the command to start a new game. This can
 * be done in the middle of a game or at the end of a game
 * 
 * @author Peter and Evan
 */

package command;

import model.GCReceiver;
import model.GameCommand;

public class SubmitNewGameCommand implements GameCommand {

    private GCReceiver receiver;
    
    /**
     * Constructor for the new game command 
     * 
     * @param receiver - A reference for the receiver
     */
    public SubmitNewGameCommand(GCReceiver receiver) {
        this.receiver = receiver;
    }
    
    /**
     * This method interacts with the receiver in order to reset the GameState
     * so that it is as if we have started a new game
     */
    @Override
    public void execute() {

    	receiver.clearHistory();
    }

}
