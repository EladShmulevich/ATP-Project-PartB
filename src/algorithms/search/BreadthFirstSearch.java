package algorithms.search;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public Solution solve(ISearchable domain) {
        return new Solution(bfsAlgorithm(domain));
    }


    private ArrayList<AState> bfsAlgorithm(ISearchable domain){
        if (domain == null) {
            return null;
        }

        Queue<ArrayList<AState>> queuePaths = new LinkedList<ArrayList<AState>>();
        AState currState;
        AState startState = domain.getStartState();
        AState goalState = domain.getGoalState();
        int numLevels = 0;


        LinkedList<AState> neigList = new LinkedList<AState>();
        HashSet<AState> visitedNodes = new HashSet<AState>();

        ArrayList<AState> path = new ArrayList<AState>();
        //first path
        path.add(startState);
        //add first path to queue path
        queuePaths.add(path);
        while (queuePaths.size() > 0){
            path = queuePaths.poll();
            //get the last move from the path
            AState lastState = path.get(path.size()-1);
            visitedNodes.add(lastState);
            //path found
            if(lastState.equals(goalState)){
                return path;
            }
            else{
                neigList = domain.getAllPossibleStates(lastState);
                for (AState aState : neigList) {
                    if (!visitedNodes.contains(aState)) {
                        ArrayList<AState> newPath = new ArrayList<AState>(path);
                        newPath.add(aState);
                        queuePaths.add(newPath);
                    }
                }
            }
        }
        //if nothing found in the while loop
        return null;
    }
}
