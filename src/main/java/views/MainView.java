package views;

import controllers.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.EventNote;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainView {

    private MainController controller;
    private ObservableList<EventNote> data;
    @FXML   private Button addBtn;
    @FXML   private TableView contentTable;

    @FXML
    public void initialize(){
        initColumn();
    }

    /**
     * set CellValueFactory of all column
     */
    private void initColumn() {
        ObservableList<TableColumn<EventNote, String>> columns = contentTable.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<>("startTime"));
        columns.get(0).setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(dateFormat.format(param.getValue().getStartTime()));
            return property;
        });
        columns.get(1).setCellValueFactory(param -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat dateFormat = new SimpleDateFormat("HH.mm");
            String start = dateFormat.format(param.getValue().getStartTime());
            String stop = dateFormat.format(param.getValue().getStopTime());
            property.setValue(start + "-" + stop);
            return property;
        });
        columns.get(2).setCellValueFactory(new PropertyValueFactory<>("topic"));
        columns.get(3).setCellValueFactory(new PropertyValueFactory<>("detail"));
    }

    /**
     * handle on click save button
     */
    @FXML
    private void onClickSave(){
        controller.save();
    }

    /**
     * handle on click add button
     */
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
            Stage newEventStage = new Stage();
            newEventStage.setScene(sc);
            newEventStage.setResizable(false);
            newEventStage.setTitle("New Event");
            newEventStage.initModality(Modality.APPLICATION_MODAL);
            newEventStage.showAndWait();
            contentTable.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * set controller to this view
     * @param controller
     */
    public void setController(MainController controller){
        this.controller = controller;
        this.data = FXCollections.observableList(controller.getSchedule().getEvents());
        System.out.println(controller.getSchedule().getEvents());
        this.contentTable.setItems(this.data);
    }


}
