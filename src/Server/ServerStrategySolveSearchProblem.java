package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.ArrayList;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    public static final String tempDirectoryPath = System.getProperty("java.io.tmpdir");

    @Override
    public void ServerStrategy(InputStream inFromClient, OutputStream outToClient) {
        try{

            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);

            Maze maze = (Maze)fromClient.readObject();
            SearchableMaze toSearchIn= new SearchableMaze(maze);
            Solution newSolution = HandleSolution(maze, isSolutionExist(maze), toSearchIn);
            toClient.writeObject(newSolution);
            toClient.flush();
            fromClient.close();
            toClient.close();
            toClient.flush();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * checks if we already write a solution for this maze in a file
     * @param maze - Maze
     * @return true/false
     */
    private boolean isSolutionExist(Maze maze){
        return new File(tempDirectoryPath + "/" + maze.hashCode() + ".sol").exists();
    }

    /**
     * return Solution for the maze, if exist and if not find a new one and write it to a file
     * @param maze maze
     * @param exist true/false
     * @param toSearchIn searchable maze
     * @return Solution
     * @throws IOException e
     * @throws ClassNotFoundException e
     */
    private Solution HandleSolution(Maze maze, boolean exist, SearchableMaze toSearchIn) throws IOException, ClassNotFoundException {
        Solution sol;
        if(exist){
            ObjectInputStream inFromFile = new ObjectInputStream(new FileInputStream(tempDirectoryPath + "/" + maze.hashCode() + ".sol"));
            sol = (Solution) inFromFile.readObject();
            inFromFile.close();
        }
        else{
            ObjectOutputStream outToFile = new ObjectOutputStream(new FileOutputStream(tempDirectoryPath + "/" + maze.hashCode()));
//            ISearchingAlgorithm searchingAlg = Configurations.searchingAlgorithmConfiguration();
            ISearchingAlgorithm searchingAlg = new BestFirstSearch();
            sol = searchingAlg.solve(toSearchIn);
//            ArrayList<AState> solutionPath = sol.getSolutionPath();
//            for (int i = 0; i < solutionPath.size(); i++) {
//                System.out.println(String.format("%s.%s", i, solutionPath.get(i)));
//            }
            outToFile.writeObject(sol);
            outToFile.flush();
            outToFile.close();
        }
        return sol;
    }

}
