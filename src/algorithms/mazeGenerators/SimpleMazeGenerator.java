package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {


    @Override
    public Maze generate(int rows, int columns) {
        Random rand = new Random();

        Position startPosition, currPosition, nextPosition, endPosition;

        //initial all cells to 1 - no path
        int[][] mazeArr = defaultMaze(rows, columns);

        //put 1 in all cells
        generateMazeGrid(mazeArr, rows, columns, 1);

        //create start position
        startPosition = initStartPosition(mazeArr, rows, columns, rand);

        //go forward until no unvisited Neighbors found
        currPosition = startPosition;
        boolean flag = true;
        while(flag){
            nextPosition = currPosition;
            ArrayList<Position> allNeighbors= getNeighboursList(nextPosition, mazeArr, 1);
            if(allNeighbors.size() > 0){
                currPosition = allNeighbors.get(rand.nextInt(allNeighbors.size()));
                mazeArr[nextPosition.getRowIndex()][nextPosition.getColumnIndex()] =0;
            }
            else flag = false;
        }

        endPosition = currPosition;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++){
                if(mazeArr[i][j] != 0)
                    mazeArr[i][j] = rand.nextInt(2);
            }
        return new Maze(mazeArr, startPosition, endPosition);
    }
}