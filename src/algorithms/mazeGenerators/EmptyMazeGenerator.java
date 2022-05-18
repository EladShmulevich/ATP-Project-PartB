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
        int endRow = rand.nextInt(rows);
        int endCol = rand.nextInt(columns);
        Position endP = new Position(rand.nextInt(rows), rand.nextInt(columns));
        while (startP.equals(endP)){
            endP.setRowPos(rand.nextInt(rows));
            endP.setColPos(rand.nextInt(columns));
        }

        return new Maze(mazeArr, startP, endP);
    }
}
