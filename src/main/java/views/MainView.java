package views;

import controllers.CoreController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.EventNote;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class MainView implements RootView{

    private CoreController controller;
    private ObservableList<EventNote> data;
    @FXML   private Button addBtn;
    @FXML   private TableView contentTable;
    @FXML   private Button allBtn;
    @FXML   private DatePicker datePicker;

    @FXML
    public void initialize(){
        initColumn();
        contentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    /**
     * set CellValueFactory of all column
     */
    private void initColumn() {
        ObservableList<TableColumn<EventNote, String>> columns = contentTable.getColumns();
//        columns.get(0).setCellValueFactory(new PropertyValueFactory<>("startTime"));
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
        columns.get(4).setCellValueFactory(new PropertyValueFactory<>("frequency"));

    }

    /**
     * show events relate to selected date
     */
    @FXML
    private void onSelectDate(){
        Date date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<EventNote> events = controller.getSchedule().getEvents(date);
        data = FXCollections.observableList(events);
        contentTable.setItems(data);
    }

    /**
     * show all events
     */
    @FXML
    private void onClickAll(){
        data = FXCollections.observableList(controller.getSchedule().getAllEvents());
        contentTable.setItems(data);
    }
    /**
     * handle on click add button
     */
    @FXML
    private void onClickAdd(){
        System.out.println("onClickAdd");
        createNewEventScene();
        refreshContent();
    }

    /**
     * handle select event action
     * @param e
     */
    @FXML
    private void onClickTable(MouseEvent e){
        if (e.getClickCount() != 2 || contentTable.getSelectionModel().getSelectedItem() == null)
            return;

        EventNote eventNote = (EventNote) contentTable.getSelectionModel().getSelectedItem();
        createEventPropertyScene(eventNote);
    }

    /**
     * popup new event scene
     */
    private void createNewEventScene(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/NewEventView.fxml"));
            Pane mainLayout = loader.load();
            NewEventView newEventView = loader.getController();
            newEventView.setRoot(this);

            Scene sc = new Scene(mainLayout);
            Stage newEventStage = new Stage();
            newEventStage.setScene(sc);
            newEventStage.setResizable(false);
            newEventStage.setTitle("New Event");
            newEventStage.initModality(Modality.APPLICATION_MODAL);
            newEventStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * popup event property scene
     * @param eventNote data to show in popup
     */
    private void createEventPropertyScene(EventNote eventNote){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/EventPropertyView.fxml"));
            Pane mainLayout = loader.load();
            EventPropertyView eventPropertyView = loader.getController();
            eventPropertyView.setRoot(this);
            eventPropertyView.setEventNote(eventNote);

            Scene sc = new Scene(mainLayout);
            Stage eventPropertyStage = new Stage();
            eventPropertyStage.setScene(sc);
            eventPropertyStage.setResizable(false);
            eventPropertyStage.setTitle("Event Property");
            eventPropertyStage.initModality(Modality.APPLICATION_MODAL);
            eventPropertyStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * set controller to this view
     * @param controller controller for this view
     */
    public void setController(CoreController controller){
        this.controller = controller;
        this.data = FXCollections.observableList(controller.getSchedule().getAllEvents());
        System.out.println(controller.getSchedule().getAllEvents());
        this.contentTable.setItems(this.data);

        refreshContent();
    }


    @Override
    public void edit(EventNote oldEvent, EventNote newEvent) {
        boolean result = controller.editEvent(oldEvent, newEvent);
        if(result)
            refreshContent();
    }

    @Override
    public void delete(EventNote event) {
        boolean result = controller.deleteEvent(event);
        if(result)
            refreshContent();
    }

    @Override
    public void add(EventNote event) {
        boolean result = controller.addEvent(event);
        if(result)
            refreshContent();
    }

    /**
     * refresh for show changing of data
     */
    private void refreshContent(){
        contentTable.refresh();
        contentTable.getSortOrder().add(contentTable.getColumns().get(0));
        contentTable.getSortOrder().add(contentTable.getColumns().get(1));
        contentTable.getSortOrder().add(contentTable.getColumns().get(2));
    }
}
