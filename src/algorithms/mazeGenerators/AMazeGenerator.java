package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    public abstract Maze generate(int rows, int columns);

    public long measureAlgorithmTimeMillis (int rows, int columns){
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    //generate default size of maze if rows or col too small
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


}
