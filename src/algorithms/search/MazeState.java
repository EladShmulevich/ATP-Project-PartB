package algorithms.search;
import algorithms.mazeGenerators.Position;

import java.util.Objects;


public class MazeState extends AState{
    Position mazePosition;
    /**
     *
     * @param cost - the cost until this state(position)
     * @param pos - the position(row,index) of this state
     */
    public MazeState(int cost, Position pos) {
        super(cost, pos.toString());
        this.mazePosition = pos;
    }


    public Position getPosition() {
        return mazePosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeState other = (MazeState) o;
        return (other.getPosition().getRowIndex() == this.getPosition().getRowIndex()
                && other.getPosition().getColumnIndex() == this.getPosition().getColumnIndex());
    }

    @Override
    public String toString() {
        return "MazeState{" +
                "mazePosition=" + mazePosition +
                '}';
    }
}
