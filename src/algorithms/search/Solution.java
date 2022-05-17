package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> path;

    //stack from end to start

    public Solution(ArrayList<AState> path) {
        this.path = path;
    }

    public ArrayList<AState> getSolutionPath(){
        return this.path;
    }
}
