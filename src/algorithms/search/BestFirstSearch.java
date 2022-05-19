package algorithms.search;

import java.util.*;

public class BestFirstSearch extends BreadthFirstSearch{

    public Solution solve(ISearchable domain) {
        return new Solution(bestFSAlgorithm(domain));
    }


    private ArrayList<AState> bestFSAlgorithm(ISearchable domain){

        PriorityQueue<AState> queueStates = new PriorityQueue<AState>(Comparator.comparing(AState::getCost)) ;
        AState startState = domain.getStartState();
        AState goalState = domain.getGoalState();
        int numLevels = 0;


        LinkedList<AState> neigList = new LinkedList<AState>();
        HashSet<AState> visitedNodes = new HashSet<AState>();

        ArrayList<AState> path = new ArrayList<AState>();
        //first path
        //path.add(startState);
        numOfNodesEve ++;
        //add first path to queue path
        queueStates.add(startState);
        while (queueStates.size() > 0){
            AState currentState =  queueStates.poll();
            numOfNodesEve ++;
            visitedNodes.add(currentState);
            //path found
            if(currentState.equals(goalState)){
                while(currentState != startState){
                    path.add(currentState);
                    currentState = currentState.getPreviousState();
                }
                path.add(startState);
                Collections.reverse(path);
                return path;
            }
            else{
                neigList = domain.getAllPossibleStates(currentState);
                for (AState aState : neigList) {
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
        return null;
    }


}
