/**
 * Novice.java
 * 
 * @author Jeremy
 */

package model;

import java.util.HashSet;

public class Novice implements Difficulty {

    @Override
    public boolean isLegalCode(Code code) {
    	CodePegs pegs[] = code.getCode();
        HashSet<CodePegs> seen = new HashSet<CodePegs>();
        
        for(int i = 0;i < pegs.length;i++) {
            if(seen.contains(pegs[i]))
                    return false;
            seen.add(pegs[i]);
        }
        return true;
    }

}
