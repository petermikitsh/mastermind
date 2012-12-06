/**
 * MainCLI.java
 * 
 * Initializes the state pattern's ConcreteStates.
 * 
 * @author peter
 */

package cli;

import java.util.ArrayList;
import java.util.HashMap;

import model.GameController;


public class MainCLI {
	
	private GameController _controller;
	private CLIState _currState;
	private String _feedback;
	private String _guess;
	private int _numGuesses;
	private String codemakerType;
	private String codebreakerType;
	
	private static HashMap<String, String> map;
	{
	    map = new HashMap<String, String>();
        map.put("rd", "RED");
        map.put("bl", "BLUE");
        map.put("gr", "GREEN");
        map.put("ye", "YELLOW");
        map.put("pu", "PURPLE");
        map.put("wh", "WHITE");
        map.put("bk", "BLACK");
	}
	
	public MainCLI(GameController controller) {
		this._controller = controller;
		this._currState = new WelcomeState(this);
	}
	
	//Getters
	
	public String getFeedback() {
		return this._feedback;
	}
	
	public int getNumGuesses() {
		return this._numGuesses;
	}
	
	public String getCodemakerType() {
		return this.codemakerType;
	}
	
	public String getCodebreakerType() {
		return this.codebreakerType;
	}
	
	public String getGuess() {
		return this._guess;
	}
	
	// Setters
	
	public void setFeedback(String newFeedback) {
		this._feedback = newFeedback;
	}

	public void setNumGuesses(int newNumGuesses) {
		this._numGuesses = newNumGuesses;
	}
	
	public void setCodemakerType(String codemaker) {
		this.codemakerType = codemaker;
	}
	
	public void setCodebreakerType(String codebreaker) {
		this.codebreakerType = codebreaker;
	}
	
	public void setGuess(String guess) {
		this._guess = guess;
	}
	
	//Additional Functionality
	public void start() {
		this._currState.printMsg();
	}
	
	
	public void changeState() {
		this._currState = this._currState.nextState();
		this._currState.printMsg();
	}
	
	public void updateGameMode(String gameMode) {
		if(gameMode.equals("Novice")) {
			this._controller.setGameMode("1");
		}
		else{
			this._controller.setGameMode("2");
		}
	}
	
	public void updateCodebreaker(ArrayList<String> codebreaker) {
		 this._controller.updateCodeBreaker(codebreaker);
		 setCodebreakerType(codebreaker.get(0));
	}
	
	public void updateCodemaker(String codemaker) {
		 this._controller.updateCodeMaker(codemaker);
		 setCodemakerType(codemaker);
	}
	
	public void newGame(String newGameType) {
		if (newGameType.equals("new")) {
			_numGuesses = 0;
			 this._controller.startNewGame();
		}
		else if (newGameType.equals("restart")) {
			this._controller.restartGame();
		}
		else {}
	}
	
	public void addFeedback() {
	    _controller.computerFeedback();
	}

	public void addGuess(String guess) {
	    ArrayList<String> pegs = new ArrayList<String>();
	    for(String peg : guess.split(" "))
	        pegs.add(map.get(peg));
	    _controller.setNewGame(false);
	    _controller.addGuess(pegs);
	    //System.out.println(pegs);
	}
	
	public void createComputerAnswer() {
		_controller.addAnswer(_controller.generateComputerGuess());
	}
	
	public void createComputerGuess() {
		_controller.addGuess(_controller.generateComputerGuess());
	}
	
	public void addAnswer(String answer) {
	    ArrayList<String> pegs = new ArrayList<String>();
        for(String peg : answer.split(" "))
            pegs.add(map.get(peg));
        _controller.addAnswer(pegs);
	}
	
	public void incrementNumGuesses() {
		this._numGuesses++;
	}
	
	public boolean parseCode(String guess) { 
        String[] pegs = guess.split(" ");
        for(String peg : pegs)
            if(map.get(peg) == null)
                return false;
        return true;
	}
}
