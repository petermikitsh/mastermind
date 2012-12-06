package model;

/**
 * @author Jeremy
 *
 */
public class SystemPlayer implements Player {

	@Override
	public void makeCommand(GameCommand newCommand) {
		//System never does this
	}

	@Override
	public String getName() {
		return "Gimp";
	}
	
	/**
	 * Generates feedback based on the code and the answer
	 * 
	 * @param guess - The code that is being guessed
	 * @param answer - The solution code
	 * @return feedback - The feedback for this round of mastermind
	 */
	public Feedback generateFeedback(Code guess, Code answer) {
		int numFeedbackPegs = 0;
		//Gets the magnitude of the intersection of the guess and the answer
		for(CodePegs color : CodePegs.values()) {
			numFeedbackPegs += Math.min(getNumOccur(color, guess.getCode()), 
					getNumOccur(color, answer.getCode()));
		}
		
		//Calculates the number of black pegs
		int blacks = 0;
		for(int i = 0;i < answer.getCode().length;i++)
			if(guess.getCode()[i] == answer.getCode()[i])
				blacks++;
		
		//Start creating the feedback
		FeedbackPegs[] feedback = new FeedbackPegs[4];
		
		//Add the black pegs
		for(int i = 0;i < blacks;i++)
			feedback[i] = FeedbackPegs.BLACK;
		
		//Add the white pegs
		for(int i = blacks;i < numFeedbackPegs;i++)
			feedback[i] = FeedbackPegs.WHITE;
		
		//Add blanks
		for(int i = numFeedbackPegs;i < answer.getCode().length;i++)
			feedback[i] = FeedbackPegs.NONE;
		
		return new Feedback(feedback, answer.getCode().length);
	}
	
	private int getNumOccur(CodePegs peg, CodePegs[] code) {
		int num = 0;
		for(CodePegs p : code)
			if(p == peg)
				num++;
		return num;
	}
	
	/*public static void main(String args[]) {
		CodePegs[] pegs = {CodePegs.BLUE, CodePegs.BLUE, CodePegs.BLACK, CodePegs.BLUE};
		CodePegs[] ans = {CodePegs.BLACK, CodePegs.BLUE, CodePegs.BLACK, CodePegs.BLUE};
		Code guess = new Code(pegs, 4);
		Code answer = new Code(ans, 4);
		SystemPlayer gimp = new SystemPlayer();
		System.out.println(gimp.generateFeedback(guess, answer));
	}*/

}
