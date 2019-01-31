package view.fxview.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class EditGenreController {

    private MainController parentController;
    private String buttonType;
    private String result = "";
    private TableLine pressedLine;

    @FXML
    private TextField field;

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }

    public void setParentController(MainController controller) {
        parentController = controller;
    }

    public void setPressedLine(TableLine pressedLine) {
        this.pressedLine = pressedLine;
        field.setText(pressedLine.getGenreName());
    }

    public void actionClose(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void actionSave(ActionEvent actionEvent) {
        switch (buttonType) {
            case "addGenre":
                result = "1|" + field.getText() + "|-|-|-|0";
                break;
            case "deleteGenre":
                result = "2|" + field.getText() + "|-|-|-|0";
                break;
            case "changeGenre":
                if (pressedLine == null) {
                    parentController.updateInformationField("Ошибка! (Жанр не выбран)");
                    Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    stage.close();
                    return;
                }
                result = "4|" + field.getText() + "|-|-|-|0|" + pressedLine.getGenreName() + "|0";
                break;
        }
        parentController.sentRequest(result);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
        parentController.sentRequest("5");
    }
}
