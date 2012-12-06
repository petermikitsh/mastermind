/**
 * Log.java
 * 
 * @author Jeremy
 *
 * This class handles logging everything that happens in the game
 */

package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private boolean enabled;
    private String filename;
    private BufferedWriter output;
    
    /**
     * Constructor for the log. The default name is Log.txt
     * The log is disabled by default
     */
    public Log() {
        this.filename = "Log.txt";
        enabled = false;
    }
    
    /**
     * This function sets the log to be enabled or disabled.
     * If the log is being enabled, the file is opened and prepared.
     * If the log is being disabled, the file is closed.
     * 
     * @param enabled - true if we are enabling the log
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    	if(enabled)
			try {
				output = new BufferedWriter(new FileWriter(filename));
			} catch (IOException e) {
				System.err.println("Error opening log file for writing.");
				System.err.println(e.getMessage());
			}
		else
			try {
				if(output != null)
					output.close();
			} catch (IOException e) {
				System.err.println("Error closing log file.");
				System.err.println(e.getMessage());
			}
    }
    
    /**
     * This function returns whether or not the log is currently enabled
     * 
     * @return true if the log is enabled
     */
    public boolean getEnabled() {
        return enabled;
    }
    
    /**
     * This function writes an event to the log. This function assumes
     * that the log is already open.
     * 
     * @param event - The even to write to the log file
     */
    public void addEvent(String event) {
    	try {
			output.write(event + "\n");
			output.flush();
		} catch (IOException e) {
			System.err.println("Error writing to log file.");
			System.err.println(e.getMessage());
		}
    }
    
    /**
     * This function allows a user to use a filename other than the default name
     * 
     * @param filename - the name of the file to be the log
     */
    public void setFilename(String filename) {
    	this.filename = filename;
    }
}
