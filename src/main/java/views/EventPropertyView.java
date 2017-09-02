package views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.EventNote;
import java.time.ZoneId;
import java.util.Date;


public class EventPropertyView implements AlertableEmptyTopic {
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
        String topic = topicTextField.getText();
        if(topic.equals("")) {
            showEmptyTopicDialog();
            return;
        }
        String detail = detailTextArea.getText();
        Date date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date startTime = ((Date) date.clone());
        startTime.setHours(startHour.getValue());
        startTime.setMinutes(startMins.getValue());
        Date stopTime = ((Date) date.clone());
        stopTime.setHours(endHour.getValue());
        stopTime.setMinutes(endMins.getValue());

        EventNote newEvent = new EventNote(topic, detail, startTime, stopTime);
        root.edit(eventNote, newEvent);

        close();
    }

    @FXML
    private void onClickDelete(){
        root.delete(eventNote);
        close();
    }

    private void close(){
        Stage stage = (Stage) topicTextField.getScene().getWindow();
        stage.close();
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
