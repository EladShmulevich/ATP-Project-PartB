package algorithms.search;
import algorithms.mazeGenerators.Position;


public class MazeState extends AState{
    Position mazePosition;

    /**
     *
     * @param cost - the cost until this state(position)
     * @param pos - the position(row,index) of this state
     */
    public MazeState(int cost, Position pos) {
        super(cost);
        this.mazePosition = pos;
    }

    /**
     *
     * @param obj - compare state to state by cost value
     * @return int - 1 bigger, 0, equal, -1 smaller
     */
    public int compareCost(Object obj){
        AState state = (AState) obj;
        return Integer.compare(this.cost, state.getCost());
    }

    public Position getPosition() {
        return mazePosition;
    }
}
