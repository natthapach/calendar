package views;

import controllers.MainController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.EventNote;
import sun.applet.Main;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 708 on 8/29/2017.
 */
public class NewEventView {
    @FXML   private TextField topicTextField;
    @FXML   private TextArea detailTextArea;
    @FXML   private DatePicker datePicker;
    @FXML   private Spinner<Integer> startHour;
    @FXML   private Spinner<Integer> startMins;
    @FXML   private Spinner<Integer> endHour;
    @FXML   private Spinner<Integer> endMins;
    private MainController controller;

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

        EventNote event = new EventNote(topic, detail, startTime, stopTime);

        controller.addEventNote(event);

        Stage stage = (Stage) topicTextField.getScene().getWindow();
        stage.close();
    }
    public void setController(MainController controller){
        this.controller = controller;
    }
    @FXML
    public void initialize(){
        datePicker.setValue(LocalDate.now());
    }

    private void showEmptyTopicDialog(){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.UTILITY);
        Button okBtn = new Button("Ok");
        okBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogStage.close();
            }
        });
        VBox vbox = new VBox(new Text("Please enter topic"), okBtn);
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20,0,20, 0));
        dialogStage.setMinWidth(200);
        dialogStage.setTitle("Warning");
        dialogStage.setScene(new Scene(vbox));
        dialogStage.showAndWait();
    }
}
