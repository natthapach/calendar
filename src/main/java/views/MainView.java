package views;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.awt.*;

public class MainView {
    @FXML   private DatePicker datePicker;
    @FXML   private Label AppNameLabel;
    @FXML   private FlowPane contentPane;

    public MainView() {

    }

    public void addContent(Pane content){
        contentPane.getChildren().add(content);
    }
}
