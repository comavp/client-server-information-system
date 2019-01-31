package view.fxview.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

public class MainController extends Observable {

    private ObservableList<TableLine> data = FXCollections.observableArrayList();
    private ArrayList<TableLineInformation> numbersOfTracks = new ArrayList<TableLineInformation>();

    @FXML
    private TableView dataTable;
    @FXML
    private TableColumn<TableLine, String> genreNameColumn;
    @FXML
    private TableColumn<TableLine, String> trackNameColumn;
    @FXML
    private TableColumn<TableLine, String> artistColumn;
    @FXML
    private TableColumn<TableLine, String> albumColumn;
    @FXML
    private TableColumn<TableLine, String> lengthColumn;
    @FXML
    private Label informationField;

    private void fillData() {
        genreNameColumn.setCellValueFactory(new PropertyValueFactory<TableLine, String>("genreName"));
        trackNameColumn.setCellValueFactory(new PropertyValueFactory<TableLine, String>("trackName"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<TableLine, String>("artist"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<TableLine, String>("album"));
        lengthColumn.setCellValueFactory(new PropertyValueFactory<TableLine, String>("length"));
        dataTable.setItems(data);
    }

    public int getNumberOfTrack(String line) {
        int number = 0;
        for (TableLineInformation x : numbersOfTracks) {
            if (x.getLine().equals(line)) {
                number = x.getNumber();
                break;
            }
        }
        return number;
    }

    public void showDialog(ActionEvent actionEvent, String resourcePath, String stageTitle) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
            Parent root = loader.load();

            stage.setTitle(stageTitle);
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);

            if (actionEvent.getSource() instanceof Node) {
                stage.setMinHeight(80);
                stage.setMinWidth(500);
                stage.setScene(new Scene(root, 500, 80));
                stage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());

                FilePathController controller = loader.getController();
                controller.setParentController(this);
            } else {
                MenuItem item = (MenuItem)actionEvent.getSource();
                String id = item.getId();
                if (id.equals("addTrack") || id.equals("deleteTrack") || id.equals("changeTrack")) {
                    stage.setMinHeight(160);
                    stage.setMinWidth(500);
                    stage.setScene(new Scene(root, 500, 160));

                    EditTrackController controller = loader.getController();
                    controller.setParentController(this);
                    controller.setButtonType(id);
                    TableLine line = (TableLine)dataTable.getSelectionModel().getSelectedItem();
                    if (line != null) {
                        controller.setPressedLine(line);
                    } else {
                        updateInformationField("Ошибка. Не выбран ни один жанр");
                        return;
                    }
                } else {
                    stage.setMinHeight(80);
                    stage.setMinWidth(500);
                    stage.setScene(new Scene(root, 500, 80));

                    EditGenreController controller = loader.getController();
                    controller.setParentController(this);
                    controller.setButtonType(id);
                    TableLine line = (TableLine)dataTable.getSelectionModel().getSelectedItem();
                    if (line != null) {
                        controller.setPressedLine(line);
                    }
                }

            }
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAddGenre(ActionEvent actionEvent) {
        String resourcePath = "../fxmlComponents/EditGenreForm.fxml";
        String stageTitle = "Добавление нового жанра";
        showDialog(actionEvent, resourcePath, stageTitle);
    }

    public void handleAddTrack(ActionEvent actionEvent) {
        String resourcePath = "../fxmlComponents/EditTrackForm.fxml";
        String stageTitle = "Добавление нового трека";
        showDialog(actionEvent, resourcePath, stageTitle);
    }

    public void handleDeleteGenre(ActionEvent actionEvent) {
        String resourcePath = "../fxmlComponents/EditGenreForm.fxml";
        String stageTitle = "Удаление жанра";
        showDialog(actionEvent, resourcePath, stageTitle);
    }

    public void handleDeleteTrack(ActionEvent actionEvent) {
        String resourcePath = "../fxmlComponents/EditTrackForm.fxml";
        String stageTitle = "Удаление трека";
        showDialog(actionEvent, resourcePath, stageTitle);
    }

    public void handleChangeGenre(ActionEvent actionEvent) {
        String resourcePath = "../fxmlComponents/EditGenreForm.fxml";
        String stageTitle = "Изменение жанра";
        showDialog(actionEvent, resourcePath, stageTitle);
    }

    public void handleChangeTrack(ActionEvent actionEvent) {
        String resourcePath = "../fxmlComponents/EditTrackForm.fxml";
        String stageTitle = "Изменение трека";
        showDialog(actionEvent, resourcePath, stageTitle);
    }

    public void handleChoosePath(ActionEvent actionEvent) {
        String resourcePath = "../fxmlComponents/FilePathForm.fxml";
        String stageTitle = "Введите путь к файлу";
        showDialog(actionEvent, resourcePath, stageTitle);
    }

    public void receiveResult(String result) {
        int i = 0;
        String typeOfResult = "";
        while(i < result.length()) {
            if (result.charAt(i) == '\n') {
                break;
            }
            typeOfResult += result.charAt(i);
            i++;
        }
        if (typeOfResult.equals("Текущая музыкальная библиотека:")) {
            data.clear();
            numbersOfTracks.clear();
            i++;
            if (result.charAt(i) == 'Б') {
                updateInformationField("Музыкальная библиотека пуста");
            } else {
                for (; i < result.length(); i++) {
                    String genreName = "";
                    TableLine newLine;

                    while (result.charAt(i) != '\n') {
                        genreName += result.charAt(i);
                        i++;
                    }

                    if (i + 4 < result.length()) {
                        Integer cnt = 1;
                        if (result.charAt(i + 4) == cnt.toString().charAt(0) && result.charAt(i + 5) == '.') {
                            while (result.charAt(i + 4) == cnt.toString().charAt(0) && result.charAt(i + 5) == '.') {
                                String trackName = "";
                                String artist = "";
                                String album = "";
                                String length = "";

                                i += 4;
                                while (result.charAt(i) != '(') {
                                    trackName += result.charAt(i);
                                    i++;
                                }
                                i++;
                                while (result.charAt(i) != ',') {
                                    artist += result.charAt(i);
                                    i++;
                                }
                                i++;
                                while (result.charAt(i) != ',') {
                                    album += result.charAt(i);
                                    i++;
                                }
                                i++;
                                while (result.charAt(i) != ')') {
                                    length += result.charAt(i);
                                    i++;
                                }
                                i++;
                                cnt++;
                                newLine = new TableLine(genreName.substring(3, genreName.length() - 1), trackName.substring(3), artist, album.substring(1), length.substring(1));
                                if (!data.contains(newLine)) {
                                    data.add(newLine);
                                    numbersOfTracks.add(new TableLineInformation(cnt - 1, newLine.toString()));
                                }
                                if (i + 4 >= result.length()) {
                                    break;
                                }
                            }
                        } else {
                            newLine = new TableLine(genreName.substring(3, genreName.length() - 1), "-", "-", "-", "-");
                            if (!data.contains(newLine)) {
                                data.add(newLine);
                            }
                        }
                    } else {
                        newLine = new TableLine(genreName.substring(3, genreName.length() - 1), "-", "-", "-", "-");
                        if (!data.contains(newLine)) {
                            data.add(newLine);
                        }
                    }
                }
            }
            fillData();
        } else {
            updateInformationField(result);
        }
    }

    public void sentRequest(String request) {
        setChanged();
        notifyObservers(request);
        clearChanged();
    }

    public void updateInformationField(String message) {

        informationField.setText("Результат работы программы: " + message);
    }
}
