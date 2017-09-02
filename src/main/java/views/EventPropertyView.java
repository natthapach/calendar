package views;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.EventNote;
import java.time.ZoneId;


public class EventPropertyView {
    @FXML   private TextField topicTextField;
    @FXML   private TextArea detailTextArea;
    @FXML   private DatePicker datePicker;
    @FXML   private Spinner<Integer> startHour;
    @FXML   private Spinner<Integer> startMins;
    @FXML   private Spinner<Integer> endHour;
    @FXML   private Spinner<Integer> endMins;
    private RootView root;
    private EventNote eventNote;


    @FXML
    private void onClickSave(){

    }

    @FXML
    private void onClickDelete(){

    }

    public void setRoot(RootView root) {
        this.root = root;
    }

    public void setEventNote(EventNote eventNote) {
        this.eventNote = eventNote;

        topicTextField.setText(eventNote.getTopic());
        detailTextArea.setText(eventNote.getDetail());
        datePicker.setValue(eventNote.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        startHour.getValueFactory().setValue(eventNote.getStartTime().getHours());
        startMins.getValueFactory().setValue(eventNote.getStartTime().getMinutes());
        endHour.getValueFactory().setValue(eventNote.getStopTime().getHours());
        endMins.getValueFactory().setValue(eventNote.getStopTime().getMinutes());
    }
}
