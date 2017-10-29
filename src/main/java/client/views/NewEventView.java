package client.views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import common.models.EventNote;
import common.models.Schedule;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by 708 on 8/29/2017.
 */
public class NewEventView implements AlertableEmptyTopic, ChildView{
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


    @FXML
    public void initialize(){
        datePicker.setValue(LocalDate.now());

        ToggleGroup group = new ToggleGroup();
        onceRadio.setToggleGroup(group);
        dailyRadio.setToggleGroup(group);
        weeklyRadio.setToggleGroup(group);
        monthlyRadio.setToggleGroup(group);
        onceRadio.setSelected(true);

    }

    /**
     * handle on click submit button
     * check empty topic to alert popup
     * create new EventNote and send to controller
     */
    @FXML
    private void onClickSubmit(){
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
        else
            frequency = Schedule.MONTHLY;

        EventNote event = new EventNote(0, topic, detail, startTime, stopTime, frequency);

        root.add(event);

        Stage stage = (Stage) topicTextField.getScene().getWindow();
        stage.close();
    }

    /**
     * set root view of this view for sent signal
     * @param root
     */
    public void setRoot(RootView root) {
        this.root = root;
    }


}
