/**
 * Player.java
 *
 * This is the Player interface which Humans and Computers will implement
 * from to create a GameCommand that will be passed to the Controller
 * 
 * @author Josh Eklund
 */

package model;

public interface Player {
	
	/**
	 * Allows a human or computer to package a command that will be supported
	 * inside the GameCommand Class
	 * @param newCommand is the GameCommand that will be passed from a human or computer
	 */
	public void makeCommand(GameCommand newCommand);
	
	/**
	 * The name of the player.
	 * @return "Human" or "Computer".
	 */
	public String getName();
	
}
