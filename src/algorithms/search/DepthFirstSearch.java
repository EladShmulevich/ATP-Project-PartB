package algorithms.search;

import java.util.*;


public class DepthFirstSearch extends ASearchingAlgorithm {

    public Solution solve(ISearchable domain) {
        return  new Solution(dfsAlgorithm(domain));
    }
    /**
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

        //this stack will save the maze path
        Stack<AState> statesStack = new Stack<AState>();

        //start with rhe start state
        statesStack.push(startState);
        outerLoop:
        while (!startState.equals(goalState) && !statesStack.isEmpty()) {      //if we didn't reach goalState keep going
            currState = statesStack.peek();          //peek at the head of the stack - the node were currently standing on
            visitedNodes.add(currState);              //add this node to the visited nodes list
            LinkedList<AState> allPossibleStates = domain.getAllPossibleStates(currState);   //get all current list possible neighbors
            int i = 0;
            for (AState state : allPossibleStates) {
                if (allPossibleStates.contains(goalState)){  //if we already know we can reach goalState we go to it(to prevent doing 2 moves instead of 1 - diagonal)
                    statesStack.push(goalState);
                    numOfNodesEve += visitedNodes.size()+1;
                    return stackToArray(statesStack);
                }
                if (!visitedNodes.contains(state)) {   //if current node have unvisited neighbor put it in the stack
                    statesStack.push(state);
//                    numOfNodesEve++;
                    startState = state;                //now the neighbor is the current node - go back to while loop
                    continue outerLoop;
                }
                else {i++;}
            }
            if (allPossibleStates.size() == i) {   //if we didn't find unvisited neighbor
                statesStack.pop();       //we remove the node from the stack
                startState = statesStack.peek();   //then peek to the previous node
            }
        }
        numOfNodesEve += visitedNodes.size();
        return stackToArray(statesStack);
    }

    private ArrayList<AState> stackToArray(Stack<AState> stack) {
        ArrayList<AState> arr = new ArrayList<AState>();
        while (!stack.isEmpty()) {
            arr.add(stack.pop());
        }
        Collections.reverse(arr);
        return arr;
    }
}





