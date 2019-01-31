package view.fxview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.fxview.controllers.MainController;

import java.io.IOException;

public class FXView extends Application {

    private static FXViewFacade parentFacade;
    private Parent root;
    private MainController controller;

    public void execute() throws IOException {
        Application.launch();
    }

    public static void setParentFacade(FXViewFacade facade) {
        parentFacade = facade;
    }

    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlComponents/FXView.fxml"));
        root = loader.load();
        controller = loader.getController();
        parentFacade.setController(controller);
        parentFacade.sentRequest("5");
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("MusicLibrary");
        stage.setMinHeight(600);
        stage.setMinWidth(500);
        stage.setScene(new Scene(root, 600, 500));
        stage.show();
    }
}
