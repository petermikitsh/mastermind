/**
 * MainGUIPanel.java
 * 
 * Instantiates all subpanels necessary to build GUI. Additionally,
 * Passes all information from controller to GUI and from GUI to controller.
 * 
 * @author Evan and Peter
 */

package gui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import model.GameController;

public class MainGUIPanel extends JFrame{

	private static final long serialVersionUID = 1L;
	private FileMenu _fileMenu;
	private SubmitGuessPanel _guessPanel;
	private SubmitFeedbackPanel _feedbackPanel;
	private BoardPanel _boardPanel;
	private FeedBackPegsPanel _feedbackPegsPanel;
	private GameController _gameController;
	private JLabel _gameStatus;
	private SolutionPanel _solutionPanel;
	public JPanel _cards;
	//private Integer gameRows;
	//private Integer gamePegSize;
	
	/**  mainGUIPanel constructor */
	public MainGUIPanel(GameController gameController, Integer gameRows, Integer gamePegSize) {
		
		_solutionPanel = new SolutionPanel(this);
		
		_gameStatus = new JLabel("Game Status: ");
		
		this._gameController = gameController;
		_fileMenu = new FileMenu();
		_feedbackPanel = new SubmitFeedbackPanel();
		_guessPanel = new SubmitGuessPanel();
		//this.gameRows = gameRows;
		//this.gamePegSize = gamePegSize;
		_boardPanel = new BoardPanel(gameRows, gamePegSize);
		_feedbackPegsPanel = new FeedBackPegsPanel(gameRows, gamePegSize);
		
		_cards = new JPanel(new CardLayout());
		
		((CardLayout) _cards.getLayout()).addLayoutComponent(_guessPanel.makeGuessPanel(this), "guess");
		((CardLayout) _cards.getLayout()).addLayoutComponent(_feedbackPanel.makeFeedPanel(this), "feedback");
		_cards.add(_guessPanel.makeGuessPanel(this), "guess");
		_cards.add(_feedbackPanel.makeFeedPanel(this), "feedback");
		
		setLayout(new BorderLayout());
		
		add(_gameStatus, BorderLayout.NORTH);
		add(_boardPanel, BorderLayout.CENTER);
		add(_feedbackPegsPanel, BorderLayout.EAST);
		add(_cards, BorderLayout.SOUTH);
		
		setJMenuBar(_fileMenu.makeFileMenu(this));
		
		setSize(650,800);
		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 
		// Determine the new location of the window
		int w = getSize().width;
		int h = getSize().height;
		int x = (dim.width-w)/2;
		int y = (dim.height-h)/2;
		 
		// Move the window
		setLocation(x, y);
		setVisible(true);
		setResizable(true);
		setTitle("Mastermind");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/** gets the solution panel */
	public SolutionPanel getSolutionPanel() {
		return _solutionPanel;
	}

	/** shows the feedback panel */
	public void showFeedbackPanel() {
		((CardLayout)_cards.getLayout()).show(_cards, "feedback");
	}
	
	/** shows the guess panel */
	public void showGuessPanel() {
		((CardLayout)_cards.getLayout()).show(_cards, "guess");
	}

	/** initializes a new game in the controller */
	public void newGame() {
		_gameController.startNewGame();
	}
	
	/** redraws guesses, feedback, and solutions */
	public void redraw() {
		_boardPanel.redrawGuesses(_gameController.makeGuessImageNameList());
		//System.out.println("GUI redrawing with " + _gameController.makeGuessImageNameList().toString());
		//System.out.println("GUI redrawing with " + _gameController.makeFeedbackImageNameList().toString());
		_feedbackPegsPanel.redrawFeedback(_gameController.makeFeedbackImageNameList());
		_solutionPanel.redrawSolution(_gameController.makeSolutionImageNameList());
		validate();
	}
	
	public void drawBoard() {
		_boardPanel.makeNewBoard();
		_feedbackPegsPanel.makeNewFeedbackBoard();
		_boardPanel.repaint();
		validate();
	}
	
	/** adds a guess to the game state */
	public void addGuess(ArrayList<String> pegNames) {
		_gameController.addGuess(pegNames);
	}
	
	/** adds a feedback to the game state */
	public void addFeedback(ArrayList<String> pegNames) {
		_gameController.addFeedback(pegNames);
	}
	
	/**sets up for a human solution imput */
	public void newHumanSolution() {
		_solutionPanel.newGame();
	}
	
	/**sets up for a computer solution imput */
	public void newComputerSolution() {
		_solutionPanel.newGameComputer();
	}
	
	/** modifies the settings for the current game */
	public void setOptions(ArrayList<String> settings) {
		_gameController.setConfiguration(settings);
	}
	
	/** sets the solution code for the current game */
	public void setSolutionCode(ArrayList<String> str) {
		_gameController.addAnswer(str);
	}
	
	/** sets the status for the current game */
	public void setStatus(String s) {
		if( s != null){
			_gameStatus.setText("Game Status: " + s);
		}
	}
	
	/** performs an undo command (implemented in controller) */
	public void undo() {
		_gameController.undo();
	}
	
	/** Redraw panels for new number of game rows */
	public void updateGameRows(int gameRows) {
		_boardPanel.updateGameRows(gameRows);
		_feedbackPegsPanel.setGameRows(gameRows);
	}
	
	/** Redraw panels for new peg size */
	public void updatePegSize(int pegSize) {
		_boardPanel.updatePegSize(pegSize);
		_feedbackPegsPanel.setPegSize(pegSize);
	}

}
