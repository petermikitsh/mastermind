/**
 * GUIObserver.java
 * 
 * A ConcreteObserver for the observer pattern.
 * 
 * @author peter
 */

package model;

public class GUIObserver implements GameObserver {

	private GameState _subject;
	private GameController _gameController;
	@SuppressWarnings("unused")
	private String next;
	
	public GUIObserver(GameController gameController) {
		this._gameController = gameController;
	}
	
	/**
	 * @see GameObserver#update()
	 */
	public void update(GameSubject subject) {
		_subject = subject.getState();
		_gameController.setGameState(this._subject);
		this._gameController.drawBoard();
	}
	
	public GameState getState() {
		return this._subject;
	}

}
