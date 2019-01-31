package controller;

import connection.Connection;
import model.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    private Model model;
    private ArrayList<Connection> connections = new ArrayList<Connection>();
    private Connection currentConnection;

    public Controller(Model model){
        this.model = model;
        model.addObserver(this);
        System.out.println("Server started...");
        try (ServerSocket server = new ServerSocket(8000)) {
            while (true) {
                try {
                    connections.add(new Connection(this, server.accept()));
                    System.out.println("New client connected");
                } catch (IOException e){
                    System.out.println("Connection exception: " + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        currentConnection.sendResponse((String)arg);
    }

    public synchronized void processRequest(Connection connetion, String request) {
        currentConnection = connetion;
        model.helper(request);
    }

    public synchronized void deleteConnection(Connection connection) {
        connections.remove(connection);
        System.out.println("Working connections: " + connections.size());
    }
}
