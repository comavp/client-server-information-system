import view.ConsoleView;
import view.ModelView;
import view.fxview.FXView;
import view.fxview.FXViewFacade;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class InformationSystem implements Observer {

    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ModelView view;

    public static void main(String[] args) throws IOException {
        InformationSystem musicLibrary = new InformationSystem();
        musicLibrary.startFXApp();
        musicLibrary.close();
    }

    private void startConsoleApp() throws IOException {
        view = new ConsoleView();
        view.addObserver(this);
        connectToServer();
        view.execute();
    }

    private void startFXApp() throws IOException {
        view = new FXViewFacade();
        view.addObserver(this);
        connectToServer();
        view.execute();
    }

    private void connectToServer() {
        try {
            socket = new Socket("127.0.0.1", 8000);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            if (view instanceof ConsoleView) {
                view.receiveResult("Ошибка! (Не удалось соединиться с сервером)");
            }
        }
    }

    private void sendRequest(String request) {
        try {
            if (view instanceof FXView) {
                sendRequest("5");
                getResponse();
            }
            output.writeObject(request);
            output.flush();
            getResponse();
        } catch (Exception e){
            view.receiveResult("Ошибка! (Потеряно соединение с сервером)");
        }
    }

    private void getResponse() {
        try {
            String response = (String) input.readObject();
            view.receiveResult(response);
        } catch (Exception e) {
            view.receiveResult("Ошибка! (Потеряно соединение с сервером)");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        sendRequest((String)arg);
    }

    private void close() {
        try {
            socket.close();
            output.close();
            input.close();
        } catch (Exception e) {

        }
    }
}
