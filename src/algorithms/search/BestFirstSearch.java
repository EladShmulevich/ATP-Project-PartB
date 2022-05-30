package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{

    public Solution solve(ISearchable domain) {
        return new Solution(bestFSAlgorithm(domain));
    }

    /**
     *
     * @param domain - something to search on
     * @return - ArrayList of the States path
     */
    private ArrayList<AState> bestFSAlgorithm(ISearchable domain){

//        PriorityQueue<AState> queueStates = new PriorityQueue<AState>(Comparator.comparing(AState::getCost));
        PriorityQueue<AState> queueStates = new PriorityQueue<AState>(AState::compareCost);

        AState startState = domain.getStartState();
        AState goalState = domain.getGoalState();
        int numLevels = 0;

        LinkedList<AState> neighborList = new LinkedList<AState>();
        HashSet<AState> visitedNodes = new HashSet<AState>();

        ArrayList<AState> path = new ArrayList<AState>();
        //first path
//        numOfNodesEve ++;

        //add first path to queue path
        queueStates.add(startState);
        while (queueStates.size() > 0){
            AState currentState =  queueStates.poll();
//            numOfNodesEve ++;
            visitedNodes.add(currentState);
            //path found
            if(currentState.equals(goalState)){
                while(currentState != startState){
                    path.add(0,currentState);
                    currentState = currentState.getPreviousState();
                }
                path.add(0, startState);
//                Collections.reverse(path);
                numOfNodesEve += visitedNodes.size();
                return path;
            }
            else{
                neighborList = domain.getAllPossibleStates(currentState);
                for (AState aState : neighborList) {
                    if (!visitedNodes.contains(aState)) {
                        boolean alreadyIn = false;
                        for(AState visitedState: visitedNodes){
                            alreadyIn = aState.isTheSame(visitedState);
                        }
                        if(!alreadyIn){
                            aState.setPreviousState(currentState);
                            queueStates.add(aState);
                        }
                    }
                }
            }
        }
        //if nothing found in the while loop
        numOfNodesEve += visitedNodes.size();
        return null;
    }
}
