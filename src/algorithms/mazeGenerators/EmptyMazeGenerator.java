package algorithms.mazeGenerators;

import java.util.Random;

public class EmptyMazeGenerator extends AMazeGenerator {


    @Override
    public Maze generate(int rows, int columns) {
        Random rand = new Random();
        int[][] mazeArr = defaultMaze(rows, columns);

        //put 0 in all cells
        generateMazeGrid(mazeArr, rows, columns, 0);

        //init random start position
        Position startP = initStartPosition(mazeArr, rows, columns, rand);

        //init random end position
        Position endP = initStartPosition(mazeArr, rows, columns, rand);

        while (startP.equals(endP)){
              endP = initStartPosition(mazeArr, rows, columns, rand);
        }
        return new Maze(mazeArr, startP, endP);
    }
}
