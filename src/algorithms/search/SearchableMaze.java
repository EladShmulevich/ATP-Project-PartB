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

    /**
     *
     * @param state - the current state
     * @return - all possible states you can go to on ArrayList
     */
    @Override
    public LinkedList<AState> getAllPossibleStates(AState state) {
        int cost;
        MazeState candidateState = (MazeState) state;
        Position statePosition = candidateState.getPosition();
        LinkedList<AState> allPossibleStates = new LinkedList<>();
        int currRow = statePosition.getRowIndex();
        int currCol = statePosition.getColumnIndex();
        int[][] PosShift = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};  //clock cycle starts at 12
        for(int i = 0; i < 8; i++){
                int row = currRow + PosShift[i][0];
                int col = currCol + PosShift[i][1];
                if(!insideMaze(row, col) || myMaze.getMaze()[row][col] != 0){ continue; }
                if (i % 2 == 0){
                    cost = 10;
                }
                else {
                    if(isDiagonalAccessible(currRow, currCol, i))
                        cost = 15;
                    else continue;
                }
                //create new state with the current cost
                candidateState = new MazeState(cost + state.getCost(), new Position(row, col));
                //add the state to the possibleStates list
                allPossibleStates.add(candidateState);
                //set the "father state" as current state
                candidateState.setPreviousState(state);
        }
        return allPossibleStates;
    }

    @Override
    public AState getStartState() {
        return new MazeState(0, myMaze.getStartPosition());
    }

    @Override
    public AState getGoalState() {
        return new MazeState(0, myMaze.getGoalPosition());
    }

    /**
     *
     * @param rowIndex - row index on maze
     * @param colIndex - col index on maze
     * @return - true if the state position is inside the maze borders, false else
     */
    private boolean insideMaze(int rowIndex, int colIndex){
        int rows = myMaze.getMaze().length;
        int cols = myMaze.getMaze()[0].length;
        return rowIndex >= 0 && rowIndex < rows && colIndex >= 0 && colIndex < cols;
    }

    /**
     *
     * @param currRow - current State Row position
     * @param currCol - current State Col position
     * @param diagonalNumber - diagonal state(1 - 3 - 5 - 7)
     * @return true if there is a path to the diagonal state through one of its neighbours
     */
    private boolean isDiagonalAccessible(int currRow, int currCol, int diagonalNumber){
        int[][] maze = myMaze.getMaze();
        int[][] candidatesStates = {{currRow,currCol},{currRow,currCol}};
        switch (diagonalNumber){
            case 1:
                candidatesStates[0][1] += 1;
                candidatesStates[1][0] -= 1;
                break;
            case 3:
                candidatesStates[0][1] += 1;
                candidatesStates[1][0] += 1;
                break;
            case 5:
                candidatesStates[0][1] -= 1;
                candidatesStates[1][0] += 1;
                break;
            case 7:
                candidatesStates[0][1] -= 1;
                candidatesStates[1][0] -= 1;
                break;
                        default:return false;
        }
        return (maze[candidatesStates[0][0]][candidatesStates[0][1]] == 0 ||
                maze[candidatesStates[1][0]][candidatesStates[1][1]] == 0);
    }
}


