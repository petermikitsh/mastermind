package model;
/**
 * Computer.java
 * @author Josh Eklund
 *
 * This is a Computer Player that overrides the Player functions
 * 
 */

import java.util.ArrayList;
import java.util.Random;

public class Computer implements Player {

	private String difficulty;
	private GameController _controller;
	private Difficulty _difficultyMode;
	private String _name;
	@SuppressWarnings("unused")
	private Integer _waitTime;	
	private boolean good = true;
	//Constants for Computer Logic (Making a guess)
	private final int MAX_COLORS = CodePegs.values().length;
	
	public Computer(GameController controller) {
	    this._controller = controller;
	    this._name = this.getClass().getName();
	    this._waitTime = 15;
	    this._difficultyMode = new Novice();
	}
	
	public String getName() {
		return this._name;
	}
	
	public void setWaitTime(int seconds) {
		this._waitTime = seconds;
	}
	
	public void setDifficulty(String level) {
		this.difficulty = level;
	}
	
	/**
	 * This method packages commands and sends them off 
	 * to the GameCommand class
	 *
	 */
	public void makeCommand(GameCommand newCommand) {
		newCommand.execute();
	}
	
	/**
	 * This method is made to make a code 
	 * with random CodePegs assigned to it
	 * 
	 * @return newCode
	 */
	public ArrayList<String> fillColor(int pegSize)  {
		//Assign the CodePegs Values to this data structure
		difficulty = _controller.getGameMode();
		good = false;
		if(difficulty.equals("2")) {
			_difficultyMode = new Expert();
		}
		else{
			_difficultyMode = new Novice();
		}
		ArrayList<String> newCode = new ArrayList<String>();
		while( good == false){
			int choice = 0;
			newCode.clear();
			CodePegs[] values = CodePegs.values();
			for (int i = 0; i < pegSize; i++) {
				choice = new Random().nextInt(MAX_COLORS);
				newCode.add(values[choice].toString());
			}
			Code c = new Code(newCode, 4);
			good = _difficultyMode.isLegalCode(c);
		  }
		return newCode;
		}
	/**
	 * This method will pass the code that the GUI makes
	 * to the code class, and then make a GameCommand
	 * to submit the new code 
	 * 
	 * @param code an Array of CodePegs that make up a code
	 */
	public void submitCode(int pegSize) {
		//try {Thread.sleep(_waitTime * 1000);}
		//catch (InterruptedException e) {}
		_controller.addGuess(fillColor(pegSize));
	}

}