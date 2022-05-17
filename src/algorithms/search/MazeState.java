package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    Position mazePosition;

    public MazeState(int cost, Position pos) {
        super(cost);
        this.mazePosition = pos;
    }

    public int compareCost(Object obj){
        AState state = (AState) obj;
        return Integer.compare(this.cost, state.getCost());
    }

    public Position getPosition() {
        return mazePosition;
    }
}
