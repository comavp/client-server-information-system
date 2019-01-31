package connection;

import controller.Controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {

    private Socket socket;
    private Controller parentController;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Thread connectionThread;

    public Connection(Controller controller, Socket socket) throws IOException{
        this.socket = socket;
        this.parentController = controller;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        connectionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!connectionThread.isInterrupted()) {
                    try {
                        String request = (String) input.readObject();
                        System.out.println("Got request: " + request);
                        parentController.processRequest(Connection.this, request);
                    }
                    catch (Exception e) {
                        disconnect();
                        parentController.deleteConnection(Connection.this);
                    }
                }
            }
        });
        connectionThread.start();
    }

    public synchronized void sendResponse(String response) {
        try {
            output.writeObject(response);
            output.flush();
        } catch (IOException e) {
            disconnect();
            parentController.deleteConnection(this);
        }
    }

    public synchronized void disconnect() {
        connectionThread.interrupt();
    }
}
