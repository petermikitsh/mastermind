/**
 * FeedbackState.java
 * 
 * Gets the feedback from the user and checks for a win.
 * 
 * @author Jeremy
 */

package cli;

public class FeedbackState implements CLIState {

	private MainCLI main;
	private final String winFeedback = "{BLACK, BLACK, BLACK, BLACK}";
	
	public FeedbackState(MainCLI main) {
		this.main = main;
	}
	
    public void printMsg() {
        String feedback = main.getFeedback();
        feedback = feedback.replace("{", "");
        feedback = feedback.replace(",", "");
        feedback = feedback.replace("}", "");
        feedback = feedback.replace("BLACK", "bk");
        feedback = feedback.replace("WHITE", "wh");
        feedback = feedback.replace("NONE", "--");
        System.out.println("Feedback: " + feedback);
        getInput();
    }

    public void getInput() {
        main.changeState();
    }

    public CLIState nextState() {
    	if(main.getFeedback().equals(winFeedback)){
    		System.out.println("win");
    		return new CodebreakerWin(main);
    	}
    	else if(main.getNumGuesses() == 10) {
    		return new CodemakerWin(main);
    	}
    	else{
    		return new GuessState(main);
    	}
    }

}
