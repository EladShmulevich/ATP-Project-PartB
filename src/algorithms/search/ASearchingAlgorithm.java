package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    public int numOfNodesEve;

    public String getName(){
        return this.getClass().getSimpleName();
    }
    public int getNumberOfNodesEvaluated(){return this.numOfNodesEve;}
    public abstract Solution solve(ISearchable domain);
}
