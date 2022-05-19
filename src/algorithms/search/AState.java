package algorithms.search;

public abstract class AState{
    protected String state;
    protected int cost;
    protected AState previousState;

    public AState(int cost, String stateS){
        this.state = stateS;
        this.cost = cost;
        this.previousState = null;
    }

    public int getCost() {
        return cost;
    }

    public AState getPreviousState() {
        return previousState;
    }

    public void setPreviousState(AState previousState) {
        this.previousState = previousState;
    }

    /**
     *
     * @param state - compare state to state by cost value
     * @return int - 1 bigger, 0, equal, -1 smaller
     */
    public int compareCost(AState state){
        return Integer.compare(this.cost, state.getCost());
    }

    @Override
    public abstract boolean equals(Object o);

    public abstract boolean isTheSame(Object o);

    @Override
    public int hashCode(){
        return this.state.hashCode();
    }
}
