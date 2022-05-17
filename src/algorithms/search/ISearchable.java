package algorithms.search;

import java.util.LinkedList;

public interface ISearchable {

    /**
     *
     * @param state - the current state
     * @return - list of all the states(position) we can go from the current state(position)
     */
    public LinkedList<AState> getAllPossibleStates(AState state);
    public AState getStartState();
    public AState getGoalState();

}
