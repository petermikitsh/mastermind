package model;

import java.util.ArrayList;

import command.DisableLogCommand;
import command.EnableLogCommand;
import command.SubmitAnswerCommand;
import command.SubmitFeedbackCommand;
import command.SubmitGuessCommand;
import command.SubmitNewGameCommand;
import command.SubmitUndoCommand;
import cli.MainCLI;
import gui.MainGUIPanel;
import javax.swing.SwingUtilities;

public class GameController {
	
	/* Required instance variables for all games */
	private Player _CM, _CB, _SP;
	private GCReceiver _Receiver;
	private GameState _currState;
	private boolean _newGame;
	private boolean GUI;
	private boolean win;
	
	/* Game user interfaces */
	private MainGUIPanel _GUI;
	private MainCLI _CLI;

	/* Dynamically defined primitives set at start of game  */
	private static final Integer DEFAULT_GAME_ROWS = 10;
	private static final Integer DEFAULT_GAME_PEG_SIZE = 4;
	private static final String DEFAULT_GAME_MODE = "Novice";
	private static String next;
	private String gameMode;
	private int gameRows;
	private int gamePegSize;
	@SuppressWarnings("unused")
	private int count;
	
	public GameController(String userInterface) {
		_CM = new Human(this, _Receiver);
		_CB = new Human(this, _Receiver);
		_SP = new SystemPlayer();
		_Receiver = new GCReceiver(this);
		_newGame = false;
		count = 0;
		next = "nope";
		if (userInterface.equals("GUI")) {
			_GUI = new MainGUIPanel(this, DEFAULT_GAME_ROWS, DEFAULT_GAME_PEG_SIZE);
			GUI = true;
		}
		setGamePegSize(DEFAULT_GAME_PEG_SIZE);
		setGameRows(DEFAULT_GAME_ROWS);
		setGameMode(DEFAULT_GAME_MODE);
		if (GUI){
			_Receiver.addState(new GameState());
		}
		if (userInterface.equals("CLI")) {
			_newGame = true;
			_CLI = new MainCLI(this);
			_Receiver.addState(new GameState());
			_CLI.start();
			GUI = false;
		}
	}
	
	// Getters
	
	private Player getCurrentPlayer() {
		if (_currState.getNumGamePlays() % 2 == 0) {
			return _CM;
		}
		else {
			return _CB;
		}
	}
	
	public String getGameMode() {
		return this.gameMode;
	}
	
	public int getPegSize() {
		return this.gamePegSize;
	}
	
	public int getRows() {
		return this.gameRows;
	}
	
	// Setters
	
	public void setConfiguration(ArrayList<String> settings) {
		for (int i = 0; i < settings.size(); i++) {
			if (i == 0) {
				if (settings.get(i).equals("Human")) {
					_CB = new Human(this, _Receiver);
				} else {
					_CB = new Computer(this); }
			} else if (i == 1 && _newGame == true) {
				if (settings.get(i).equals("Human")) {
					_CM = new Human(this, _Receiver);
					_GUI.newHumanSolution();
				} else {
					_CM = new Computer(this); 
					_GUI.newComputerSolution();
					addAnswer(((Computer) _CM).fillColor(gamePegSize));
				}
			} else if (i == 2) {
				if (settings.get(i).equals("true")) {
					new EnableLogCommand(_Receiver, settings.get(i+1)).execute();
				} else {
					new DisableLogCommand(_Receiver).execute(); }
			} else if (i == 5) {
				if (_CM.getName().equals("model.Computer")) {
					((Computer) _CM).setWaitTime(Integer.parseInt(settings.get(i)));
				} else if (_CB.getName().equals("model.Computer")) {
					((Computer) _CB).setWaitTime(Integer.parseInt(settings.get(i)));
				}
			} else if (i == 6 && _newGame) {
				setGameRows(Integer.parseInt(settings.get(i)));
			} else if (i == 7 && _newGame) {
				setGameMode(settings.get(i));
				_GUI.drawBoard();
				_newGame = false;
			}
		}
	}
	
	
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	
	public void setGameRows(int gameRows) {
		this.gameRows = gameRows;
		if (_GUI != null) {
			_GUI.updateGameRows(gameRows);
		}
	}
	
	/** CLI updateCodeBreaker */
	public void updateCodeBreaker(ArrayList<String> strings) {
		if(strings.get(0).equals("h")){
			_CB = new Human(this, _Receiver);
		}
		else {
			_CB = new Computer(this);
		}
	}
	
	public void updateCodeMaker(String s) {
		if(s.equals("h")){
			_CM = new Human(this, _Receiver);
		}
		else {
			_CM = new Computer(this);
		}
	}
	
	
	public void setGamePegSize(int gamePegSize) {
		this.gamePegSize = gamePegSize;
		if(_GUI != null) {
			_GUI.updateGameRows(gamePegSize);
		}
	}
	
	public void setNewGame(boolean b){
		_newGame = false;
	}
	
	public void setGameState(GameState newState) {
		_currState = newState;
		ArrayList<String> done = new ArrayList<String>();
		done.add("BLACK");
		done.add("BLACK");
		done.add("BLACK");
		done.add("BLACK");
		count +=1;
		if (_currState.getLastFeedback() != null) {
			if(_currState.getStatus().contains("won") || _currState.getLastFeedback().converToArray().equals(done)){
				win = true;
				next = "win";
			}
		}
		if (GUI) { 
			GUIUpdate();
		} else if (!GUI) {
			CLIUpdate();
		}
	}
	
