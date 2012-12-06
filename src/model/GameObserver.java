/**
 * GameObserver.java
 * 
 * GameObserver defines all methods necessary for all ConcreteObservers,
 * which are GUI Components in the system.
 * 
 * @author: peter
 */

package model;

public interface GameObserver {

	/**
	 * Gets the new state from the ConcreteSubject.
	 */
	public void update(GameSubject subject);
	
}
