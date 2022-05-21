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

        endPosition = initStartPosition(mazeArr, rows, columns, rand);

        while (startPosition.equals(endPosition)){
            endPosition = initStartPosition(mazeArr, rows, columns, rand);
        }

        //give the end value of 1 for the algorithm to reach the goal position
        mazeArr[endPosition.getRowIndex()][endPosition.getColumnIndex()] = 1;

        //go forward until no unvisited Neighbors found
        currPosition = startPosition;
        while (!currPosition.equals(endPosition)) {
            nextPosition = currPosition;
            ArrayList<Position> allNeighbors = getNeighboursList(nextPosition, mazeArr, 1, 1);
            //if no neighbors with value 1 search for neighbors with value 0 and continue
            if (allNeighbors.size() <= 0) {
                allNeighbors = getNeighboursList(nextPosition, mazeArr, 1, 0);
            }
            currPosition = allNeighbors.get(rand.nextInt(allNeighbors.size()));
            mazeArr[nextPosition.getRowIndex()][nextPosition.getColumnIndex()] = 0;
        }
        //give back the end position value of 0
        mazeArr[endPosition.getRowIndex()][endPosition.getColumnIndex()] = 0;

        //put random 1/0 in all cells that are not the path(0 value)
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++) {
                if (mazeArr[i][j] != 0)
                    mazeArr[i][j] = rand.nextInt(2);
            }
        return new Maze(mazeArr, startPosition, endPosition);
    }
}
