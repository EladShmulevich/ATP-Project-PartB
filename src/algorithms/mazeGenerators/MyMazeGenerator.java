package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Stack<Position> pStack = new Stack<Position>();
        ArrayList<Position> path = new ArrayList<Position>();     //save the path of the maze
        Position startPosition, endPosition;
        Random rand = new Random();


        int[][] mazeArr = defaultMaze(rows, columns);
        //start with all maze with 1 - no path
        generateMazeGrid(mazeArr, rows, columns, 1);

        //create start position
        startPosition = initStartPosition(mazeArr, rows, columns, rand);

        return dfsGenerator(mazeArr, rows, columns, startPosition, rand);
    }

    /**
     * make a solvable maze by dfs algorithm
     * @param maze - maze[][]
     * @param rows - rows num
     * @param cols - cols num
     * @param startPosition {row,col}
     * @param rand random
     */
    private Maze dfsGenerator(int[][] maze, int rows, int cols, Position startPosition, Random rand){
        Stack<Position> pStack = new Stack<Position>();
        ArrayList<Position> path = new ArrayList<Position>();     //save the path of the maze
        Position currPosition, nextPosition;
        ArrayList<Position> allNeighbors;

        nextPosition = startPosition;
        //push startPosition to stack
        pStack.push(nextPosition);
        while(!pStack.isEmpty()){
            currPosition = (pStack.pop());
            boolean unVisitedNeighbor = DoesIHaveUnVisitedNeighbors(currPosition,rows,cols,maze);
            if(unVisitedNeighbor){
                pStack.push(currPosition);
                allNeighbors = getNeighboursList(currPosition, maze, 2, 1);
                nextPosition = allNeighbors.get(rand.nextInt(allNeighbors.size()));

                // break the walls and add them to the path
                path.add(breakWalls(maze, currPosition.getRowIndex(), nextPosition.getRowIndex(), currPosition.getColumnIndex(), nextPosition.getColumnIndex()));

                //mark the neighbor as visited
                maze[nextPosition.getRowIndex()][nextPosition.getColumnIndex()] = 0;
                pStack.push(nextPosition);
                path.add(nextPosition);
            }
        }
        //select random end position and take care its on the frame!
        Position endPosition = path.get(rand.nextInt(path.size()));
        while (!isOnFrame(endPosition, rows, cols)){
            if(path.size()>1)
                path.remove(endPosition);
            endPosition = path.get(rand.nextInt(path.size()));
        }
        return new Maze(maze, startPosition, endPosition);
    }

    /**
     *check if a position have any neighbors
     * @param currentPosition {row,col}
     * @param rows - rows num
     * @param columns - cols num
     * @param maze - maze grid
     * @return true if the curr position have unvisited neighbors
     */
    public boolean DoesIHaveUnVisitedNeighbors (Position currentPosition,int rows, int columns,int[][] maze) {
        boolean bool = false;
        int i =2;
        //down
        if (currentPosition.getRowIndex() + i < rows) {
            if (maze[(currentPosition.getRowIndex() + i)][currentPosition.getColumnIndex()] == 1)
                bool = true;
        }
        //up
        if (currentPosition.getRowIndex() - i >= 0) {
            if (maze[(currentPosition.getRowIndex() - i)][currentPosition.getColumnIndex()] == 1)
                bool = true;
        }
        // right
        if (currentPosition.getColumnIndex() + i < columns) {
            if (maze[(currentPosition.getRowIndex())][currentPosition.getColumnIndex() + i] == 1)
                bool = true;
        }
        //left
        if (currentPosition.getColumnIndex() - i >= 0) {
            if (maze[(currentPosition.getRowIndex())][currentPosition.getColumnIndex() - i] == 1)
                bool = true;
        }
        return bool;
    }

    /**
     *breaks the "wall" between 2 passes(0's)
     * @param maze - mazegrid [][]
     * @param currRow -curr row position
     * @param nextRow - next row position
     * @param currCol - curr col position
     * @param nextCol - next col position
     */
    private Position breakWalls(int[][] maze, int currRow, int nextRow, int currCol, int nextCol){
        if (currRow - nextRow == 2){
            maze[currRow - 1][currCol] = 0;
            return new Position(currRow - 1, currCol);
        }
        if (currRow - nextRow == -2){
            maze[currRow + 1][currCol] = 0;
            return new Position(currRow + 1, currCol);
        }
        if (currCol - nextCol == 2){
            maze[currRow][currCol - 1] = 0;
            return new Position(currRow, currCol - 1);
        }
        if (currCol - nextCol == -2)
            maze[currRow][currCol + 1] = 0;
            return new Position(currRow, currCol + 1);
    }

    /**
     *
     * @param pos - position
     * @param rows - rows num
     * @param cols - cols num
     * @return - true if position is on maze frame
     */
    private boolean isOnFrame(Position pos, int rows, int cols){
            int rowIndex = pos.getRowIndex();
            int colIndex = pos.getColumnIndex();
        return (rowIndex <= 0 || rowIndex >= rows-1) || (colIndex <= 0 || colIndex >= cols-1);
    }
}
