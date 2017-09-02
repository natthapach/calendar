package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public interface AlertableEmptyTopic {
    default void showEmptyTopicDialog(){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.UTILITY);
        Button okBtn = new Button("Ok");
        okBtn.setOnAction(event -> { dialogStage.close();});
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
