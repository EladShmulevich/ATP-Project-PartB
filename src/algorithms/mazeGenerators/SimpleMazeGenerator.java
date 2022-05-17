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
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                mazeArr[i][j] = 1;

        //create start position
        startPosition = new Position(rand.nextInt(rows), rand.nextInt(columns));
        //initial the start position with 0
        mazeArr[startPosition.getRowIndex()][startPosition.getColumnIndex()] = 0;

        ArrayList<Position> path = new ArrayList<Position>();     //save the path of the maze

        //go forward until no unvisited Neighbors found
        currPosition = startPosition;
        int stop = 100;
        while(stop>0){
            nextPosition = currPosition;
            ArrayList<Position> allNeighbors= getNeighboursList(nextPosition, mazeArr);
            stop = allNeighbors.size();
            if(stop>0){
                currPosition = allNeighbors.get(rand.nextInt(allNeighbors.size()));
                path.add(currPosition);
                mazeArr[nextPosition.getRowIndex()][nextPosition.getColumnIndex()] =0;
            }
            else break;
        }

//        endPosition = currPosition;
        endPosition = path.get(rand.nextInt(path.size()));

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++){
                if(mazeArr[i][j] != 0)
                    mazeArr[i][j] = rand.nextInt(2);
            }


        return new Maze(mazeArr, startPosition, endPosition);
    }


    private ArrayList<Position> getNeighboursList(Position currentPos, int[][] maze) {
        ArrayList<Position> result = new ArrayList<>();
        int rowIndex = currentPos.getRowIndex();
        int colIndex = currentPos.getColumnIndex();
        //Down
        int i = 1;
        if (rowIndex + i < maze.length) {
            if (maze[rowIndex + i][colIndex] == 1)
                result.add(new Position(rowIndex + i, colIndex));
        }
        //Up
        if (rowIndex - i >= 0) {
            if (maze[rowIndex - i][colIndex] == 1)
                result.add(new Position(rowIndex - i, colIndex));
        }
        //Left
        if (colIndex - i >= 0) {
            if (maze[rowIndex][colIndex - i] == 1)
                result.add(new Position(rowIndex, colIndex - i));
        }
        //Right
        if (colIndex + i < maze[0].length) {
            if (maze[rowIndex][colIndex + i] == 1)
                result.add(new Position(rowIndex, colIndex + i));
        }
        return result;
    }

}