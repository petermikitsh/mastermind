/**
 * Feedback.java
 * 
 * @author Jeremy
 *
 * This class represents the codemaker's feedback to a guess
 */

package model;

import java.util.ArrayList;

public class Feedback {
	
	private FeedbackPegs[] feedback;
	private ArrayList<String> feedbkArray;
	private int pegSize;
	
	/**
	 * Constructor for a feedback.
	 * 
	 * @param feedback - The pegs that make up the feedback
	 */
	public Feedback(FeedbackPegs[] feedback, int pegSize) {
		//validate length of feedback
		if (feedback.length != pegSize)
			throw new IllegalArgumentException();
		
		//validate that elements in the feedback are assigned
		for (int i = 0; i < pegSize; i++)
			if (feedback[i] == null)
				throw new IllegalArgumentException();
		
		this.feedback = feedback;
		this.pegSize = pegSize;
		
	}
	
	/**
	 * Constructor for a feedback from ArrayList representation.
	 */
	public Feedback(ArrayList<String> pegNames, int pegSize) {
		
		FeedbackPegs[] feedback = new FeedbackPegs[pegSize];
		
		for (int i = 0; i < pegNames.size(); i++) {
			FeedbackPegs feedbackPeg = FeedbackPegs.valueOf(pegNames.get(i));
			feedback[i] = feedbackPeg;
		}
		
		this.feedback = feedback;
		this.pegSize = pegSize;
		
	}
	
	/**
	 * returns the list of feedback pegs
	 * 
	 * @return feedback - the list of feedback pegs
	 */
	public FeedbackPegs[] getFeedback() {
		return feedback;
	}
	
	/**
	 * Function to represent a function as a string
	 * 
	 * @return result - The string representation of a feedback
	 */
	public String toString() {
	    String result = "{";
	    for (int i = 0; i < this.pegSize; i++)
	        result += feedback[i] + ", ";
	    result = result.substring(0, result.length() - 2);
	    result += "}";
	    return result;
	}
	
	/**
	 * Helper function to convert array to arrayList
	 *  @return arrayList of feedback
	 */
	
	public ArrayList<String> converToArray() {
		feedbkArray = new ArrayList<String>();
		for(FeedbackPegs peg: feedback) {
			feedbkArray.add(peg.toString());
		}
		return feedbkArray;
	}
	
	/**
	 * This function compares two feedbacks. This will be used to determine
	 * if a codebreaker has won the game and maybe for a couple other things
	 * 
	 * @param other - The other feedback that we are comparing ourselves to
	 * @return true if the feedbacks contain the same pegs
	 */
//	public boolean equals(Object other) {
//		if (other.getClass() == Feedback.class) {
//			Feedback otherFeedback = (Feedback)other;
//			for (int i = 0; i < GameController.gamePegSize; i++)
//				if (feedback[i] != otherFeedback.getFeedback()[i])
//					return false;
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
	
	/**
	 * Checks to see if feedback (of any length) is a winning combination.
	 * @param feedback
	 * @return
	 */
	public static boolean victory(Feedback feedback) {
		if (feedback == null || !feedback.feedback[0].equals(FeedbackPegs.BLACK)) {
			return false;
		}
		else {
			for (int i = 0; i < feedback.feedback.length - 2; i++) {
				if (!feedback.feedback[i].equals(feedback.feedback[i+1])) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static ArrayList<String> makeFeedbackImageNameList(ArrayList<Feedback> allFeedback) {
		ArrayList<String> feedbackString = new ArrayList<String>();
		//iterate the feedback
		for (int i = 0; i < allFeedback.size(); i++) {
			FeedbackPegs[] currFeedback = allFeedback.get(i).getFeedback();
			//iterate each guesses' pegs
			for (int j = 0; j < currFeedback.length; j++ ) {
				if( currFeedback[j].name() == "BLACK"){
					String colorFileName = "FEEDBK_BLACK.png";
					feedbackString.add(colorFileName);
				}else if (currFeedback[j].name() == "WHITE") {
					String colorFileName = "FEEDBK_WHITE.png";
					feedbackString.add(colorFileName);
				}else {
					String colorFileName = "NONE.jpg";
					feedbackString.add(colorFileName);
				}
			}
		}
		return feedbackString;
	}
	
}
