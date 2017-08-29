package views;

import controllers.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.EventNote;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class MainView {

    private MainController controller;
    private ObservableList<EventNote> data;
    @FXML   private Button addBtn;
    @FXML   private TableView contentTable;

    public MainView() {

    }

    @FXML
    public void initialize(){
        ObservableList<TableColumn<EventNote, String>> columns = contentTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<EventNote, String>("startTime"));
        columns.get(0).setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EventNote, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<EventNote, String> param) {
                SimpleStringProperty property = new SimpleStringProperty();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                property.setValue(dateFormat.format(param.getValue().getStartTime()));
                return property;
            }
        });
        columns.get(1).setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EventNote, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<EventNote, String> param) {
                SimpleStringProperty property = new SimpleStringProperty();
                DateFormat dateFormat = new SimpleDateFormat("HH.mm");
                String start = dateFormat.format(param.getValue().getStartTime());
                String stop = dateFormat.format(param.getValue().getStopTime());
                property.setValue(start + "-" + stop);
                return property;
            }
        });
        columns.get(2).setCellValueFactory(new PropertyValueFactory<EventNote, String>("topic"));
        columns.get(3).setCellValueFactory(new PropertyValueFactory<EventNote, String>("detail"));
    }
    @FXML
    private void onClickAdd(){
        System.out.println("onClickAdd");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/NewEventView.fxml"));
            Pane mainLayout = loader.load();
            NewEventView newEventView = loader.getController();
            newEventView.setController(controller);

            Scene sc = new Scene(mainLayout);
            Stage primaryStage = new Stage();
            primaryStage.setScene(sc);
            primaryStage.setResizable(false);
            primaryStage.setTitle("New Event");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.showAndWait();
            contentTable.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setController(MainController controller){
        this.controller = controller;
        this.data = FXCollections.observableList(controller.getSchedule().getEvents());
        System.out.println(controller.getSchedule().getEvents());
        this.contentTable.setItems(this.data);
    }
}
