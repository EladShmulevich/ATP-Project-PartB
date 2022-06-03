package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP;
    private int serverPort;
    private IClientStrategy strategy;



    public Client(InetAddress serverIP, int serverPort, IClientStrategy strategy) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.strategy = strategy;
    }

    public void start(){
        try{
            Socket clientSocket = new Socket(serverIP, serverPort);
            //System.out.println("connected to server - IP = " + serverIP + ", Port = " + serverPort);
            strategy.clientStrategy(clientSocket.getInputStream(),clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
