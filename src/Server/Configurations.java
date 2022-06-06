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
    private static Properties properties;
    int threadPoolSize;
    public Configurations() {
        properties = new Properties();
        start();
    }

    public static Configurations getInstance() {
        if(configInstance==null){
            configInstance = new Configurations();
        }
//        else{
//            System.out.println("Configuration initiated..");
//
//        }
        return configInstance;
    }


    public synchronized Properties start() {
        try{
            properties.load(new FileInputStream("resources/config.properties"));
            return properties;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static IMazeGenerator generateMazeAlgorithmConfig(){
        return switch (properties.getProperty("mazeGeneratingAlgorithm")) {
            case "EmptyMazeGenerator" -> new EmptyMazeGenerator();
            case "SimpleMazeGenerator" -> new SimpleMazeGenerator();
            default -> new MyMazeGenerator();
        };
    }

    public static ISearchingAlgorithm searchingAlgorithmConfiguration(){
        return switch (properties.getProperty("mazeSearchingAlgorithm")) {
            case "BreadthFirstSearch" -> new BreadthFirstSearch();
            case "DepthFirstSearch" -> new DepthFirstSearch();
            default -> new BestFirstSearch();
        };
    }

    public int getThreadPoolSize(){
        assert false;
        int size = Integer.parseInt(properties.getProperty("threadPoolSize"));
        threadPoolSize = size;
        return size;
    }
}


