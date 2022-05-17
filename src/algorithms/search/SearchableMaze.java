package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public class SearchableMaze implements ISearchable{
    Maze myMaze;

    public SearchableMaze(Maze maze){
        this.myMaze = maze;
    }


    @Override
    public LinkedList<AState> getAllPossibleStates(AState state) {
        int x=0;
        int y = 0;


        MazeState mState = (MazeState) state;
        Position statePosition = mState.getPosition();
        LinkedList<AState> AllPossibleStates = new LinkedList<>();
        int currRow = statePosition.getRowIndex();
        int currCol = statePosition.getColumnIndex();
        int[][] PosShift = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{0,1},{1,-1}};  //clock cycle starts at 9
        for(int i = 0; i < 8; i++){
                int row = currRow + PosShift[i][0];
                int col = currCol + PosShift[i][1];
                boolean valideRange = insideMaze(row, col);




        }



        return null;
    }




    private boolean insideMaze(int rowIndex, int colIndex){
        int rows = myMaze.getMaze().length;
        int cols = myMaze.getMaze()[0].length;
        return rowIndex >= 0 && rowIndex < rows && colIndex >= 0 && colIndex < cols;


    }
}

//
//        if (rowIndex + i < maze.length) {
//        if (maze[rowIndex + i][colIndex] == 1)
//        result.add(new Position(rowIndex + i, colIndex));
//        }
//        //Up
//        if (rowIndex - i >= 0) {
//        if (maze[rowIndex - i][colIndex] == 1)
//        result.add(new Position(rowIndex - i, colIndex));
//        }
//        //Left
//        if (colIndex - i >= 0) {
//        if (maze[rowIndex][colIndex - i] == 1)
//        result.add(new Position(rowIndex, colIndex - i));
//        }
//        //Right
//        if (colIndex + i < maze[0].length) {
//        if (maze[rowIndex][colIndex + i] == 1)
//        result.add(new Position(rowIndex, colIndex + i));
//        }
//        return result;