	public void CLIUpdate(){
		if(_CLI != null) {
			try{ _CLI.setGuess(_currState.getLastGuess().toString()); }
			catch (IndexOutOfBoundsException e){}
		}
		if( _currState.getLastFeedback() != null){
			_CLI.setFeedback(_currState.getLastFeedback().toString());
			_CLI.setGuess(_currState.getLastGuess().toString());
		}
	}
	
	public void GUIUpdate(){
		_GUI.setStatus(_currState.getStatus());
		if (_currState.getAnswer() != null && !_currState.getStatus().contains("won")) {
			if (getCurrentPlayer().getName().equals("model.Computer") && _CM == getCurrentPlayer()) {
				next = "cp_feedbk";
			}
			else if (getCurrentPlayer().getName().equals("model.Computer") && _CB == getCurrentPlayer()) {
				next = "cp_guess";
			}
			if (getCurrentPlayer().getName().equals("model.Human") && _CM == getCurrentPlayer()) {
				_GUI.showFeedbackPanel();
				next = "human";
			} else if( getCurrentPlayer().getName().equals("model.Human") && _CB == getCurrentPlayer()){
				_GUI.showGuessPanel();
				next = "human";
			}
		}
	}
	
	public void drawBoard() {
		try {_GUI.redraw();} 
		catch( NullPointerException e){}
	}
	
	public void whosNext() {
		if(next.equals("cp_guess")  && getCurrentPlayer().getName().equals("model.Computer") && win == false){
			drawBoard();
			computerGuess();
		}
		else if(next.equals("cp_feedbk") && getCurrentPlayer().getName().equals("model.Computer") && win == false){
			drawBoard();
			computerFeedback();
		}
		else return;
	}
	
	public void computerFeedback(){
		if(GUI) {
				Runnable temp = new Runnable(){
	
					@Override
				public void run() {
					Feedback f = ((SystemPlayer) _SP).generateFeedback(_currState.getLastGuess(), _currState.getAnswer());
					addFeedback(f.converToArray());
					
				}
				
			};
			SwingUtilities.invokeLater(temp);

		} else {
			Feedback f = ((SystemPlayer) _SP).generateFeedback(_currState.getLastGuess(), _currState.getAnswer());
			addFeedback(f.converToArray());
		}
	}
	
	public void computerGuess() {
		Runnable temp = new Runnable(){
			@Override
			public void run() {
				((Computer) getCurrentPlayer()).submitCode(gamePegSize);
			}
		};
		SwingUtilities.invokeLater(temp);
	}
	
	public ArrayList<String> generateComputerGuess() {
		return ((Computer) getCurrentPlayer()).fillColor(gamePegSize);
	}
	
	// Additional Methods
	
	/** Adds a guess to the gameState */
	public void addGuess(ArrayList<String> pegNames) {
		Code guess = new Code(pegNames, pegNames.size());
		GameCommand newGuess = new SubmitGuessCommand(_Receiver, guess, gameRows);
		getCurrentPlayer().makeCommand(newGuess);
		if(GUI) {
			_GUI.validate();
			drawBoard();
			whosNext();
			drawBoard();
			_GUI.validate();
		}
	}
	
	/** Adds a feedback to the gameState */
	public void addFeedback(ArrayList<String> pegNames) {
		Feedback feedback = new Feedback(pegNames, gamePegSize);
		GameCommand newFeedback = new SubmitFeedbackCommand(_Receiver, feedback, gameRows);
		getCurrentPlayer().makeCommand(newFeedback);
		if(GUI) {
			_GUI.validate();
			drawBoard();
			whosNext();
			drawBoard();
			_GUI.validate();
		}
	}
	
	/** Adds an answer to the gameState */
	public void addAnswer(ArrayList<String> pegNames) {
		Code code = new Code(pegNames, gamePegSize);
		GameCommand newGuess = new SubmitAnswerCommand(_Receiver, code);
		getCurrentPlayer().makeCommand(newGuess);
		if(GUI) {
			_GUI.validate();
			drawBoard();
			whosNext();
			drawBoard();
			_GUI.validate();
		}
	}
	
	/**
	 * Converts a list of guesses into a list of strings who's names
	 * correspond to the appropriate image file.
	 */
	public ArrayList<String> makeGuessImageNameList() {
		return Code.makeGuessImageList(_currState.getGuesses());
	}
	
	/**
	 * Converts a list of feedback into a list of strings who's names
	 * correspond to the appropriate image file.
	 */
	public ArrayList<String> makeFeedbackImageNameList() {
		return Feedback.makeFeedbackImageNameList(_currState.getFeedback());
	}
	
	/** Encapsulate answer Code in array and pass to static Code method. */
	public ArrayList<String> makeSolutionImageNameList() {
		ArrayList<Code> answerString = new ArrayList<Code>();
		answerString.add(_currState.getAnswer());
		return Code.makeGuessImageList(answerString);
	}
	
	public void startNewGame() {
		_newGame = true;
		win = false;
		getCurrentPlayer().makeCommand(new SubmitNewGameCommand(_Receiver));
	}
	
	public void undo() {
		new SubmitUndoCommand(this._Receiver).execute();
	}

	public void restartGame() {
		while( _currState.getGuesses().size() > 0){
			undo();
		}
	}
}