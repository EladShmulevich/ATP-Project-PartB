package test;
import algorithms.mazeGenerators.*;
import algorithms.search.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class RunSearchOnMaze {
    public static void main(String[] args) { IMazeGenerator mg = new MyMazeGenerator(); Maze maze = mg.generate(10, 20);

        IMazeGenerator mg2 = new SimpleMazeGenerator();
        Maze maze2 = mg2.generate(4, 8);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Position start = maze.getStartPosition();

        maze.print();
        // print the start position
        System.out.println(String.format("Start Position: %s", maze.getStartPosition())); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
        LinkedList<AState> possibleStates = searchableMaze.getAllPossibleStates(new MazeState(0,start));


        for (AState possibleState : possibleStates) {
            MazeState m = (MazeState) possibleState;
            System.out.println(m.getPosition());
        }



//        solveProblem(searchableMaze, new BreadthFirstSearch()); solveProblem(searchableMaze, new DepthFirstSearch()); solveProblem(searchableMaze, new BestFirstSearch());
    }
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain); System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
                System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) { System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
        }
    }
}