package view.fxview.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class FilePathController {

    private MainController parentController;
    private String result = "";

    @FXML
    private TextField field;

    public void setParentController(MainController controller) {
        parentController = controller;
    }

    public void actionClose(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void actionSave(ActionEvent actionEvent) {
        result = "6|" + field.getText();
        parentController.sentRequest(result);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
        parentController.sentRequest("5");
    }
}
