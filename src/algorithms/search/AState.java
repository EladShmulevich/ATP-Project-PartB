package algorithms.search;

import algorithms.mazeGenerators.Position;

public abstract class AState{
    protected int cost;
    protected AState previousState;

    public AState(int cost){
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

    //1 if bigger
    //-1 if smaller
    //0 equals
    public abstract int compareCost(Object obj);
}
