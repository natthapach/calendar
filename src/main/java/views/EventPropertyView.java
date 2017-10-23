package views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.EventNote;
import models.Schedule;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class EventPropertyView implements AlertableEmptyTopic, EventView {
    @FXML   private TextField topicTextField;
    @FXML   private TextArea detailTextArea;
    @FXML   private DatePicker datePicker;
    @FXML   private Spinner<Integer> startHour;
    @FXML   private Spinner<Integer> startMins;
    @FXML   private Spinner<Integer> endHour;
    @FXML   private Spinner<Integer> endMins;
    @FXML   private RadioButton onceRadio;
    @FXML   private RadioButton dailyRadio;
    @FXML   private RadioButton weeklyRadio;
    @FXML   private RadioButton monthlyRadio;
    private RootView root;
    private EventNote eventNote;


    @FXML
    public void initialize(){
        datePicker.setValue(LocalDate.now());

        ToggleGroup group = new ToggleGroup();
        onceRadio.setToggleGroup(group);
        dailyRadio.setToggleGroup(group);
        weeklyRadio.setToggleGroup(group);
        monthlyRadio.setToggleGroup(group);
    }
    /**
     * handle save action
     */
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

        String frequency = null;
        if (onceRadio.isSelected())
            frequency = Schedule.ONCE;
        else if (dailyRadio.isSelected())
            frequency = Schedule.DAILY;
        else if (weeklyRadio.isSelected())
            frequency = Schedule.WEEKLY;
        else if (monthlyRadio.isSelected())
            frequency = Schedule.MONTHLY;

        EventNote newEvent = new EventNote(0, topic, detail, startTime, stopTime, frequency);
        root.edit(eventNote, newEvent);

        close();
    }

    /**
     * handle delete action
     */
    @FXML
    private void onClickDelete(){
        root.delete(eventNote);
        close();
    }

    /**
     * close stage
     */
    private void close(){
        Stage stage = (Stage) topicTextField.getScene().getWindow();
        stage.close();
    }

    /**
     * set root view to this view
     * @param root
     */
    public void setRoot(RootView root) {
        this.root = root;
    }

    /**
     * set data
     * @param eventNote data
     */
    public void setEventNote(EventNote eventNote) {
        this.eventNote = eventNote;

        topicTextField.setText(eventNote.getTopic());
        detailTextArea.setText(eventNote.getDetail());
        datePicker.setValue(eventNote.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        startHour.getValueFactory().setValue(eventNote.getStartTime().getHours());
        startMins.getValueFactory().setValue(eventNote.getStartTime().getMinutes());
        endHour.getValueFactory().setValue(eventNote.getStopTime().getHours());
        endMins.getValueFactory().setValue(eventNote.getStopTime().getMinutes());

        String frequency = eventNote.getFrequency();
        if (Schedule.ONCE.equals(frequency))
            onceRadio.setSelected(true);
        else if (Schedule.DAILY.equals(frequency))
            dailyRadio.setSelected(true);
        else if (Schedule.WEEKLY.equals(frequency))
            weeklyRadio.setSelected(true);
        else if (Schedule.MONTHLY.equals(frequency))
            monthlyRadio.setSelected(true);
    }

}
