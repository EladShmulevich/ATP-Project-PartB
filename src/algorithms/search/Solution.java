package algorithms.search;

import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> solutionPath;

    //stack from end to start

//    public Solution(ArrayList<AState> path) {
//        this.path = path;
//    }

//    public Solution(AState endState) {
//        if(endState != null){
//            this.path = new ArrayList<AState>();
//            while(endState != null){
//                path.add(0, endState);
//                endState = endState.getPreviousState();
//            }
//        }
//    }


    public Solution(ArrayList<AState> mazePath) {
        this.solutionPath = mazePath;
    }


    public ArrayList<AState> getSolutionPath(){
        return this.solutionPath;
    }
}
