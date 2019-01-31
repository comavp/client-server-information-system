package view.fxview;

import view.ModelView;
import view.fxview.controllers.MainController;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class FXViewFacade extends Observable implements ModelView, Observer {

    private MainController controller;
    private FXView view;

    public FXViewFacade() {
        FXView.setParentFacade(this);
        view = new FXView();
    }

    public void execute() throws IOException {
        view.execute();
    }

    public void receiveResult(String result) {
        controller.receiveResult(result);
    }

    public void setController(MainController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    public void sentRequest(String request) {
        setChanged();
        notifyObservers(request);
        clearChanged();
    }

    public void update(Observable o, Object arg){
        sentRequest((String)arg);
    }
}
