package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    private final ArrayList<AState> solutionPath;

    /**
     * constructor
     * @param mazePath - arraylist
     */
    public Solution(ArrayList<AState> mazePath) {
        this.solutionPath = mazePath;
    }

    public ArrayList<AState> getSolutionPath(){
        return this.solutionPath;
    }
}
