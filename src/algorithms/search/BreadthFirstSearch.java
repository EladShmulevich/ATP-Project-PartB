package algorithms.search;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class BreadthFirstSearch extends ASearchingAlgorithm{

    public Solution solve(ISearchable domain) {
        return new Solution(bfsAlgorithm(domain));
    }


    /**
     *
     * @param domain - something to search on
     * @return - ArrayList of the States path
     */
    private ArrayList<AState> bfsAlgorithm(ISearchable domain){
        if (domain == null) {
            return null;
        }
        Queue<ArrayList<AState>> queuePaths = new LinkedList<ArrayList<AState>>();
        AState startState = domain.getStartState();
        AState goalState = domain.getGoalState();
        int numLevels = 0;


        LinkedList<AState> neighborList;
        HashSet<AState> visitedNodes = new HashSet<AState>();

        ArrayList<AState> path = new ArrayList<AState>();
        //first path
        path.add(startState);
//        numOfNodesEve ++;
        //add first path to queue path
        queuePaths.add(path);
        while (queuePaths.size() > 0){
            path = queuePaths.poll();
            //get the last move from the path
            AState lastState = path.get(path.size()-1);
//            numOfNodesEve ++;
            visitedNodes.add(lastState);
            //path found
            if(lastState.equals(goalState)){
                numOfNodesEve += visitedNodes.size();
                return path;
            }
            else{
                neighborList = domain.getAllPossibleStates(lastState);
                for (AState aState : neighborList) {
                    if (!visitedNodes.contains(aState)) {
                        ArrayList<AState> newPath = new ArrayList<AState>(path);
                        newPath.add(aState);
                        queuePaths.add(newPath);
                    }
                }
            }
        }
        //if nothing found in the while loop
        numOfNodesEve += visitedNodes.size();
        return null;
    }
}
