/**
 * Expert.java
 * 
 * Checks if a code is legal to "expert" detail.
 * 
 * @author Jeremy
 */
package model;

public class Expert implements Difficulty {
    
    @Override
    public boolean isLegalCode(Code code) {
    	//All possible codes are legal at the expert level
    	return true;
    }
}
