/**
 * GCReciever.java
 * 
 * The ConcreteSubject for the Observer pattern
 * and a receiver for the Command pattern.
 */

package model;

import java.util.ArrayList;

public class GCReceiver implements GameSubject {

	private ArrayList<GameObserver> _observers;
	private ArrayList<GameState> _pastStates;
	private Log _log;
	
	public GCReceiver(GameController gameController) {
	    _observers = new ArrayList<GameObserver>();
	    _observers.add(new GUIObserver(gameController));
	    _pastStates = new ArrayList<GameState>();
	    _log = new Log();
	}
	
	public ArrayList<GameState> getPastStates() {
		return _pastStates;
	}

	/**
	 * @see GameSubject#attach(GameObserver)
	 */
	public void attach(GameObserver gameObserver) {
		this._observers.add(gameObserver);
	}
	
	/**
	 * @see GameSubject#detach(GameObserver)
	 */
	public void detach(GameObserver gameObserver) {
		this._observers.remove(gameObserver);
		
	}
	
	/**
	 * @see GameSubject#notifyObservers()
	 */
	public void notifyObservers() {
		for(GameObserver observer : this._observers) {
			observer.update(this);
			//System.out.println("sup " + observer.toString());
		}
		//System.out.println("sup player observer");
	}
	
	/**
	 * @see GameSubject#getState()
	 */
	public GameState getState() {
		return this._pastStates.get(this._pastStates.size() - 1);
	}
	
	/**
	 * @see GameSubject#addState(GameState)
	 */
	public void addState(GameState newState){
		this._pastStates.add(newState);
		notifyObservers();
	}

	/**
	 * Undo x number of game states.
	 * @param numberOfStatesToUndo Number of game states to undo.
	 */
	public void undoStates(int numStatesUndo) {
		
	    for(int i = numStatesUndo; i > 0; i--)
	        this._pastStates.remove(_pastStates.size() - 1);
	    
	    GameState tempState = new GameState(getState());
	    this._pastStates.remove(this._pastStates.size() - 1);
	    
	    this.addState(tempState);
	}
	
	/**
	 * Sends a command to be written to the log file.
	 * @param event A string representation of the command.
	 */
	public void sendToLog(String event) {
		if(_log.getEnabled())
			_log.addEvent(event);
	}
	
	/**
	 * Write-out the GameState to a log.
	 * @param filename
	 */
	public void enableLog(String filename) {
		if(_log.getEnabled()) {
			_log.setEnabled(false);
		}
		_log.setFilename(filename);
		_log.setEnabled(true);
		for(String event : getState().createLogFromGameState())
			_log.addEvent(event);
	}
	
	public void updateStatus(String newStatus) {
		GameState recentState = getState();
		recentState.setStatus(newStatus);
		notifyObservers();
	}
	
	/**
	 * Disables the log from outputting to a file.
	 */
	public void disableLog() {
		_log.setEnabled(false);
	}
	
	public void clearHistory() {
		GameState tempState = new GameState(_pastStates.get(0));
		_pastStates.clear();
		_pastStates.add(tempState);
	}
}
