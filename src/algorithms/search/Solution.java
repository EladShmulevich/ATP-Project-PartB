package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> solutionPath;

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
