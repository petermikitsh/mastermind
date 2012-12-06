package model;
import java.util.ArrayList;

/**
 * GameState.java
 * 
 * @author Jeremy
 *
 * This class represents the current state of the game.
 * If holds the answer code, all the guesses and all the
 * feedbacks.
 */
public class GameState {
	
    private Code answerCode;
    private ArrayList<Code> guesses;
    private ArrayList<Feedback> feedback;
    private String status;
    
    /**
     * Constructor for the GameState. There will never be more guesses or feedbacks
     * than NUM_GUESSES. The answer code has not yet been set.
     */
    public GameState() {
        this.answerCode = null;
        this.guesses = new ArrayList<Code>();
        this.feedback = new ArrayList<Feedback>();
        this.status = "Press File > \"New Game\" to begin a new game.";
    }
    
    /**
     * This constructor copies the data from a previous GameState 
     * @param original - The original GameState to copy data from
     */
    public GameState(GameState original) {
    	if (original.answerCode != null) { 
    		CodePegs[] newCode = original.answerCode.getCode();
    		answerCode = new Code(newCode, newCode.length);
    	}
    	guesses = new ArrayList<Code>(original.guesses);
    	feedback = new ArrayList<Feedback>(original.feedback);
    	status = new String(original.status);
    }
    
    /** gets the last guess made. */
    public Code getLastGuess() {
    	Code lastGuess = guesses.get(guesses.size()-1);
		return lastGuess;
    }
    
    /** Getter for guesses. */
    public ArrayList<Code> getGuesses() {
    	return this.guesses;
    }
    
    /** Getter for feedback. */
    public ArrayList<Feedback> getFeedback() {
    	return this.feedback;
    }
    
    /** Adds a guess to the GameState */
    public void addGuess(Code newGuess) {
        guesses.add(newGuess);
    }
    
    /** Adds a feedback to the GameState */
    public void addFeedback(Feedback newFeedback) {
        feedback.add(newFeedback);
    }
    
    /** Gets the current status of the game. */
    public String getStatus() {
    	return this.status;
    }
    
    /** Sets the current status of the game */
    public void setStatus(String status) {
    	this.status = status;
    }
    
    /** Sets the answer code */
    public void setAnswer(Code answer) {
        this.answerCode = answer;
    }
    
    /** Gets the answer code */
    public Code getAnswer() {
    	return this.answerCode;
    }
    
    /** Gets the total number of guesses and feedback. */
    public int getNumTurns() {
        return guesses.size() + feedback.size();
    }
    
    /**Gets the total number of answercodes, guesses, and feedback */
    public int getNumGamePlays() {
    	int i = guesses.size() + feedback.size();
    	if (answerCode != null ) {
    		i++;
    	}
    	return i;
    }
    
    /** Gets the most recent feedback */
    public Feedback getLastFeedback() {
    	if (feedback.size() == 0) {
    		return null;
    	}
    	return feedback.get(feedback.size() - 1);
    }
    
    /**
     * Creates what the log would represent based on the 
     * current state of the game
     * 
     * @return newLog - A list of the events that have brought the state of
     * 					the game to where it is now.
     */
    public ArrayList<String> createLogFromGameState() {
		ArrayList<String> newLog = new ArrayList<String>();
		for( int i = 0; i < feedback.size(); i++)  {
			newLog.add("Codebreaker guessed: " + guesses.get(i));
			newLog.add("Codemaker provided feedback: " + feedback.get(i));
		}
		
		//checks to see if we are waiting for a feedback after a guess has been placed
		//if so we need to add the most recent guess to the log
		if( guesses.size() > feedback.size() )
			newLog.add("Codebreaker guessed: " + guesses.get(guesses.size() - 1));
		return newLog;
	}
}
