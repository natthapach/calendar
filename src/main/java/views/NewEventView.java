package views;

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

import java.time.LocalDate;
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

    @FXML
    private void onClickSubmit(){
        String topic = topicTextField.getText();
        String detail = detailTextArea.getText();

        if(topic.equals(""))
            showEmptyTopicDialog();
        else{

        }
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
