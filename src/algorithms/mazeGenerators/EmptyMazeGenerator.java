package algorithms.mazeGenerators;

import java.util.Random;

public class EmptyMazeGenerator extends AMazeGenerator {


    @Override
    public Maze generate(int rows, int columns) {
        int[][] mazeArr = defaultMaze(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mazeArr[i][j] = 0;
            }
        }
        Random rand = new Random();
        Position startP = new Position(rand.nextInt(rows), rand.nextInt(columns));

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
