/**
 * 
 */
package cli;

import java.util.TimerTask;

/**
 * @author Jeremy
 *
 */
public class HumanTimer extends TimerTask{

    private MainCLI main;
    @SuppressWarnings("unused")
	private GuessState guessState;
    
    public HumanTimer(MainCLI main, GuessState guessState) {
        this.main = main;
        this.guessState = guessState;
    }
    
    @Override
    public void run() {
        System.out.print("\nWaited too long! \nGuessed: ");
        System.out.println("-- -- -- --");
        main.incrementNumGuesses();
        System.out.print("\nGuess #" + Integer.valueOf((main.getNumGuesses()+1)) + ": ");
       // guessState.timeout();
        //guessState.printMsg();
    }

}
