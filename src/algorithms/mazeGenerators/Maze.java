package algorithms.mazeGenerators;


public class Maze {

    private final int[][] maze;
    private final Position startPosition;
    private final Position endPosition;

    public Maze(int[][] maze, Position startPosition, Position endPosition) {
        this.maze = maze;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }


    /**
     * @return start position
     */
    public Position getStartPosition(){
        return  this.startPosition;
    }

    /**
     *
     * @return end position
     */
    public Position getGoalPosition(){
        return  this.endPosition;
    }

    /**
     *
     * @return the maze array
     */
    public int[][] getMaze() {
        return maze;
    }

    public void print(){
        for (int row = 0; row < maze.length; row++) {
            for(int col = 0; col < maze[0].length; col++){
                if(row == this.getStartPosition().getRowIndex() && this.getStartPosition().getColumnIndex() == col)
                    System.out.print("S");
                else  if(row == this.getGoalPosition().getRowIndex() && this.getGoalPosition().getColumnIndex() == col)
                    System.out.print("E");
                else
                    System.out.print(maze[row][col]);
            }
            System.out.print("\n");
        }
    }
}
