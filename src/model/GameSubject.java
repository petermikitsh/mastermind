/**
 *  GameSubject.java
 *  
 *  GameSubject defines all methods necessary for all ConcreteSubjects,
 *  which are Receivers for the Command pattern.
 *  
 *  @author peter
 */

package model;

public interface GameSubject {
	
	/**
	 * Adds an observer to the ConcreteSubject's collection of observers.
	 */
	public abstract void attach(GameObserver gameObserver);
	
	/**
	 * Removes an observer to the ConcreteSubject's collection of observers.
	 */
	public abstract void detach(GameObserver gameObserver);
	
	/**
	 * Notifies all observers of a change in the subject.
	 */
	public abstract void notifyObservers();
	
	/**
	 * Gets the most recent game state.
	 * @return The most recent game state.
	 */
	public abstract GameState getState();
	
	/**
	 * Adds a game state to the collection of game states.
	 * @param newState The game state to be added.
	 */
	public abstract void addState(GameState newState);
	
}
