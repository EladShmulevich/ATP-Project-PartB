package algorithms.mazeGenerators;


import java.io.Serializable;

public class Maze implements Serializable {
    private final int[][] maze;
    private final Position startPosition;
    private final Position endPosition;

    public Maze(int[][] maze, Position startPosition, Position endPosition) {
        this.maze = maze;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public Maze(byte[] b) {
        int rowsNum = byteToInt(b[0], b[1]);
        int colsNum = byteToInt(b[2], b[3]);
        this.maze = new int[rowsNum][colsNum];
        this.startPosition = new Position(byteToInt(b[4], b[5]), byteToInt(b[6], b[7]));
        this.endPosition = new Position(byteToInt(b[8], b[9]), byteToInt(b[10], b[11]));
        for(int i = 0; i < rowsNum; i++)
            for(int j = 0; j < colsNum; j++)
                this.maze[i][j] = (int) b[12+j+i*colsNum];
    }

    int byteToInt(byte div, byte reminder){
        return (div&0xFF)*256 + reminder&0xFF;
    }


    /**
     * @return start position
     */
    public Position getStartPosition(){
        return  this.startPosition;
    }

    /**
     *
     * @return end position
     */
    public Position getGoalPosition(){
        return  this.endPosition;
    }

    /**
     *
     * @return the maze array
     */
    public int[][] getMaze() {
        return maze;
    }

    public void print(){
        for (int row = 0; row < maze.length; row++) {
            for(int col = 0; col < maze[0].length; col++){
                if(row == this.getStartPosition().getRowIndex() && this.getStartPosition().getColumnIndex() == col)
                    System.out.print("S");
                else  if(row == this.getGoalPosition().getRowIndex() && this.getGoalPosition().getColumnIndex() == col)
                    System.out.print("E");
                else
                    System.out.print(maze[row][col]);
            }
            System.out.print("\n");
        }
    }


    /**
     * [0-1] num of rows, [2-3] num of cols, [4-5] row index start point, [6-7] col index start point
     * @return byteArray of the maze size, end point, start point
     */
    public byte[] toByteArray(){
        int byteSize = 256;
        int rowsNum = maze.length;
        int colsNum = maze[0].length;
        byte[] byteMaze = new byte[12 + rowsNum*colsNum];
        byteMaze[0] = (byte) (rowsNum/byteSize);
        byteMaze[1] = (byte) (rowsNum%byteSize);
        byteMaze[2] = (byte) (colsNum/byteSize);
        byteMaze[3] = (byte) (colsNum%byteSize);
        byteMaze[4] = (byte) (getStartPosition().getRowIndex()/byteSize);
        byteMaze[5] = (byte) (getStartPosition().getRowIndex()%byteSize);
        byteMaze[6] = (byte) (getStartPosition().getColumnIndex()/byteSize);
        byteMaze[7] = (byte) (getStartPosition().getColumnIndex()%byteSize);
        byteMaze[8] = (byte) (getGoalPosition().getRowIndex()/byteSize);
        byteMaze[9] = (byte) (getGoalPosition().getRowIndex()%byteSize);
        byteMaze[10] = (byte) (getGoalPosition().getColumnIndex()/byteSize);
        byteMaze[11] = (byte) (getGoalPosition().getColumnIndex()%byteSize);

        for(int i = 0; i < rowsNum; i++)
            for(int j = 0; j < colsNum; j++)
                byteMaze[12+j+i*colsNum] = (byte) maze[i][j];
        return  byteMaze;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
