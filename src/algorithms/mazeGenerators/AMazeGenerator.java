package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public abstract class AMazeGenerator implements IMazeGenerator {

    public abstract Maze generate(int rows, int columns);

    /**
     *
     * @param rows - rows num
     * @param columns cols num
     * @return - long time diff
     */
    public long measureAlgorithmTimeMillis (int rows, int columns){
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    /**
     *
     * @param rows - rows number
     * @param columns - cols number
     * @return -  generate default size of maze if rows or col too small
     */
    public int[][] defaultMaze(int rows, int columns){
        int[][] maze;
        if(rows < 3 && columns < 3)
            maze = defaultMaze(5,5);
        else if(rows < 3)
            maze = defaultMaze(5, columns);
        else if(columns < 3)
            maze = defaultMaze(rows, 5);
        else maze = new int[rows][columns];

        return maze;
    }

    /**
     *puts 0 or 1 in all maze grid cells
     * @param grid - int[][]
     * @param rows - rows number
     * @param cols - cols number
     * @param value - 1 -wall, 0 -pass
     */
    protected void generateMazeGrid(int[][] grid, int rows, int cols, int value){
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                grid[i][j] = value;
    }

    /**
     *generate current position Neighbours List
     * @param currentPos - {row,co}
     * @param maze - maze grid[][]
     * @param i - delta - how far is my neighbor
     * @return - list of all neighbors of current position
     */
    protected ArrayList<Position> getNeighboursList(Position currentPos, int[][] maze, int i, int value) {
        ArrayList<Position> result = new ArrayList<>();
        int rowIndex = currentPos.getRowIndex();
        int colIndex = currentPos.getColumnIndex();
        //Down
        if (rowIndex + i < maze.length) {
            if (maze[rowIndex + i][colIndex] == value)
                result.add(new Position(rowIndex + i, colIndex));
        }
        //Up
        if (rowIndex - i >= 0) {
            if (maze[rowIndex - i][colIndex] == value)
                result.add(new Position(rowIndex - i, colIndex));
        }
        //Left
        if (colIndex - i >= 0) {
            if (maze[rowIndex][colIndex - i] == value)
                result.add(new Position(rowIndex, colIndex - i));
        }
        //Right
        if (colIndex + i < maze[0].length) {
            if (maze[rowIndex][colIndex + i] == value)
                result.add(new Position(rowIndex, colIndex + i));
        }
        return result;
    }

    /**
     *create starting position - put zero in a random cell
     * @param mazeGrid - grid
     * @param rows - num of rows
     * @param cols - num of columns
     * @param rand - random
     * @return random start position initialed to 0
     */
    protected Position initStartPosition(int[][] mazeGrid, int rows, int cols ,Random rand){
        int randRow, randCol;
        int[] colRange = {0, cols-1};
        randRow = rand.nextInt(rows);
        if(randRow > 0 && randRow < rows){
            randCol = colRange[rand.nextInt(2)];
        }
        else{randCol = rand.nextInt(cols);}
        Position startPosition = new Position(randRow, randCol);
        mazeGrid[randRow][randCol] = 0;
        return startPosition;
    }
}
