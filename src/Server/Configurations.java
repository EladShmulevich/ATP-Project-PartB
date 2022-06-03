package Server;

import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;
import java.util.Properties;

public class Configurations {
    private static Configurations configInstance = null;
    private static final Properties properties = null;
    int threadPoolSize;
    public Configurations() throws IOException {
        start();
    }

    public static Configurations getInstance() throws IOException {
        if(configInstance==null){
            configInstance = new Configurations();
        }
        else{
            System.out.println("Configuration initiated..");
        }
        return configInstance;
    }


    public static void start() throws IOException {
        try{
            InputStream input = new FileInputStream("resources/config.properties");
            assert false;
            properties.load(input);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static IMazeGenerator generateMazeAlgorithmConfig(){
        IMazeGenerator result = null;
        switch (properties.getProperty("mazeGeneratingAlgorithm")){
            case "MyMazeGenerator": result = new MyMazeGenerator();
            case "EmptyMazeGenerator": result = new EmptyMazeGenerator();
            case "SimpleMazeGenerator": result = new SimpleMazeGenerator();
            default : result = new MyMazeGenerator();
        }
        return result;
    }

    public ISearchingAlgorithm searchingAlgorithmConfiguration(){
        ISearchingAlgorithm result = null;
        switch (properties.getProperty("mazeSearchingAlgorithm")){
            case "BestFirstSearch": result = new BestFirstSearch();
            case "BreadthFirstSearch": result = new BreadthFirstSearch();
            case "DepthFirstSearch": result = new DepthFirstSearch();
            default : result = new BestFirstSearch();
        }
        return result;
    }

    public int getThreadPoolSize(){
        assert false;
        int size = Integer.parseInt(properties.getProperty("threadPoolSize"));
        threadPoolSize = size;
        return size;
    }
}


