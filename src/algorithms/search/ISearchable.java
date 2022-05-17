package algorithms.search;

import java.util.LinkedList;

public interface ISearchable {
    public LinkedList<AState> getAllPossibleStates(AState state);
//    public AState getStartState();
//    public AState getGoalState();

}
