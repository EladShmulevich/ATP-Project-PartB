//package algorithms.mazeGenerators;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.Stack;
//
//public class MyMazeGenerator extends AMazeGenerator {
//
//    @Override
//    public Maze generate(int rows, int columns) {
//        Stack<Position> pStack = new Stack<Position>();
//
//
//        int[][] mazeArr = new int[rows][columns];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                // in the begining even is wall and odd is cell.
//                if ((i % 2 == 0) || (j % 2 == 0)) {
//                    mazeArr[i][j] = 1;
//                } else {
//                    mazeArr[i][j] = 8;
//                }
//            }
//        }
//
//
//        Position currentP = new Position(-1,-1);
//        Random rand = new Random();
//        //this is to make sure we random odd number
//        int randRow = rand.nextInt(rows); int randCol = rand.nextInt(columns);
//        randRow =(randRow/2)*2-1; randCol = (randCol/2)*2-1;
//        if(randRow < 0)
//            randRow = 1;
//        if(randCol < 0)
//            randCol = 1;
//        Position startP = new Position(randRow,randCol);
//
//        //mark startP as Visited(4)
//        mazeArr[startP.getRowIndex()][startP.getColumnIndex()] = 4;
//        //push startP to stack
//        pStack.push(startP);
//        while(!pStack.isEmpty()){
//            currentP =(pStack.pop());
//            boolean unVisitedN = DoesIHaveUnVisitedNeighbors(currentP,rows,columns,mazeArr);
//            if(unVisitedN){
//                pStack.push(currentP);
//                int moveNum = PickRandomMove(currentP,rows,columns,mazeArr);
//                Position neighbor = newPositionAndBreakWallBetween(moveNum,currentP,mazeArr);
//                mazeArr[neighbor.getRowIndex()][neighbor.getColumnIndex()] = 4;
//                pStack.push(neighbor);
//            }
//        }
//
//        Maze maze = new Maze(mazeArr,startP,new Position(5,5));
//        return maze;
//
//
//
//
//
///*        int endRow = rand.nextInt(rows);
//        int endCol = rand.nextInt(columns);
//        Position endP = new Position(rand.nextInt(rows), rand.nextInt(columns));
//        while (startP.equals(endP)){
//            endP.setRowPos(rand.nextInt(rows));
//            endP.setColPos(rand.nextInt(columns));
//        }*/
//
//    }
//
//
//
//
//
//
//    public boolean DoesIHaveUnVisitedNeighbors (Position currentPosition,int rows, int columns,int[][] maze) {
//        boolean bool = false;
//
//        //down
//        if (currentPosition.getRowIndex() + 2 < rows) {
//            if (maze[(currentPosition.getRowIndex() + 2)][currentPosition.getColumnIndex()] == 8)
//                bool = true;
//        }
//        //up
//        if (currentPosition.getRowIndex() - 2 > 0) {
//            if (maze[(currentPosition.getRowIndex() - 2)][currentPosition.getColumnIndex()] == 8)
//                bool = true;
//        }
//        // right
//        if (currentPosition.getColumnIndex() + 2 < columns) {
//            if (maze[(currentPosition.getRowIndex())][currentPosition.getColumnIndex() + 2] == 8)
//                bool = true;
//        }
//        //left
//        if (currentPosition.getColumnIndex() - 2 > 0) {
//            if (maze[(currentPosition.getRowIndex())][currentPosition.getColumnIndex() - 2] == 8)
//                bool = true;
//        }
//        return bool;
//    }
//
//    public int PickRandomMove(Position currentPosition,int rows, int columns,int[][] maze){
//        //dynamic array
//        int sizeArray = 0;
//        int[] randArray = new int[sizeArray];
//
//        //down
//        if (currentPosition.getRowIndex() + 2 < rows) {
//            if (maze[(currentPosition.getRowIndex() + 2)][currentPosition.getColumnIndex()] == 8){
//               sizeArray++;
//                randArray = new int[sizeArray];
//                randArray[sizeArray-1] = 1;
//            }
//        }
//        //up
//        if (currentPosition.getRowIndex() - 2 > 0) {
//            if (maze[(currentPosition.getRowIndex() - 2)][currentPosition.getColumnIndex()] == 8){
//                sizeArray++;
//                randArray = new int[sizeArray];
//                randArray[sizeArray-1] = 2;
//            }
//        }
//        // right
//        if (currentPosition.getColumnIndex() + 2 < columns) {
//            if (maze[(currentPosition.getRowIndex())][currentPosition.getColumnIndex() + 2] == 8){
//                sizeArray++;
//                randArray = new int[sizeArray];
//                randArray[sizeArray-1] = 3;
//            }
//
//        }
//        //left
//        if (currentPosition.getColumnIndex() - 2 > 0) {
//            if (maze[(currentPosition.getRowIndex())][currentPosition.getColumnIndex() - 2] == 8){
//                sizeArray++;
//                randArray = new int[sizeArray];
//                randArray[sizeArray-1] = 4;
//            }
//        }
//        // 1= Down, 2=Up, 3=Right, 4=Left
//        int randomMove = pickRandomNumFromArr(randArray);
//        return randomMove;
//    }
//
//    public int pickRandomNumFromArr(int[] randArray){
//        //
//        if (randArray.length == 0){
//            return -1;
//        }
//        else{
//            Random rand = new Random();
//            int indexNum = rand.nextInt(randArray.length);
//            return randArray[indexNum];
//        }
//    }
//
//    public Position newPositionAndBreakWallBetween(int numMove,Position CurrentPos,int[][] maze){
//        //ERROR
//        if(numMove == -1){
//            return null;
//        }
//        //down
//        else if(numMove == 1){
//            maze[CurrentPos.getRowIndex()+1][CurrentPos.getColumnIndex()] = 0;
//            CurrentPos.setRowPos(CurrentPos.getRowIndex()+2);
//        }
//        //up
//        else if(numMove == 2){
//            maze[CurrentPos.getRowIndex()-1][CurrentPos.getColumnIndex()] = 0;
//            CurrentPos.setRowPos(CurrentPos.getRowIndex()-2);
//        }
//        //right
//        else if(numMove == 3){
//            maze[CurrentPos.getRowIndex()][CurrentPos.getColumnIndex()+1] = 0;
//            CurrentPos.setColPos(CurrentPos.getColumnIndex()+2);
//        }
//        //left
//        else if(numMove == 4){
//            maze[CurrentPos.getRowIndex()][CurrentPos.getColumnIndex()-1] = 0;
//            CurrentPos.setColPos(CurrentPos.getColumnIndex()-2);
//        }
//        return CurrentPos;
//    }
//
//
//
//
//
//
//
//
//}
package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        Stack<Position> pStack = new Stack<Position>();
        ArrayList<Position> path = new ArrayList<Position>();     //save the path of the maze
        Position startPosition, currPosition, nextPosition, endPosition;
        Random rand = new Random();
        int endRowIndex, endColIndex;

        //start with all maze with 1 - no path
        int[][] mazeArr = defaultMaze(rows, columns);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                mazeArr[i][j] = 1;


        //create start position
        startPosition = new Position(rand.nextInt(rows), rand.nextInt(columns));
        //initial the start position with 0
        mazeArr[startPosition.getRowIndex()][startPosition.getColumnIndex()] = 0;

        nextPosition = startPosition;

        //push startPosition to stack
        pStack.push(nextPosition);
        while(!pStack.isEmpty()){
            currPosition = (pStack.pop());
            boolean unVisitedNeighbor = DoesIHaveUnVisitedNeighbors(currPosition,rows,columns,mazeArr);
            if(unVisitedNeighbor){
                pStack.push(currPosition);
                ArrayList<Position> allNeighbors = getNeighboursList(currPosition, mazeArr);
                nextPosition = allNeighbors.get(rand.nextInt(allNeighbors.size()));

                // break the walls
                breakWalls(mazeArr, currPosition.getRowIndex(), nextPosition.getRowIndex(), currPosition.getColumnIndex(), nextPosition.getColumnIndex());

                //mark the neighbor as visited
                mazeArr[nextPosition.getRowIndex()][nextPosition.getColumnIndex()] = 0;
                pStack.push(nextPosition);
                path.add(nextPosition);
            }
        }

        endPosition = path.get(rand.nextInt(path.size()));
        return new Maze(mazeArr, startPosition, endPosition);
    }

    public boolean DoesIHaveUnVisitedNeighbors (Position currentPosition,int rows, int columns,int[][] maze) {
        boolean bool = false;
        int i =2;
        //down
        if (currentPosition.getRowIndex() + i < rows) {
            if (maze[(currentPosition.getRowIndex() + i)][currentPosition.getColumnIndex()] == 1)
                bool = true;
        }
        //up
        if (currentPosition.getRowIndex() - i >= 0) {
            if (maze[(currentPosition.getRowIndex() - i)][currentPosition.getColumnIndex()] == 1)
                bool = true;
        }
        // right
        if (currentPosition.getColumnIndex() + i < columns) {
            if (maze[(currentPosition.getRowIndex())][currentPosition.getColumnIndex() + i] == 1)
                bool = true;
        }
        //left
        if (currentPosition.getColumnIndex() - i >= 0) {
            if (maze[(currentPosition.getRowIndex())][currentPosition.getColumnIndex() - i] == 1)
                bool = true;
        }
        return bool;
    }


    private void breakWalls(int[][] maze, int currRow, int nextRow, int currCol, int nextCol){
        if (currRow - nextRow == 2)
            maze[currRow - 1][currCol] = 0;
        if (currRow - nextRow == -2)
            maze[currRow + 1][currCol] = 0;

        if (currCol - nextCol == 2)
            maze[currRow][currCol - 1] = 0;

        if (currCol - nextCol == -2)
            maze[currRow][currCol + 1] = 0;
    }

    private ArrayList<Position> getNeighboursList(Position currentPos, int[][] maze) {
        ArrayList<Position> result = new ArrayList<>();
        int rowIndex = currentPos.getRowIndex();
        int colIndex = currentPos.getColumnIndex();
        //Down
        int i = 2;
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

