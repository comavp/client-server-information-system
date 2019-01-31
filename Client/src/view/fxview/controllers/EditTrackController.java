package view.fxview.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditTrackController {

    private MainController parentController;
    private String buttonType;
    private String result = "";
    private TableLine pressedLine;

    @FXML
    private TextField trackNameField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField albumField;
    @FXML
    private TextField lengthField;

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }

    public void setParentController(MainController controller) {
        parentController = controller;
    }

    public void setPressedLine(TableLine pressedLine) {
        this.pressedLine = pressedLine;
        trackNameField.setText(pressedLine.getTrackName());
        artistField.setText(pressedLine.getArtist());
        albumField.setText(pressedLine.getAlbum());
        lengthField.setText(pressedLine.getLength());
    }

    public void actionClose(ActionEvent actionEvent) {
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void actionSave(ActionEvent actionEvent) {
        try {
            Integer.parseInt(lengthField.getText());
        } catch (Exception e) {
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.close();
            parentController.updateInformationField("Ошибка! (Некорректное значение длительности трека)");
            return;
        }
        switch (buttonType) {
            case "addTrack":
                result = "1|" + pressedLine.getGenreName() + "|" + trackNameField.getText() + "|" + artistField.getText() + "|" +
                        albumField.getText() + "|" + lengthField.getText();
                break;
            case "deleteTrack":
                result = "2|" + pressedLine.getGenreName() + "|" + trackNameField.getText() + "|" + artistField.getText() + "|" +
                        albumField.getText() + "|" + lengthField.getText();
                break;
            case "changeTrack":
                if (pressedLine.getTrackName().equals("-") && pressedLine.getArtist().equals("-") &&
                        pressedLine.getLength().equals("-")) {
                    Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                    stage.close();
                    parentController.updateInformationField("Ошибка! (Не существует треков данного жанра)");
                    return;
                }
                result = "4|" + "-|" + trackNameField.getText() + "|" + artistField.getText() + "|" +
                        albumField.getText() + "|" + lengthField.getText() + "|" + pressedLine.getGenreName() + "|" + parentController.getNumberOfTrack(pressedLine.toString());
                break;
        }
        parentController.sentRequest(result);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.close();
        parentController.sentRequest("5");
    }
}
