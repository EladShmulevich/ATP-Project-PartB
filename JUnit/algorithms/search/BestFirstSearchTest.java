package algorithms.search;

import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

class BestFirstSearchTest {

    private static void solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        System.out.println(String.format("'%s' algorithm - nodes evaluated:%s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        System.out.println("Solution path:");
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s.%s",i,solutionPath.get(i)));
        }
    }

    @Test
    void testGetName() throws Exception {
        BreadthFirstSearch BFS = new BreadthFirstSearch();
        Random random = new Random();
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(60, 80);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        mg.measureAlgorithmTimeMillis(1000,1000);

        maze.print();
        // print the start position
        System.out.println(String.format("Start Position: %s", maze.getStartPosition())); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()  +"\n"));
//
        solveProblem(searchableMaze, new BreadthFirstSearch());




        IMazeGenerator emptyMg = new EmptyMazeGenerator();
        Maze emptyMaze = emptyMg.generate(5, 12);
        Position startEmpty = emptyMaze.getStartPosition();
        Position endEmpty = emptyMaze.getGoalPosition();
        SearchableMaze searchableMaze2 = new SearchableMaze(emptyMaze);

        int[][] grid = emptyMaze.getMaze();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j< grid[0].length; j++)
                grid[i][j] = 1;
        }

        SearchableMaze unSearchableMaze = new SearchableMaze(emptyMaze);
        BFS.solve(unSearchableMaze);



//        System.out.println("\n");
//        emptyMaze.print();
//        // print the start position
//        System.out.println(String.format("Start Position: %s", startEmpty)); // format "{row,column}"
//        // prints the maze exit position
//        System.out.println(String.format("Goal Position: %s", endEmpty));




//        solveProblem(searchableMaze2, new BreadthFirstSearch());
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        bfs.getName();


        bfs.solve(null);
    }
}