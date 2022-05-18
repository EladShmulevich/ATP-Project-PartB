package algorithms.search;
import java.util.*;


public class DepthFirstSearch extends ASearchingAlgorithm{

    public Solution solve(ISearchable domain) {
        return new Solution(dfsAlgorithm(domain));
    }

    /**
     *
     * @param domain - the object to search on
     * @return array list of positions - the maze path(start to end)
     */
    private ArrayList<AState> dfsAlgorithm(ISearchable domain) {
        if (domain == null) {
            return null;
        }
        AState currState;
        AState goalState = domain.getGoalState();
        AState startState = domain.getStartState();
        HashSet<AState> visitedNodes = new HashSet<AState>();

        ArrayList<AState> visitedinList = new ArrayList<AState>();

        Stack<AState> statesStack = new Stack<AState>();
        ArrayList<AState> solutionPath = new ArrayList<AState>();

        statesStack.push(startState);
        while (!statesStack.empty()) {
            currState = statesStack.pop();
            solutionPath.add(currState);
            numOfNodesEve++;
            if (visitedNodes.contains(currState))
                continue;
//            visitedNodes.add(currState);
            int i =0;
            for (AState state : domain.getAllPossibleStates(currState)) {
                if (state.equals(goalState)) {
                    solutionPath.add(state);
                    numOfNodesEve++;
                    return solutionPath;
                }
                if (!visitedNodes.contains(state))
                    statesStack.push(state);
                else {i++;}
                if(domain.getAllPossibleStates(currState).size() == i){
                    visitedNodes.add(currState);
                    for (AState visitedNode : visitedinList){
                        if(!DoesIHaveUnVisitedNeighbors(visitedNode, domain, visitedNodes)){
                            solutionPath.add(visitedNode);
                        }
                        else{
                            solutionPath.add(visitedNode);
                            break;
                        }

                    }
                }
            }
            visitedNodes.add(currState);
            if(!visitedinList.contains(currState)){
                visitedinList.add(0, currState);
            }

        }
        return solutionPath;
    }


    private boolean DoesIHaveUnVisitedNeighbors(AState state, ISearchable domain, HashSet<AState> allVisitedNodes){
        for(AState visited : domain.getAllPossibleStates(state))
            if(!allVisitedNodes.contains(visited))
                return true;
        return false;
    }

}

